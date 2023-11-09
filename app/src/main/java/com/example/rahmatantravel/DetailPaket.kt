package com.example.rahmatantravel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.paketResponse.APIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_paket)

        initializeViews()

        btn_booking = findViewById(R.id.btn_booking);
        val paketId = intent.getStringExtra("keberangkatan_id")!!.toInt()

        Log.d("paket_id", paketId.toString())

        btn_booking.setOnClickListener {
            val intent = Intent(this, DetailBooking::class.java)
            startActivity(intent)
        }
        RetrofitClient.instance.getPaketById(paketId).enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let { displayPaketDetails(it) }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("Error body", "onResponse: Error Body: $errorBody")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Log.e("API Call", "Failed: ${t.message}")
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
    }

    private fun displayPaketDetails(apiResponse: APIResponse) {
        val paket = apiResponse.data[0].paket[0]

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

        hargaTerkecil?.let {
            bottomViewHarga.text = "Rp $it"
        } ?: run {
            bottomViewHarga.text = "Tidak ada harga tersedia"
        }
    }

    private fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date: Date = inputFormat.parse(inputDate) ?: Date()
        return outputFormat.format(date)
    }

    private fun formatHargaText(dp: Int, hargaTerkecil: Int?): String {
        return hargaTerkecil?.let {
            "Harga paket mulai dari Rp $it\nDp mulai dari Rp $dp"
        } ?: "Tidak ada harga tersedia"
    }
}