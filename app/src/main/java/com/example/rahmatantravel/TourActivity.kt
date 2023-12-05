package com.example.rahmatantravel;

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rahmatantravel.Adapter.PaketAdapter
import com.example.rahmatantravel.Models.PaketModels
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.paketResponse.APIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TourActivity : AppCompatActivity() {

    private lateinit var recyclePaketTour: RecyclerView
    private var paketAdapter: PaketAdapter? = null
    private var paketModelsArrayList: ArrayList<PaketModels>? = null
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var backToMenuLainnya: ImageView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour)

        searchView = findViewById(R.id.searchView)
        val searchSrcText = searchView.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        searchSrcText.textSize = 12f
        searchSrcText.setTextColor(0xFF808080.toInt())
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        backToMenuLainnya = findViewById(R.id.backToMenuLainnya)

        backToMenuLainnya.setOnClickListener {
            onBackPressed()
        }

        val dialog = Dialog(this@TourActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        recyclePaketTour = findViewById(R.id.recyclerView)
        imageView = findViewById(R.id.imageView)

        // Sembunyikan imageView di awal
        imageView.visibility = View.GONE

        RetrofitClient.instance.getPaketByMenu("Tour").enqueue(object : Callback<APIResponse>{
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                val responseCode = response.code().toString()

                if (response.isSuccessful){
                    Log.d("responseCode", responseCode)
                    val apiResponse = response.body()
                    if (apiResponse != null) {

                        val data = apiResponse.data
                        if (data.isNullOrEmpty()) {
                            // Jika data kosong, tampilkan imageView dan sembunyikan recyclerView
                            imageView.visibility = View.VISIBLE
                            recyclePaketTour.visibility = View.GONE
                        } else {
                            // Jika data tidak kosong, tampilkan recyclerView dan sembunyikan imageView
                            imageView.visibility = View.GONE
                            recyclePaketTour.visibility = View.VISIBLE
                            // Update recyclerView dengan data
                            recyclePaketTour = findViewById(R.id.recyclerView)
                            imageView = findViewById(R.id.imageView)

                            // Sembunyikan imageView di awal
                            imageView.visibility = View.GONE
                            Log.d("Data API", apiResponse.toString())
                            Log.d("Data Paket", apiResponse.data.flatMap { it.paket }.toString())
                            Log.d("Data Hotel", apiResponse.data.flatMap { it.paket.flatMap { it.hotel } }.toString())

                            val adapter = com.example.rahmatantravel.Adapter.PaketAdapter(
                                apiResponse.data,
                                apiResponse.data.flatMap { it.paket },
                                apiResponse.data.flatMap { it.paket.flatMap { it.hotel } }
                            )

                            Log.d("ID Keberangkatan", apiResponse.data.map { it.id }.toString())


                            adapter.onItemClick = { keberangkatanItem, paketItem, hotelItem ->
                                val intent = Intent(this@TourActivity, DetailPaket::class.java)
                                intent.putExtra("keberangkatan_id", keberangkatanItem.id.toString())
                                intent.putExtra("paket_id", paketItem.id_paket.toString())
                                startActivity(intent)
                            }
                            val layoutManager1 = LinearLayoutManager(this@TourActivity, LinearLayoutManager.VERTICAL, false)
                            recyclePaketTour.layoutManager = layoutManager1
                            recyclePaketTour.adapter = adapter
                            Log.d("recyclerView", "RecyclerView bersasil dibuat")
                        }

                        dialog.dismiss()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("Error body", "onResponse: Error Body: $errorBody")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
