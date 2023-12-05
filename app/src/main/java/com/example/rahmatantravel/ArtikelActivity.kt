package com.example.rahmatantravel;

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.rahmatantravel.Adapter.ArtikelAdapter
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.artikelResponse.APIArtikel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArtikelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artikel)

        val backToMenuLainnya = findViewById<ImageView>(R.id.backToMenuLainnya)
        val artikel = findViewById<RecyclerView>(R.id.recyclerView)

        RetrofitClient.instance.getArtikel().enqueue(object : Callback<APIArtikel> {
            override fun onResponse(call: Call<APIArtikel>, response: Response<APIArtikel>) {
                if (response.isSuccessful){
                    val apiResponse = response.body()
                    if (apiResponse != null) {
                        logData("Artikel Success", apiResponse.data.toString())
                        val artikelAdapter = ArtikelAdapter(apiResponse.data)
                        artikel.adapter = artikelAdapter
                    }else{
                        logData("Artikel is null", response.code().toString())
                    }
                }else{
                    logData("Artikel Fail", response.code().toString())
                }
            }

            override fun onFailure(call: Call<APIArtikel>, t: Throwable) {
                logData("Article on Failure", t.message.toString())
            }
        })

        backToMenuLainnya.setOnClickListener { onBackPressed() }
    }
    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }
}
