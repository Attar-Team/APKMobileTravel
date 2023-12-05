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
import androidx.cardview.widget.CardView
import android.widget.EditText
import com.example.rahmatantravel.api.RegisterPostResponse
import com.example.rahmatantravel.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val belumPunyaAkun = findViewById<TextView>(R.id.belumPunyaAkun)
        val namaEditText = findViewById<EditText>(R.id.txt_nama)
        val emailEditText = findViewById<EditText>(R.id.txt_email)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val rePasswordEditText = findViewById<EditText>(R.id.re_password)
        val hobbyEditText = findViewById<EditText>(R.id.txt_hobby)
        val errorMassageTextView = findViewById<TextView>(R.id.errorMessage)
        val btn_register = findViewById<CardView>(R.id.btn_register)

        belumPunyaAkun.setOnClickListener {
            val intent = Intent(this, LoginActivityRevisi2::class.java)
            startActivity(intent)
        }

        btn_register.setOnClickListener {
            val nama = namaEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val rePassword = rePasswordEditText.text.toString()
            val hobby = hobbyEditText.text.toString()

            when {
                password != rePassword -> {
                    errorMassageTextView.text = "Password harus sama !"
                    rePasswordEditText.requestFocus()
                }
                rePassword.isEmpty() -> {
                    errorMassageTextView.text = "Password tidak boleh kosong"
                }
                password.length < 8 -> {
                    errorMassageTextView.text = "Password minimal harus 8 kata!"
                }
                else -> {
                    val data = LinkedHashMap<String, String>()

                    data["nama"] = nama
                    data["email"] = email
                    data["password"] = password
                    data["hoby"] = hobby

                    logData("Nama Lengkap", nama)
                    logData("Email", email)
                    logData("Password", password)
                    logData("Hobby", hobby)

                    val dialog = Dialog(this)
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog.setCancelable(true)
                    dialog.setContentView(R.layout.dialog_loading)
                    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.show()

                    RetrofitClient.instance.register(data).enqueue(object :
                        Callback<RegisterPostResponse> {
                        override fun onResponse(
                            call: Call<RegisterPostResponse>,
                            response: Response<RegisterPostResponse>
                        ) {
                            if(response.isSuccessful){
                                val responseBody = response.body()
                                if (responseBody != null) {
                                    logData("Response Success", responseBody.status)
                                    dialog.dismiss()
                                    showDialogSuccess()
                                }else{
                                    logData("Response body is null", responseBody.toString())
                                    dialog.dismiss()
                                    showDialogFail()
                                }
                            }else{
                                logData("Response Failed", response.toString())
                                dialog.dismiss()
                                showDialogFail()
                            }
                        }

                        override fun onFailure(call: Call<RegisterPostResponse>, t: Throwable) {
                            logData("Response On Failure", t.message.toString())
                            dialog.show()
                            showDialogFail()
                        }

                    })
                }
            }
        }
    }
    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }

    private fun showDialogSuccess() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_login_success)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button = dialog.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivityRevisi2::class.java)
            startActivity(intent)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showDialogFail() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_login_fail)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button = dialog.findViewById<Button>(R.id.btnRepeat)

        button.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
}
