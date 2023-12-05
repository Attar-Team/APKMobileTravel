package com.example.rahmatantravel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.paketResponse.APIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailPaket : AppCompatActivity() {

    private lateinit var judulPaket: TextView
    private lateinit var tanggalPaket: TextView
    private lateinit var lamaPaket: TextView
    private lateinit var lamaProgram: TextView
    private lateinit var hargaMulai: TextView
    private lateinit var termasukHarga: TextView
    private lateinit var tidakTermasukHarga: TextView
    private lateinit var bottomViewHarga: TextView
    private lateinit var btn_booking: CardView
    private lateinit var imagePaket: ImageView
    private lateinit var jumlahKursi : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_paket)

        initializeViews()

        btn_booking = findViewById(R.id.btn_booking);
        val paketId = intent.getStringExtra("keberangkatan_id")!!.toInt()

        Log.d("paket_id", paketId.toString())

        btn_booking.setOnClickListener {
            val intent = Intent(this, DetailBooking::class.java)
            intent.putExtra("paket_id", paketId.toString())
            startActivity(intent)
        }

        RetrofitClient.instance.getPaketById(paketId).enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let { displayPaketDetails(it) }

                    Log.d("Response Success", "onResponse: $apiResponse")
                } else {
                    val errorBody = response.errorBody()?.string()

                    Log.e("Response Failed", "onResponse: Error Body: $errorBody")
                }
            }
            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Log.e("Response On Failure", "Failed: ${t.message}")
            }
        })
    }

    private fun initializeViews() {
        judulPaket = findViewById(R.id.judulPaket)
        tanggalPaket = findViewById(R.id.tanggalPaket)
        lamaPaket = findViewById(R.id.lamaHari)
        lamaProgram = findViewById(R.id.lamaProgram)
        hargaMulai = findViewById(R.id.hargaMulai)
        termasukHarga = findViewById(R.id.termasukHarga)
        tidakTermasukHarga = findViewById(R.id.tidaktermasukHarga)
        bottomViewHarga = findViewById(R.id.bottomTextHargaMulai)
        imagePaket = findViewById(R.id.imagebg)
        jumlahKursi = findViewById(R.id.jumlahKursi)
    }

    private fun displayPaketDetails(apiResponse: APIResponse) {
        val paket = apiResponse.data[0].paket[0]
        val kursi = apiResponse.data[0].seats

        val formattedDate = formatDate(apiResponse.data[0].tanggal)

        judulPaket.text = paket.nama_paket
        tanggalPaket.text = formattedDate
        lamaPaket.text = "${paket.lama_hari} Hari"
        lamaProgram.text = "Program ${paket.lama_hari} Hari"

        val hargaTerkecil = paket.harga.minOfOrNull { it.harga }
        hargaMulai.text = formatHargaText(paket.minimDP, hargaTerkecil)

        val termasukHargaFormatted = paket.termasuk_harga.joinToString(separator = "\n • ", prefix = "Harga sudah termasuk :\n • ")
        termasukHarga.text = termasukHargaFormatted

        val tidakTermasukHargaFormatted = paket.tidak_termasuk_harga.joinToString(separator = "\n • ", prefix = "Harga Tidak termasuk :\n • ")
        tidakTermasukHarga.text = tidakTermasukHargaFormatted

        val formattedHarga = formatCurrency(hargaTerkecil ?: 0)

        hargaTerkecil?.let {
            bottomViewHarga.text = formattedHarga
        } ?: run {
            bottomViewHarga.text = "Tidak ada harga tersedia"
        }

        Glide.with(this).load("https://rahmatanumrah.000webhostapp.com/uploads/foto_brosur/${paket.foto_paket}").into(imagePaket)

        val kursiTerisi = "Tesedia ${kursi} Kursi"
        jumlahKursi.text = kursiTerisi
    }

    private fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date: Date = inputFormat.parse(inputDate) ?: Date()
        return outputFormat.format(date)
    }

    private fun formatHargaText(dp: Int, hargaTerkecil: Int?): String {
        return hargaTerkecil?.let {
            // Format harga menggunakan formatCurrency
            val hargaFormatted = formatCurrency(it)
            val dpFomatted = formatCurrency(dp)

            // Kembalikan string yang sudah diformat
            return "Harga paket mulai dari $hargaFormatted\nDp mulai dari $dpFomatted"
        } ?: "Tidak ada harga tersedia"
    }
    fun formatCurrency(number: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID")) // Locale untuk Indonesia
        return format.format(number)
    }
}