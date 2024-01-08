package com.example.rahmatantravel;

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.cardview.widget.CardView

class KiblatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kiblat)

        val backToMenuLainnya = findViewById<ImageView>(R.id.backToMenuLainnya)
        val buttonLanjut = findViewById<CardView>(R.id.buttonLanjut)

        buttonLanjut.setOnClickListener {

            val intent = Intent(this, KiblatActivity2::class.java)
            startActivity(intent)
        }

        backToMenuLainnya.setOnClickListener {
            onBackPressed()
        }
    }
}
