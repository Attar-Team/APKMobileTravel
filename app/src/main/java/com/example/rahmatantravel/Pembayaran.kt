package com.example.rahmatantravel;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.rahmatantravel.Models.SharedViewModel
import okhttp3.RequestBody
import java.text.NumberFormat
import java.util.Locale
import okhttp3.MediaType.Companion.toMediaTypeOrNull


class Pembayaran : AppCompatActivity() {

    private lateinit var totalHarga : TextView
    private lateinit var btnSelanjutnya: CardView
    private lateinit var backToMenuLainnya: ImageView
    private lateinit var radioGroupBank : RadioGroup
    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        var selectedValue: String = ""
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        totalHarga = findViewById(R.id.hargaPaket)
        btnSelanjutnya = findViewById(R.id.btn_selanjutnya)
        backToMenuLainnya = findViewById(R.id.backToMenuLainnya)
        radioGroupBank = findViewById(R.id.radioGroupBank)

        radioGroupBank.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton.tag != null && selectedRadioButton.tag.toString().isNotEmpty()) {
                selectedValue = selectedRadioButton.tag.toString()
                logData("Radio Tag", selectedValue)
            }
        }

        backToMenuLainnya.setOnClickListener {
            onBackPressed()
        }

        val total = intent.getStringExtra("totalHarga") ?.toInt()
        totalHarga.text = formatCurrency(total ?: 0)

        val extras = intent.extras
        val NamaNIK = extras?.keySet()?.filter { it.startsWith("NamaNIK_") }?.associateWith { extras.getString(it) }?.mapKeys { it.key.substring("NamaNIK_".length) }
        val IDHarga = extras?.keySet()?.filter { it.startsWith("IDHarga_") }?.associateWith { extras.getString(it)?.toInt() }?.mapKeys { it.key.substring("IDHarga_".length) }

        logData("NamaNIK", NamaNIK.toString())
        logData("NamaNIK", NamaNIK ?.keys.toString())
        logData("NamaNIk", NamaNIK ?.values.toString())
        logData("IDHarga", IDHarga.toString())
        logData("IDHarga", IDHarga ?.keys.toString())
        logData("IDHarga", IDHarga ?.values.toString())

        val NIK : Set<String>? = NamaNIK?.keys
        val HargaID : Set<String>? = IDHarga?.keys
        val ArrayNIK = NIK?.let { ArrayList(it) }
        val ArrayHargaID = HargaID?.let { ArrayList(it) }

        logData("Array NIK", ArrayNIK.toString())
        logData("Array Harga ID", ArrayHargaID.toString())

        val mapNIK = NIK ?.map { it } ?.fold(mutableMapOf<String, String>()) { map, nik ->
            map.put("profil${map.size + 1}", nik)
            map
        }
        logData("MAP NIK", mapNIK.toString())

        val mapIDHarga = HargaID ?.map { it } ?.fold(mutableMapOf<String, String>()) { map, id ->
            map.put("profil${map.size + 1}", id)
            map
        }
        logData("MAP ID Harga", mapIDHarga.toString())

        val data = LinkedHashMap<String, RequestBody>()

        for ((key, value) in mapNIK!!) {
            data["customer[$key]"] = RequestBody.create("text/plain".toMediaTypeOrNull(), value)
        }

        for ((key, value) in mapIDHarga!!) {
            data["harga[$key]"] = RequestBody.create("text/plain".toMediaTypeOrNull(), value.toString())
        }

        data["agen_id"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "0")
        data["keberangkatan_id"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "6")
        data["catatan_pemesanan"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "noted kak")
        data["jenis_pembayaran"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "transfer")
        data["status"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "belum lunas")
        data["total_tagihan"] = RequestBody.create("text/plain".toMediaTypeOrNull(), (total ?: 0).toString())

        val kodereferal = intent.getStringExtra("KodeReferal")
        val catatanPemesanan = intent.getStringExtra("CatatanPemesanan")
        logData("Kode Referal", kodereferal.toString())
        logData("Catatan Pemesanan", catatanPemesanan.toString())


        btnSelanjutnya.setOnClickListener {

            logData("data", data.toString())

            val intent = Intent(this@Pembayaran, VerifikasiManual::class.java)
            intent.putExtra("totalHarga", total)
            intent.putExtra("NIK", ArrayNIK)
            intent.putExtra("IDHarga", ArrayHargaID)
            intent.putExtra("Bank", selectedValue)
            startActivity(intent)
        }

    }

    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }

    fun String.explode(delimiter: String): List<String> {
        return this.split(delimiter)
    }
    fun formatCurrency(number: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID")) // Locale untuk Indonesia
        return format.format(number)
    }
}
