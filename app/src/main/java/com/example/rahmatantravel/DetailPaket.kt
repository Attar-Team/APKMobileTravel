package com.example.rahmatantravel

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.setMargins
import com.bumptech.glide.Glide
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.paketResponse.APIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder
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
    private lateinit var buttonBack : CardView
    private lateinit var jumlahKursi : TextView
    private lateinit var deskripsiPaket: TextView

    var ID_Keberangakatan : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_paket)

        initializeViews()

        btn_booking = findViewById(R.id.btn_booking);
        val paketId = intent.getStringExtra("keberangkatan_id")!!.toInt()

        Log.d("paket_id", paketId.toString())



        buttonBack = findViewById(R.id.buttonkemabli)
        buttonBack.setOnClickListener {
            onBackPressed()
        }

        val dialog = Dialog(this@DetailPaket)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        RetrofitClient.instance.getPaketById(paketId).enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let { displayPaketDetails(it) }

                    ID_Keberangakatan = apiResponse?.let { displayPaketDetails(it) }!!

                    btn_booking.setOnClickListener {
                        val intent = Intent(this@DetailPaket, DetailBooking::class.java)
                        intent.putExtra("paket_id", paketId.toString())
                        intent.putExtra("keberangkatan_id", ID_Keberangakatan.toString())
                        Log.d("KEBERANGAKAATAN", ID_Keberangakatan.toString())
                        startActivity(intent)
                    }

                    Log.d("Response Success", "onResponse: $apiResponse")
                    dialog.dismiss()
                } else {
                    val errorBody = response.errorBody()?.string()

                    Log.e("Response Failed", "onResponse: Error Body: $errorBody")
                    showDialogFail()
                }
            }
            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Log.e("Response On Failure", "Failed: ${t.message}")
                showDialogFail()
            }
        })

        Log.d("KEBERANGAKAATAN", ID_Keberangakatan.toString())

    }

    private fun displayPaketDetails(apiResponse: APIResponse): Int {
        val paket = apiResponse.data[0].paket[0]
        val kursi = apiResponse.data[0].seats
        val hotelList = apiResponse.data[0].paket[0].hotel

        Log.d("Hotel List Size", hotelList.size.toString())

        val formattedDate = formatDate(apiResponse.data[0].tanggal)
        ID_Keberangakatan = apiResponse.data[0].id

        Log.d("id_keberangakatan", ID_Keberangakatan.toString())

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

        val mainLayout = findViewById<LinearLayout>(R.id.hotel) // Gantilah dengan ID layout utama yang sesuai

        mainLayout.removeAllViews()

        for (hotel in hotelList) {
            val linearLayout = LinearLayout(this)
            linearLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 10, 0, 10)  // Atur margin di sini
            }
            linearLayout.orientation = LinearLayout.HORIZONTAL

            // ImageView untuk icon hotel
            val iconHotel = ImageView(this)
            iconHotel.layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.icon_size),
                resources.getDimensionPixelSize(R.dimen.icon_size)
            )
            iconHotel.setImageResource(R.drawable.iconhotel)
            linearLayout.addView(iconHotel)

            // TextView untuk nama hotel
            val namaHotel = TextView(this)
            namaHotel.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                resources.getDimensionPixelSize(R.dimen.text_height)
            ).apply {
                setMargins(10, 10, 10, 10)
            }

            // Mengatur fontFamily menggunakan ResourcesCompat.getFont
            namaHotel.typeface = ResourcesCompat.getFont(this@DetailPaket, R.font.poppinsmedium)

            namaHotel.text = hotel.namaHotel
            namaHotel.setTextColor(ContextCompat.getColor(this, R.color.black))
            linearLayout.addView(namaHotel)

            // Menambahkan LinearLayout ke dalam layout utama
            mainLayout.addView(linearLayout)

            val iconBintangLayout = LinearLayout(this)
            iconBintangLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            for (i in 0 until hotel.bintang) {
                val iconBintang = ImageView(this)
                iconBintang.layoutParams = LinearLayout.LayoutParams(
                    resources.getDimensionPixelSize(R.dimen.icon_size),
                    resources.getDimensionPixelSize(R.dimen.icon_size)
                )
                iconBintang.setImageResource(R.drawable.iconbintang)
                iconBintangLayout.addView(iconBintang)
            }

            linearLayout.addView(iconBintangLayout)
        }

        return ID_Keberangakatan
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

    private fun showDialogFail() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_fail)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button = dialog.findViewById<Button>(R.id.btnRepeat)

        button.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
}