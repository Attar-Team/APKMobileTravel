package com.example.rahmatantravel;

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.rahmatantravel.Models.UserData
import com.example.rahmatantravel.SharedPref.PrefManager
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.UserPostRequest
import com.example.rahmatantravel.api.UserPostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityRevisi2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_revisi2)

        val myButton = findViewById<CardView>(R.id.loginButton)
        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val belumPunyaAkun = findViewById<TextView>(R.id.belumPunyaAkun)

        belumPunyaAkun.setOnClickListener {
            val intent = Intent(this@LoginActivityRevisi2, RegisterActivity::class.java)
            startActivity(intent)
        }

        myButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            createPost(username, password)

            println("Request sent. Value $username")
            println("Request sent. Value $password")
        }
    }

    private fun showDialogSuccess() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_login_success)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button = dialog.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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

    private fun createPost(email: String, password: String) {

        val prefManager = PrefManager(this)

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        RetrofitClient.instance.post(UserPostRequest(email, password)).enqueue(object : Callback<UserPostResponse> {
            override fun onResponse(call: Call<UserPostResponse>, response: Response<UserPostResponse>) {
                if (response.isSuccessful) {
                    val userPostResponse = response.body()
                    if (userPostResponse != null && userPostResponse.status == 200) {
                        println("Request successful. Response: $email")
                        println("Request successful. Response: $password")
                        println("Request successful. Response: ${userPostResponse.message}")

                        dialog.dismiss()

                        response.body()?.token?.let {
                            response.body() ?.level?.let { it1 ->
                                prefManager.saveLoginData(response.body()?.userId,
                                    it, it1
                                )
                            }
                        }
                        prefManager.setUserName(response.body()!!.nama)

                        showDialogSuccess()
                    } else {
                        println("Unexpected response: $userPostResponse")
                        dialog.dismiss()
                        showDialogFail()
                    }
                } else {
                    println("Server error. Status code: ${response.code()}")
                    dialog.dismiss()
                    showDialogFail()
                }
            }

            override fun onFailure(call: Call<UserPostResponse>, t: Throwable) {
                println("Request failed: ${t.message}")
                dialog.dismiss()
                showDialogFail()
            }
        })
    }
}
