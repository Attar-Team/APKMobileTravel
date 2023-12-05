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

            val geoUri = "https://maps.app.goo.gl/ezRvpGWGfEvpGbbZA"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            startActivity(intent)
        }

        backToMenuLainnya.setOnClickListener {
            onBackPressed()
        }
    }
}
