package com.example.rahmatantravel;

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class UbahKataSandi : AppCompatActivity() {

    private lateinit var backToMenuLainnya: ImageView
    private lateinit var buttonSave : CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_kata_sandi)

        backToMenuLainnya = findViewById(R.id.backToMenuLainnya)
        backToMenuLainnya.setOnClickListener { onBackPressed() }

        buttonSave = findViewById(R.id.btn_save)
        buttonSave.setOnClickListener { onBackPressed() }
    }
}
