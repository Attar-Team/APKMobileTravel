package com.example.rahmatantravel;

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rahmatantravel.Adapter.DataJamaahAgenAdapter
import com.example.rahmatantravel.SharedPref.PrefManager
import com.example.rahmatantravel.api.PesananAgenResponse
import com.example.rahmatantravel.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataJamaahAgen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_jamaah_agen)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerAnggota)


        val prefManager = PrefManager(this)

        val userID = prefManager.getLoginData().first?.toInt()

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        if (userID != null) {
            RetrofitClient.instance.getPemesananInvitAgen(userID).enqueue(object : Callback<PesananAgenResponse>{
                override fun onResponse(
                    call: Call<PesananAgenResponse>,
                    response: Response<PesananAgenResponse>
                ) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null) {
                            logData("Response Success", responseBody.message)
                            dialog.dismiss()

                            val jumlahTransaksi = findViewById<TextView>(R.id.jumlahTransaksi)
                            val jumlahPemesanan = responseBody.data.size
                            val jumlahPemesananString = jumlahPemesanan.toString()
                            jumlahTransaksi.text = jumlahPemesananString

                            val adapter = DataJamaahAgenAdapter(responseBody.data)
                            recyclerView.adapter = adapter
                            LinearLayoutManager(this@DataJamaahAgen).also { recyclerView.layoutManager = it }
                        } else {
                            logData("Response body null", response.body().toString())
                        }
                    } else {
                        logData("Response Failed", response.message())
                    }
                }

                override fun onFailure(call: Call<PesananAgenResponse>, t: Throwable) {
                    logData("Response onFailure", t.message.toString())
                }
            })
        }
    }
    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }
}
