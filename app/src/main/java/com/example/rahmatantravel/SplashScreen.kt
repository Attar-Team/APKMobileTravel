package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.rahmatantravel.SharedPref.PrefManager

class SplashScreen : AppCompatActivity() {

    companion object {
        private const val SPLASH_TIMEOUT = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val prefManager = PrefManager(this)
        val loginData = prefManager.getLoginData()

        Handler().postDelayed({
            // Cek apakah data login ada
            if (loginData.first != null && loginData.second != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                // Data login tidak ada, tampilkan dialog login
                startActivity(Intent(this, LoginActivityRevisi::class.java))
                finish()
            }
        }, SPLASH_TIMEOUT)




    }
}