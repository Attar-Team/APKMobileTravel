package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText
import android.widget.ImageView;
import android.widget.TextView
import android.widget.Toast

import com.example.rahmatantravel.Adapter.DetailJamaahAdapter;
import com.example.rahmatantravel.Models.DetailJamaahModels;
import com.example.rahmatantravel.SharedPref.PrefManager
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.customerResponse.DataCustomerResponse
import com.example.rahmatantravel.api.paketResponse.APIResponse
import com.example.rahmatantravel.api.paketResponse.HargaPaketResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.text.NumberFormat
import java.text.SimpleDateFormat

import java.util.ArrayList;
import java.util.Date
import java.util.Locale

class DetailBooking : AppCompatActivity() {
    private lateinit var backToMenuLainnya: ImageView
    private lateinit var recycleDetailJamaah: RecyclerView
    private lateinit var detailJamaahAdapter: DetailJamaahAdapter
    private lateinit var detailJamaahModelsArrayList: ArrayList<DetailJamaahModels>
    private lateinit var judulPaket: TextView
    private lateinit var  tanggalPaket: TextView
    private lateinit var lamaPaket: TextView
    private lateinit var rateHotel: TextView
    private lateinit var hargaPaket: TextView
    private lateinit var totalHarga: TextView
    private lateinit var kodeReferal: EditText
    private lateinit var catatanPemesanan: EditText
    private lateinit var btnCheck: Button
    private lateinit var btnAdd: ImageView
    private lateinit var btnBooking: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_booking)

        initializeViews()
        setupListeners()
        fetchData()
    }

    // Inisialisasi tampilan dengan menggunakan findViewById
    private fun initializeViews() {
        btnBooking = findViewById(R.id.btn_booking)
        btnCheck = findViewById(R.id.btn_checkTotal)
        btnAdd = findViewById(R.id.btn_add)
        backToMenuLainnya = findViewById(R.id.backToMenuLainnya)
        recycleDetailJamaah = findViewById(R.id.recycleDetailJamaah)
        judulPaket = findViewById(R.id.judulPaket)
        tanggalPaket = findViewById(R.id.tanggalBerangkat)
        lamaPaket = findViewById(R.id.waktuPaket)
        rateHotel = findViewById(R.id.rateHotel)
        hargaPaket = findViewById(R.id.hargaPaket)
        kodeReferal = findViewById(R.id.edt_kodereferal)
        catatanPemesanan = findViewById(R.id.edt_catatanPemesanan)
        totalHarga = findViewById(R.id.textViewTotalHarga)
    }

    private fun setupListeners() {
        // Menyiapkan listener untuk tombol booking
        btnBooking.setOnClickListener {
            val extras = Bundle()

            extras.putString("KodeReferal", kodeReferal.text.toString())
            extras.putString("CatatanPemesanan", catatanPemesanan.text.toString())

            val total = detailJamaahAdapter.getTotalHarga()
            logData("Total Harga", total.toString())

            val NamaNIK: Map<String, String> = detailJamaahAdapter.mapNamaNIK()
            val IDHarga: Map<String, Int> = detailJamaahAdapter.mapIDHarga()

            val IDHargaString = IDHarga.mapValues { it.value.toString() }

            for ((key, value) in NamaNIK) {
                extras.putString("NamaNIK_$key", value)
            }

            for ((key, value) in IDHargaString) {
                extras.putString("IDHarga_$key", value)
            }

            if (totalHarga.text == "Rp-" ) {
                Toast.makeText(this, "Mohon check total harga terlebih dahulu", Toast.LENGTH_LONG).show()
            } else if (totalHarga.text == "Rp0,00"){
                Toast.makeText(this, "Mohon tambahkan detail jamaah terlebih dahulu", Toast.LENGTH_LONG).show()
            } else{
                val intent = Intent(this@DetailBooking, Pembayaran::class.java)
                intent.putExtra("totalHarga", total.toString())
                intent.putExtras(extras)
                startActivity(intent)
            }
        }

        // Menyiapkan listener untuk tombol add
        btnAdd.setOnClickListener {
            val itemCount = detailJamaahAdapter.itemCount + 1
            val newDetailJamaah = DetailJamaahModels(itemCount)

            // Menambah data pada adapter
            detailJamaahModelsArrayList.add(newDetailJamaah)
            detailJamaahAdapter.notifyItemInserted(itemCount)
            recycleDetailJamaah.smoothScrollToPosition(itemCount)
        }

        // Menyiapkan listener untuk tombol check
        btnCheck.setOnClickListener {
            val total = detailJamaahAdapter.getTotalHarga()
            val totalFormatted = formatCurrency(total)
            totalHarga.text = "$totalFormatted"

            val NamaNIK : Map<String, String> = detailJamaahAdapter.mapNamaNIK()
            logData("NIK Nama", NamaNIK.toString())
            logData("NIK Nama", NamaNIK.keys.toString())
            logData("NIK Nama", NamaNIK.values.toString())

            val IDHarga : Map<String, Int> = detailJamaahAdapter.mapIDHarga()
            logData("ID Harga", IDHarga.toString())
            logData("ID Harga", IDHarga.keys.toString())
            logData("ID Harga", IDHarga.values.toString())
        }

        // Menyiapkan listener untuk tombol back
        backToMenuLainnya.setOnClickListener { onBackPressed() }
    }

    // Menyiapkan RecyclerView dan adapter
    private fun setupRecyclerView(namaResponse: List<String>, nikResponse: List<String>, paketResponse: APIResponse) {
        detailJamaahAdapter = DetailJamaahAdapter(this, detailJamaahModelsArrayList, nikResponse, namaResponse, paketResponse)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recycleDetailJamaah.layoutManager = layoutManager
        recycleDetailJamaah.adapter = detailJamaahAdapter
    }

    // Menampilkan detail paket pada tampilan
    private fun displayPaketDetails(apiResponse: APIResponse) {
        val paket = apiResponse.data[0].paket[0]

        val formattedDate = formatDate(apiResponse.data[0].tanggal)
        val formattedHarga = formatCurrency(paket.harga[0].harga)

        judulPaket.text = paket.nama_paket
        tanggalPaket.text = formattedDate
        lamaPaket.text = "${paket.lama_hari} Hari"
        hargaPaket.text = "Mulai dari ${formattedHarga}"

    }

    // Menambah data pada adapter
    private fun addDataDetailJamaah(namaJamaah: List<String>, nikResponse: List<String>, paketResponse: APIResponse) {
        detailJamaahModelsArrayList = ArrayList()
        detailJamaahModelsArrayList.add(DetailJamaahModels(1))

        try {
            val adapter = DetailJamaahAdapter(this, detailJamaahModelsArrayList,nikResponse ,namaJamaah, paketResponse)
            recycleDetailJamaah.adapter = adapter
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("DateError", "Error parsing date: " + e.message)
        }
    }

    private fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date: Date = inputFormat.parse(inputDate) ?: Date()
        return outputFormat.format(date)
    }

    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }


    // Memanggil API untuk mendapatkan data paket dan customer secara asynchronous
    private fun fetchData() {

        val paketID = intent.getStringExtra("paket_id")!!.toInt()
        logData("Paket ID", paketID.toString())

        val prefManager = PrefManager(this)
        val loginData = prefManager.getLoginData()

        val userID = loginData.first ?.toInt()
        logData("User ID", userID.toString())

        val paketCall = RetrofitClient.instance.getPaketById(paketID)
        val customerCall = userID?.let { RetrofitClient.instance.getProfileCustomer(it) }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val paketResponse = paketCall.await()
                val customerResponse = customerCall ?.await()

                Log.d("Response Paket", paketResponse.toString())
                Log.d("Response Customer", customerResponse.toString())

                val responseCustomer: Response<List<DataCustomerResponse>> = Response.success(customerResponse ?.data)

                withContext(Dispatchers.Main) {
                    onFetchDataSuccess(paketResponse, responseCustomer)
                }
            } catch (e: Exception) {
                onFetchDataFailure(e.message)
            }
        }

    }

    // Menangani hasil sukses pengambilan data
    private fun onFetchDataSuccess(paketResponse: APIResponse, customerResponse: Response<List<DataCustomerResponse>>) {
        if (customerResponse.isSuccessful) {
            displayPaketDetails(paketResponse)

            val namaArray = customerResponse.body()?.map { it.nama }
            val NIKArray = customerResponse.body()?.map { it.nik }

            logData("Nama Array", namaArray.toString())

            if (namaArray != null && NIKArray != null) {
                addDataDetailJamaah(namaArray, NIKArray, paketResponse)
            }
            if (namaArray != null && NIKArray != null) {
                setupRecyclerView(namaArray, NIKArray, paketResponse)
            }
        } else {
            logData("Response Failed", "Some error occurred.")
        }
    }

    // Menangani kegagalan pengambilan data
    private fun onFetchDataFailure(message: String?) {
        logData("Response on Failure", message ?: "Unknown error occurred.")
    }

    fun formatCurrency(number: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID")) // Locale untuk Indonesia
        return format.format(number)
    }
}



