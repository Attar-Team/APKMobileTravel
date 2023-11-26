package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rahmatantravel.Adapter.DataCustomerAdapter
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.customerResponse.CustomerResponse
import com.example.rahmatantravel.api.customerResponse.DataCustomerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataJamaah : AppCompatActivity() {

    private lateinit var tambahData: ImageView
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_jamaah)

        // Inisasi Konten View
        tambahData = findViewById(R.id.tambahData)
        recyclerView = findViewById(R.id.recycleDataJamaah)

        tambahData.setOnClickListener {
            val intent = Intent(this@DataJamaah, Dokumen::class.java)  // Pindah intent menuju Dokumen.java
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)  // Inisiasi recyclerView

        // Proses eksekusi retrofit
        RetrofitClient.instance.getProfileCustomer(1).enqueue(object :
            Callback<List<DataCustomerResponse>> {
            override fun onResponse(
                call: Call<List<DataCustomerResponse>>,
                response: Response<List<DataCustomerResponse>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        logData("Response Success", responseBody.toString())

                        val adapter = DataCustomerAdapter(responseBody)  // Inisiasi adapter
                        recyclerView.adapter = adapter  // Mengatur adapter untuk recyclerView

                    } else {
                        logData("Response Body Null", response.message())
                    }
                }else{
                    logData("Response Failed", response.message())
                }
            }
            override fun onFailure(call: Call<List<DataCustomerResponse>>, t: Throwable) {
                logData("Response on Failure", t.message.toString())
            }
        })
    }

    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }
}