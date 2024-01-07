package com.example.rahmatantravel;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.rahmatantravel.Fragment.Dokumen1_K
import com.example.rahmatantravel.Models.SharedViewModel
import com.example.rahmatantravel.SharedPref.PrefManager

class UbahProfile : AppCompatActivity() {

    private lateinit var image : ImageView
    private lateinit var btnImage : TextView
    private lateinit var nama : EditText
    private lateinit var btnUbah : CardView
    private lateinit var bttnBack : ImageView
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_profile)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        val prefManager = PrefManager(this)

        image = findViewById(R.id.imageProfile)
        btnImage = findViewById(R.id.tv_gantiProfile)
        nama = findViewById(R.id.et_Nama)
        btnUbah = findViewById(R.id.btn_save)
        bttnBack = findViewById(R.id.backToMenuLainnya)

        bttnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("selectedItemId", R.id.akun)
            startActivity(intent)
        }

        // Mengambil nilai nama dari SharedPreferences
        val savedNama = prefManager.getUserName()

        // Menetapkan nilai nama ke dalam EditText
        nama.setText(savedNama)

        btnImage.setOnClickListener {
            imagePicker()
        }

        btnUbah.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("selectedItemId", R.id.akun)
            startActivity(intent)
        }


    }
    private fun imagePicker() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, Dokumen1_K.REQUEST_CODE_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Dokumen1_K.REQUEST_CODE_IMAGE && resultCode == AppCompatActivity.RESULT_OK) {
            data?.data?.let { uri ->
                sharedViewModel.setProfileImageUri(uri)
                val imageView = findViewById<ImageView>(R.id.imageProfile)
                if (imageView != null) {
                    imageView.setImageURI(uri)
                }
            }
        }
    }
}
