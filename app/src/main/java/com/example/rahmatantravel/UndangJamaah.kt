package com.example.rahmatantravel;

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rahmatantravel.SharedPref.PrefManager
import com.example.rahmatantravel.api.PesananAgenResponse
import com.example.rahmatantravel.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Double.sum

class UndangJamaah : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_undang_jamaah)

        val cekTransaksi = findViewById<TextView>(R.id.cekTransaksi)

        val prefManager = PrefManager(this)

        val userID = prefManager.getLoginData().first?.toInt()

        cekTransaksi.setOnClickListener {
            val intent = Intent(this, DataJamaahAgen::class.java)
            startActivity(intent)
        }

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
                            dialog.dismiss()
                            logData("Response Success", responseBody.message)
                            val data = responseBody.data
                            val jumlahPemesanan = data.size
                            val jumlahPemesananString = jumlahPemesanan.toString()

                            val jumlahTransaksi = findViewById<TextView>(R.id.jumlahTransaksi)
                            jumlahTransaksi.text = jumlahPemesananString

                            val Jumlahkomisi = findViewById<TextView>(R.id.komisi)
                            val totalKomisi = data.sumBy { it.komisi.toInt() }
                            Jumlahkomisi.text = totalKomisi.toString()
                        }
                        else {
                            logData("Response Body null", responseBody.toString())
                            dialog.dismiss()
                            showDialogFail()
                        }
                    } else {
                        logData("Response Failed", response.message())
                        dialog.dismiss()
                        showDialogFail()
                    }
                }

                override fun onFailure(call: Call<PesananAgenResponse>, t: Throwable) {
                    logData("Response on Failure", t.message.toString())
                    dialog.dismiss()
                    showDialogFail()
                }
            })
        }
    }

    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }

    private fun showDialogFail() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_fail)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button = dialog.findViewById<Button>(R.id.btnRepeat)
        button.setOnClickListener {
           super.onBackPressed()
        }

        dialog.show()
    }
}
