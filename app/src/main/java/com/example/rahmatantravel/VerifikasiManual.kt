package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri
import android.os.Bundle;
import android.provider.OpenableColumns
import android.util.Log
import android.view.Window;
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView;
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.rahmatantravel.Fragment.Dokumen1_K
import com.example.rahmatantravel.Models.SharedViewModel
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.UploadRequestBody
import com.example.rahmatantravel.api.pemesananResponse.PemesananPostResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class VerifikasiManual : AppCompatActivity(), UploadRequestBody.UploadCallback {

    private lateinit var  NamaBank : EditText
    private lateinit var  NomorRekening : EditText
    private lateinit var  Nominal : EditText
    private lateinit var  CatatanPembayaran : EditText
    private lateinit var  tanggalPembayaran: TextView
    private lateinit var  uploadImage: TextView
    private lateinit var  buktiPembayaran: ImageView
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasi_manual)

        NamaBank = findViewById(R.id.edt_banktujuan)
        NomorRekening = findViewById(R.id.edt_norek)
        Nominal = findViewById(R.id.edt_nominal)
        tanggalPembayaran = findViewById(R.id.tglPembayaran)
        CatatanPembayaran = findViewById(R.id.edt_catatan)
        buktiPembayaran = findViewById(R.id.fotoBukti)
        uploadImage = findViewById(R.id.uploadFoto)

        uploadImage.setOnClickListener {
            imagePicker()
        }

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        val fotobukti = sharedViewModel.getSelectedImageBuktiPembayaran()
        buktiPembayaran.setImageURI(fotobukti)


        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date = dateFormat.format(Calendar.getInstance().time)
        tanggalPembayaran.text = date

        val total = intent.getIntExtra("totalHarga",0)
        logData("Total Harga", total.toString())
        Nominal.setText(formatCurrency(total))

        val selectedBank = intent.getStringExtra("Bank") ?.explode(",")
        logData("Selected Bank", selectedBank.toString())

        val ArrayNIK = intent.getStringArrayListExtra("NIK")
        val ArrayHargaID = intent.getStringArrayListExtra("IDHarga")

        logData("Array NIK", ArrayNIK.toString())
        logData("Array Harga ID", ArrayHargaID.toString())

        val NIK = ArrayNIK ?.toSet()
        val HargaID = ArrayHargaID ?.toSet()

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

        if (selectedBank != null && selectedBank.isNotEmpty()) {
            NamaBank.setText("${selectedBank[0]} A.T. ${selectedBank[1]}")
            NomorRekening.setText(selectedBank[2])
        }

        val btnSubmit = findViewById<CardView>(R.id.btn_submit)
        btnSubmit.setOnClickListener {

            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_loading)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.show()

            val fotobukti = sharedViewModel.getSelectedImageBuktiPembayaran()

            mapNIK?.let { map ->
                for ((key, value) in map) {
                    data["customer[$key]"] = RequestBody.create("text/plain".toMediaTypeOrNull(), value)
                }
            }

            mapIDHarga?.let { map ->
                for ((key, value) in map) {
                    data["harga[$key]"] = RequestBody.create("text/plain".toMediaTypeOrNull(), value.toString())
                }
            }

            data["agen_id"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "0")
            data["keberangkatan_id"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "6")
            data["catatan_pemesanan"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "noted")
            data["jenis_pembayaran"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "transfer")
            data["status"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "belum lunas")
            data["total_tagihan"] = RequestBody.create("text/plain".toMediaTypeOrNull(), (total ?: 0).toString())
            data["jumlah_bayar"] = RequestBody.create("text/plain".toMediaTypeOrNull(), (1000000).toString())
            data["catatan"] = RequestBody.create("text/plain".toMediaTypeOrNull(), CatatanPembayaran.text.toString())

            logData("Foto Bukti", fotobukti.toString())

            var part: MultipartBody.Part? = null

            if (fotobukti != null) {
                this.contentResolver.openFileDescriptor(fotobukti, "r", null)?.let { parcelFileDescriptor ->
                    val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
                    val file = File(this.cacheDir, getFileName(fotobukti))
                    val outputStream = FileOutputStream(file)
                    inputStream.copyTo(outputStream)

                    val photo = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                    part = MultipartBody.Part.createFormData("foto", file.name, photo)

                    logData("Part Image", part.toString())
                }
            }

            logData("Data", data.toString())

            RetrofitClient.instance.tambahPemesanan(data, part).enqueue(object :
                Callback<PemesananPostResponse> {
                override fun onResponse(
                    call: Call<PemesananPostResponse>,
                    response: Response<PemesananPostResponse>
                ) {
                    if (response.isSuccessful) {
                        val pemesananResponse = response.body()
                        if (pemesananResponse != null) {
                            logData("Response Success", pemesananResponse.message)
                            dialog.dismiss()
                            showDialogSuccess()
                        }else{
                            logData("Response body is null", response.message())
                            showDialogFail()
                        }
                    }else{
                        logData("Response Failed", response.message())
                        showDialogFail()
                    }
                }

                override fun onFailure(call: Call<PemesananPostResponse>, t: Throwable) {
                    logData("Response On Failure", t.message.toString())
                    showDialogFail()
                }

            })
        }


        val backToMenuLainnya = findViewById<ImageView>(R.id.backToMenuLainnya)
        backToMenuLainnya.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showDialogSuccess() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_order_success)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button = dialog.findViewById<CardView>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("selectedItemId", R.id.pesanan)
            startActivity(intent)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showDialogFail() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_save_fail)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button = dialog.findViewById<Button>(R.id.btnRepeat)

        button.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }


    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }

    fun String.explode(delimiter: String): List<String> {
        return this.split(delimiter)
    }
    private fun imagePicker() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, Dokumen1_K.REQUEST_CODE_IMAGE)
        }
    }

    private fun getFileName(selectedImageUri: Uri): String {
        var name = ""
        this.contentResolver.query(selectedImageUri, null, null, null, null)?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (it.moveToFirst()) {
                name = it.getString(nameIndex)
            }
        }
        return name
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Dokumen1_K.REQUEST_CODE_IMAGE && resultCode == AppCompatActivity.RESULT_OK) {
            data?.data?.let { uri ->
                sharedViewModel.setSelectedImageBuktiPembayaran(uri)
                val imageView = findViewById<ImageView>(R.id.fotoBukti)
                if (imageView != null) {
                    imageView.setImageURI(uri)
                }
            }
        }
    }

    override fun onProgressUpdate(percentage: Int) {
        Log.d("API Response", "Progress: $percentage")
    }

    fun formatCurrency(number: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID")) // Locale untuk Indonesia
        return format.format(number)
    }
}
