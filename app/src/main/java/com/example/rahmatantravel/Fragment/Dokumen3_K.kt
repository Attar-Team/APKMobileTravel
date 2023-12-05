package com.example.rahmatantravel.Fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.rahmatantravel.DataJamaah
import com.example.rahmatantravel.Dokumen
import com.example.rahmatantravel.Models.SharedViewModel
import com.example.rahmatantravel.R
import com.example.rahmatantravel.SharedPref.PrefManager
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.UploadRequestBody
import com.example.rahmatantravel.api.customerResponse.CustomerData
import com.example.rahmatantravel.api.customerResponse.CustomerResponse
import com.example.rahmatantravel.api.customerResponse.PhotoResponse
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Calendar
import kotlin.math.log


class Dokumen3_K : Fragment(), UploadRequestBody.UploadCallback {

    private val REQUEST_CODE_IMAGE = 100

    private var paspor : Uri? = null
    private var paspor2 : Uri? = null

    private lateinit var uploadImagePasport1 : CardView
    private lateinit var uploadImagePasport2 : CardView

    private lateinit var imgPaspor1: ImageView
    private lateinit var imgPaspor2: ImageView

    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var LayoutTanggalPenerbitanPasport : EditText

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0

    @SuppressLint("Recycle")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_dokumen3, container, false)

        // Inisiasi Konten View
        val LayoutNomorPasport = view.findViewById<TextInputLayout>(R.id.TextFieldNomorPasport)
        val LayoutNamaPasport = view.findViewById<TextInputLayout>(R.id.TextFieldNamaPasport)
        val LayoutTempatPenerbitanPasport = view.findViewById<TextInputLayout>(R.id.TextFieldTempatPenerbitan)
        LayoutTanggalPenerbitanPasport = view.findViewById(R.id.TextFieldTanggalPaspor)
        val Simpan = view.findViewById<CardView>(R.id.btn_selanjutnya)
        val Back = view.findViewById<CardView>(R.id.btn_back)
        uploadImagePasport1 = view.findViewById(R.id.uploadImagePasport1)
        uploadImagePasport2 = view.findViewById(R.id.uploadImagePasport2)
        imgPaspor1 = view.findViewById(R.id.imgPasport)
        imgPaspor2 = view.findViewById(R.id.imgPasport2)

        // Inisiasi Shared ViewModel
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Mengambil Data uri dari Shared ViewModel
        val selectedImagePaspor1 = sharedViewModel.getSelectedImagePaspor1()
        val selectedImagePaspor2 = sharedViewModel.getSelectedImagePaspor2()

        // Mengubah image view sesuai dengan gambar yang dipilih
        imgPaspor1.setImageURI(selectedImagePaspor1)
        imgPaspor2.setImageURI(selectedImagePaspor2)

        // Mengambil data bundle dari fargment sebelumnya
        val NIK: String? = arguments?.getString("nik")
        val NamaLengkap: String? = arguments?.getString("nama")
        val TanggalLahir: String? = arguments?.getString("tanggalLahir")
        val TempatLahir: String? = arguments?.getString("tempatLahir")
        val JenisKelamin: String? = arguments?.getString("jeniskelamin")
        val Pekerjaan: String? = arguments?.getString("pekerjaan")
        val Alamat: String? = arguments?.getString("alamat")
        val Sizes: String? = arguments?.getString("sizes")
        val customer: Uri? = arguments?.getParcelable("imageUri")
        val ktp : Uri? = arguments?.getParcelable("KTP")
        val keluarga : Uri? = arguments?.getParcelable("KK")
        val rekening : Uri? = arguments?.getParcelable("Rekening")
        val akte : Uri? = arguments?.getParcelable("Akte")
        val pernikahan : Uri? = arguments?.getParcelable("Buku Nikah")

        // Pengecekan ketersediaan data
        if (NIK != null && NamaLengkap != null && TanggalLahir != null &&
            TempatLahir != null && JenisKelamin != null && Pekerjaan != null &&
            Alamat != null && Sizes != null && customer != null) {

            // Log Data
            logData("Data NIK", NIK.toString())
            logData("Data Nama", NamaLengkap.toString())
            logData("Data Tanggal Lahir", TanggalLahir.toString())
            logData("Data Tempat Lahir", TempatLahir.toString())
            logData("Data Jenis Kelamin", JenisKelamin.toString())
            logData("Data Pekerjaan", Pekerjaan.toString())
            logData("Data Alamat", Alamat.toString())
            logData("Data Sizes", Sizes.toString())
            logData("Data imageUri", customer.toString())
            logData("KTP",  ktp.toString())
            logData("KK",   keluarga.toString())
            logData("Rekening",   rekening.toString())
            logData("Akte",   akte.toString())
            logData("Buku Nikah",   pernikahan.toString())

            uploadImagePasport1.setOnClickListener {
                imagePicker(uploadImagePasport1, REQUEST_CODE_IMAGE)  // Menjalankan fungsi Image Picker
            }
            uploadImagePasport2.setOnClickListener {
                imagePicker(uploadImagePasport2, REQUEST_CODE_IMAGE + 1)  // Menjalankan fungsi Image Picker
            }

            LayoutTanggalPenerbitanPasport.setOnClickListener {
                showDatePickerDialog()
            }

            Simpan.setOnClickListener {

                // Log Data
                logData("Button", "Button Clicked")
                logData("Data NIK", NIK.toString())
                logData("Data Nama", NamaLengkap.toString())
                logData("Data Tanggal Lahir", TanggalLahir.toString())
                logData("Data Tempat Lahir", TempatLahir.toString())
                logData("Data Jenis Kelamin", JenisKelamin.toString())
                logData("Data Pekerjaan", Pekerjaan.toString())
                logData("Data Alamat", Alamat.toString())
                logData("Data Sizes", Sizes.toString())
                logData("Data imageUri", customer.toString())
                logData("KTP", ktp.toString())
                logData("KK", keluarga.toString())
                logData("Rekening", rekening.toString())
                logData("Akte", akte.toString())
                logData("Buku Nikah", pernikahan.toString())
                logData("Paport1", paspor.toString())
                logData("Paport2", paspor2.toString())
                logData("Nomor Pasport", LayoutNomorPasport.editText?.text.toString())
                logData("Nama Pasport", LayoutNamaPasport.editText?.text.toString())
                logData(
                    "Tempat Penerbitan Pasport",
                    LayoutTempatPenerbitanPasport.editText?.text.toString()
                )
                logData(
                    "Tanggal Penerbitan Pasport",
                    LayoutTanggalPenerbitanPasport.text.toString()
                )

                // Mendapatkan teks dari EditText TanggalPenerbitanPasport
                val tanggalPenerbitanPasporText = LayoutTanggalPenerbitanPasport.text.toString()

                // Memilih tanggal yang akan dimasukkan ke dalam customerData
                val tanggalPenerbitanPaspor =
                    if (tanggalPenerbitanPasporText.isNotBlank()) {
                        tanggalPenerbitanPasporText
                    } else {
                        "1990-12-12"
                    }

                val prefManager = PrefManager(requireContext())
                val loginData = prefManager.getLoginData()

                val userID = loginData.first ?.toInt()
                logData("User ID", userID.toString())

                val customerData = CustomerData(
                    NIK = NIK,
                    user_id = "$userID",
                    NamaLengkap = NamaLengkap,
                    TempatLahir = TempatLahir,
                    TanggalLahir = TanggalLahir,
                    Alamat = Alamat,
                    JenisKelamin = JenisKelamin,
                    Pekerjaan = Pekerjaan,
                    Sizes = Sizes,
                    NoTelp = "0888888888",
                    NomorPasport = LayoutNomorPasport.editText?.text.toString(),
                    NamaPasport = LayoutNamaPasport.editText?.text.toString(),
                    TempatPenerbitanPasport = LayoutTempatPenerbitanPasport.editText?.text.toString(),
                    TanggalPenerbitanPasport = tanggalPenerbitanPaspor
                )


                // Inisiasi Map untuk hasil pemerosesan gambar menjadi MultipartBody.Part
                val processedData = mutableMapOf<String, MultipartBody.Part>()

                // Memasukkan data gamber kedalam Map
                val data = mapOf(
                    "customer" to customer,
                    "ktp" to ktp,
                    "keluarga" to keluarga,
                    "rekening" to rekening,
                    "akte" to akte,
                    "pernikahan" to pernikahan,
                    "paspor" to paspor,
                    "paspor2" to paspor2
                )

                // Proses konversi data dari URI menjadi MultipartBody.Part
                for ((key, value) in data) {
                    println("$key -> $value")

                    // Pemeriksaan null untuk URI
                    if (value != null) {
                        requireActivity().contentResolver.openFileDescriptor(value, "r", null)?.let { parcelFileDescriptor ->
                            val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
                            val file = File(requireContext().cacheDir, getFileName(value))
                            Log.d("File Info", "File Name: ${file.name}, File Path: ${file.absolutePath}")

                            val outputStream = FileOutputStream(file)
                            inputStream.copyTo(outputStream)

                            val body = UploadRequestBody(file, "image", this)
                            val photo = MultipartBody.Part.createFormData("foto[$key]", file.name, body)

                            // Memasukkan gambar yang telah di proses kedalam Map
                            processedData[key] = photo
                            logData("Photo", photo.toString())
                        }
                    } else {
                        // Proses jika URI kosong
                        Log.d("Photo Info", "URI for $key is null or empty.")
                        // Tambahkan form data kosong ke processedData
                        processedData[key] = MultipartBody.Part.createFormData("foto[$key]", "")
                    }
                }

                // Mengambil data hasil pemerosesan gambar
                val profilePhoto  = processedData["customer"]
                val ktpPhoto  = processedData["ktp"]
                val keluargaPhoto  = processedData["keluarga"]
                val rekeningPhoto  = processedData["rekening"]
                val aktePhoto  = processedData["akte"]
                val pernikahanPhoto  = processedData["pernikahan"]
                val pasporPhoto  = processedData["paspor"]
                val paspor2Photo  = processedData["paspor2"]

                // Log Data
                logData("Data NIK", customerData.NIK)
                logData("Data User ID", customerData.user_id)
                logData("Data Nama", customerData.NamaLengkap)
                logData("Data Tempat Lahir", customerData.TempatLahir)
                logData("Data Tanggal Lahir", customerData.TanggalLahir)
                logData("Data Alamat", customerData.Alamat)
                logData("Data Jenis Kelamin", customerData.JenisKelamin)
                logData("Data Pekerjaan", customerData.Pekerjaan)
                logData("Data Sizes", customerData.Sizes)
                logData("Data No Telp", customerData.NoTelp)
                logData("Data Nomor Pasport", customerData.NomorPasport)
                logData("Data Nama Pasport", customerData.NamaPasport)
                logData("Data Tempat Penerbitan Pasport", customerData.TempatPenerbitanPasport)
                logData("Data Tanggal Penerbitan Pasport", customerData.TanggalPenerbitanPasport)
                logData("Data Poto Profile", profilePhoto.toString())
                logData("Data Poto KTP", ktpPhoto.toString())
                logData("Data Poto KK", keluargaPhoto.toString())
                logData("Data Poto Rekening", rekeningPhoto.toString())
                logData("Data Poto Akte", aktePhoto.toString())
                logData("Data Poto Pernikahan", pernikahanPhoto.toString())
                logData("Data Poto Pasport", pasporPhoto.toString())
                logData("Data Poto Pasport 2", paspor2Photo.toString())

                // Menjalankan fungsi showDialogCheck
                showDialogCheck(customerData.NIK, customerData.user_id, customerData.NamaLengkap, customerData.TanggalLahir, customerData.TempatLahir, customerData.JenisKelamin,
                    customerData.Pekerjaan, customerData.Alamat, customerData.Sizes, profilePhoto, ktpPhoto, keluargaPhoto, rekeningPhoto, aktePhoto,
                    pernikahanPhoto, pasporPhoto, paspor2Photo, customerData.NomorPasport, customerData.NamaPasport, customerData.TempatPenerbitanPasport, customerData.TanggalPenerbitanPasport)

            }

        } else {
            logData("Data Bundle", "Data Bundle Kosong")
        }
        Back.setOnClickListener {
            onBackPressed()
        }

        return view
    }
    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }

    private fun getFileName(selectedImageUri: Uri): String {
        var name = ""
        requireActivity().contentResolver.query(selectedImageUri, null, null, null, null)?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (it.moveToFirst()) {
                name = it.getString(nameIndex)
            }
        }
        return name
    }


    private fun imagePicker(linearLayout: CardView, requestCode: Int) {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, requestCode)
        }
    }

    private fun updateImageView(linearLayout: CardView, uri: Uri, imageViewId: Int) {
        val imageView = linearLayout.findViewById<ImageView>(imageViewId)
        imageView?.setImageURI(uri)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            data?.data?.let { uri ->
                when (requestCode) {
                    REQUEST_CODE_IMAGE -> {
                        paspor = uri
                        sharedViewModel.setSelectedImagePaspor1(uri)
                        updateImageView(
                            linearLayout = uploadImagePasport1,
                            uri = uri,
                            imageViewId = R.id.imgPasport
                        )
                    }

                    REQUEST_CODE_IMAGE + 1 -> {
                        paspor2 = uri
                        sharedViewModel.setSelectedImagePaspor2(uri)
                        updateImageView(
                            linearLayout = uploadImagePasport2,
                            uri = uri,
                            imageViewId = R.id.imgPasport2
                        )
                    }
                }
            }
        }
    }

    override fun onProgressUpdate(percentage: Int) {
        Log.d("API Response", "Progress: $percentage")
    }
    fun onBackPressed() {
        parentFragmentManager.popBackStack()
    }

    private fun showDialogSuccess() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_save_success)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button = dialog.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(requireContext(), DataJamaah::class.java)
            startActivity(intent)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showDialogFail() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_save_fail)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button = dialog.findViewById<Button>(R.id.btnRepeat)

        button.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }

    private fun showDialogCheck(nik: String, userId : String, nama: String, tglLahir: String, tempatLahir: String, jk: String, pekerjaan: String, alamat: String, sizes: String, imageUri: MultipartBody.Part?,
                selectedImageKTP: MultipartBody.Part?, selectedImageKK: MultipartBody.Part?, selectedImageRekening: MultipartBody.Part?, selectedImageAkte: MultipartBody.Part?, selectedImageBukuNikah: MultipartBody.Part?,
                                paspor1: MultipartBody.Part?, paspor2: MultipartBody.Part?, nomorPaspor: String, namaPaspor: String, tempatTerbitPaspor: String, tanggalTerbitPaspor: String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_data_checkl)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val Dialog = Dialog(requireContext())
        Dialog.setContentView(R.layout.dialog_loading)
        Dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Dialog.setCancelable(false)
        Dialog.show()


        val buttonNext = dialog.findViewById<Button>(R.id.btnNext)
        buttonNext.setOnClickListener {
            dialog.dismiss()

            RetrofitClient.instance.tambahProfile(
                NIK = nik,
                user_id = userId,
                NamaLengkap = nama,
                TempatLahir = tempatLahir,
                TanggalLahir = tglLahir,
                Alamat = alamat,
                JenisKelamin = jk,
                Pekerjaan = pekerjaan,
                Sizes = sizes,
                NoTelp = "08123456789",
                NomorPasport = nomorPaspor,
                NamaPasport = namaPaspor,
                TempatPenerbitanPasport = tempatTerbitPaspor,
                TanggalPenerbitanPasport = tanggalTerbitPaspor,
                FotoCustomer = imageUri,
                FotoPasport = paspor1,
                FotoPasport2 = paspor2,
                FotoKTP = selectedImageKTP,
                FotoKK = selectedImageKK,
                FotoRekening = selectedImageRekening,
                FotoAkte = selectedImageAkte,
                FotoBukuNikah = selectedImageBukuNikah
            ).enqueue(object : Callback<PhotoResponse> {
                override fun onResponse(
                    call: Call<PhotoResponse>,
                    response: Response<PhotoResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            logData("Response Success", responseBody.message)
                            logData("Response Info", responseBody.info)

                            Dialog.dismiss()
                            showDialogSuccess()
                        }else{
                            logData("Response Body Null", response.message())

                            Dialog.dismiss()
                            showDialogFail()
                        }
                    }else{
                        logData("Response Failed", response.message().toString())

                        Dialog.dismiss()
                        showDialogSuccess()
                    }
                }

                override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                    logData("Response on Failure", t.message.toString())

                    Dialog.dismiss()
                    showDialogFail()
                }
            })
        }

        val buttonRepeat = dialog.findViewById<Button>(R.id.btnRepeat)
        buttonRepeat.setOnClickListener {
            dialog.dismiss()
            Dialog.dismiss()
        }
            dialog.show()

    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                selectedYear = year
                selectedMonth = month
                selectedDay = day
                LayoutTanggalPenerbitanPasport.setText("$selectedYear-${selectedMonth + 1}-$selectedDay")
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

}