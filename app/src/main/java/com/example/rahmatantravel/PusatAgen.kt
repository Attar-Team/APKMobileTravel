package com.example.rahmatantravel

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.rahmatantravel.SharedPref.PrefManager

class PusatAgen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pusat_agen)

        val undangJamaah = findViewById<LinearLayout>(R.id.undangjamaah)
        val komisi = findViewById<LinearLayout>(R.id.komisi)
        val dompet = findViewById<LinearLayout>(R.id.dompet)

        val prefManager = PrefManager(this)

        prefManager.getUserName().let {
            findViewById<TextView>(R.id.NamaJamaah).text = it.toString()
        }

        undangJamaah.setOnClickListener {
            val intent = Intent(this, UndangJamaah::class.java)
            startActivity(intent)
        }

        komisi.setOnClickListener {
            val intent = Intent(this, DataJamaahAgen::class.java)
            startActivity(intent)
        }

        dompet.setOnClickListener {
            val intent = Intent(this, Dompet::class.java)
            startActivity(intent)
        }

    }
}