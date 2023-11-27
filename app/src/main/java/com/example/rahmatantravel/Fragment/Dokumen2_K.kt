package com.example.rahmatantravel.Fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.rahmatantravel.Models.SharedViewModel
import com.example.rahmatantravel.R

class Dokumen2_K : Fragment() {

    private val REQUEST_CODE_IMAGE = 100

    private var selectedImageKK: Uri? = null
    private var selectedImageKTP: Uri? = null
    private var selectedImageRekening: Uri? = null
    private var selectedImageAkte: Uri? = null
    private var selectedImageBukuNikah: Uri? = null

    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var imgKTP : ImageView
    private lateinit var imgKK : ImageView
    private lateinit var imgRekening : ImageView
    private lateinit var imgAkte : ImageView
    private lateinit var imgBukuNikah : ImageView

    private lateinit var UploadImageKTP: LinearLayout
    private lateinit var UploadImageKK: LinearLayout
    private lateinit var UploadImageRekening: LinearLayout
    private lateinit var UploadImageAkte: LinearLayout
    private lateinit var UploadImageBukuNikah: LinearLayout
    private lateinit var buttonNext: CardView
    private lateinit var buttonBack: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dokumen2, container, false)

        // Inisiasi SharedViewModel
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Inisiasi Konten View
        UploadImageKTP = view.findViewById(R.id.uploadImageKTP)
        UploadImageKK = view.findViewById(R.id.uploadImageKK)
        UploadImageRekening = view.findViewById(R.id.uploadImageRekening)
        UploadImageAkte = view.findViewById(R.id.uploadImageAkte)
        UploadImageBukuNikah = view.findViewById(R.id.uploadImageBukuNikah)
        buttonNext = view.findViewById(R.id.btn_selanjutnya)
        buttonBack = view.findViewById(R.id.btn_back)
        imgKTP = view.findViewById(R.id.imgKTP)
        imgKK = view.findViewById(R.id.imgKK)
        imgRekening = view.findViewById(R.id.imgRekening)
        imgAkte = view.findViewById(R.id.imgAkte)
        imgBukuNikah = view.findViewById(R.id.imgBukuPernikahan)


        // Mengambil data uri gambar dari SharedViewModel
        val selectedImageKTP = sharedViewModel.getSelectedImageKTP()
        val selectedImageKK = sharedViewModel.getSelectedImageKartuKeluarga()
        val selectedImageRekening = sharedViewModel.getSelectedImageBukuRekening()
        val selectedImageAkte = sharedViewModel.getSelectedImageAkteKelahiran()
        val selectedImageBukuNikah = sharedViewModel.getSelectedImageBukuPernikahan()

        // Mengatur imageView menjadi gambar yang telah dipilih
        imgKTP.setImageURI(selectedImageKTP)
        imgKK.setImageURI(selectedImageKK)
        imgRekening.setImageURI(selectedImageRekening)
        imgAkte.setImageURI(selectedImageAkte)
        imgBukuNikah.setImageURI(selectedImageBukuNikah)

        UploadImageKTP.setOnClickListener {
            imagePicker(REQUEST_CODE_IMAGE)  //Menjalankan fungsi imagePicker
        }

        UploadImageKK.setOnClickListener {
            imagePicker(REQUEST_CODE_IMAGE + 1)  //Menjalankan fungsi imagePicker
        }

        UploadImageRekening.setOnClickListener {
            imagePicker(REQUEST_CODE_IMAGE + 2)  //Menjalankan fungsi imagePicker
        }

        UploadImageAkte.setOnClickListener {
            imagePicker(REQUEST_CODE_IMAGE + 3)  //Menjalankan fungsi imagePicker
        }

        UploadImageBukuNikah.setOnClickListener {
            imagePicker(REQUEST_CODE_IMAGE + 4)  //Menjalankan fungsi imagePicker
        }

        // Mengambil data bundle dari Fragment Sebelumnya
        val TEST:String? = arguments?.getString("TEST")
        val NIK: String? = arguments?.getString("nik")
        val NamaLengkap: String? = arguments?.getString("nama")
        val TanggalLahir: String? = arguments?.getString("tanggalLahir")
        val TempatLahir: String? = arguments?.getString("tempatLahir")
        val JenisKelamin: String? = arguments?.getString("jeniskelamin")
        val Pekerjaan: String? = arguments?.getString("pekerjaan")
        val Alamat: String? = arguments?.getString("alamat")
        val Sizes: String? = arguments?.getString("sizes")
        val imageUri: Uri? = arguments?.getParcelable("imageUri")

        //Log data
        logData("TEST", TEST.toString())
        logData("Data NIK", NIK.toString())
        logData("Data Nama", NamaLengkap.toString())
        logData("Data Tanggal Lahir", TanggalLahir.toString())
        logData("Data Tempat Lahir", TempatLahir.toString())
        logData("Data Jenis Kelamin", JenisKelamin.toString())
        logData("Data Pekerjaan", Pekerjaan.toString())
        logData("Data Alamat", Alamat.toString())
        logData("Data Sizes", Sizes.toString())
        logData("Data imageUri", imageUri.toString())

        buttonNext.setOnClickListener {

            // Mengambil data uri gambar dari SharedViewModel
            val selectedImageKTP = sharedViewModel.getSelectedImageKTP()
            val selectedImageKK = sharedViewModel.getSelectedImageKartuKeluarga()
            val selectedImageRekening = sharedViewModel.getSelectedImageBukuRekening()
            val selectedImageAkte = sharedViewModel.getSelectedImageAkteKelahiran()
            val selectedImageBukuNikah = sharedViewModel.getSelectedImageBukuPernikahan()

            //Log data
            logData("TEST", TEST.toString())
            logData("Data NIK", NIK.toString())
            logData("Data Nama", NamaLengkap.toString())
            logData("Data Tanggal Lahir", TanggalLahir.toString())
            logData("Data Tempat Lahir", TempatLahir.toString())
            logData("Data Jenis Kelamin", JenisKelamin.toString())
            logData("Data Pekerjaan", Pekerjaan.toString())
            logData("Data Alamat", Alamat.toString())
            logData("Data Sizes", Sizes.toString())
            logData("Data imageUri", imageUri.toString())
            logData("Data KTP",  selectedImageKTP.toString())
            logData("Data KK",   selectedImageKK.toString())
            logData("Data Rekening",   selectedImageRekening.toString())
            logData("Data Akte",   selectedImageAkte.toString())
            logData("Data Buku Nikah",   selectedImageBukuNikah.toString())

            showDialogCheck(NIK, NamaLengkap, TanggalLahir, TempatLahir, JenisKelamin, Pekerjaan, Alamat, Sizes, imageUri,
                selectedImageKTP, selectedImageKK, selectedImageRekening, selectedImageAkte, selectedImageBukuNikah)       //Menjalankan fungsi showDialogCheck
        }

        buttonBack.setOnClickListener {
            onBackPressed()
        }

        return view
    }

    private fun imagePicker(requestCode: Int) {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, requestCode)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            data?.data?.let { uri ->
                when (requestCode) {
                    REQUEST_CODE_IMAGE -> {
                        selectedImageKTP = uri
                        sharedViewModel.setSelectedImageKTP(uri)
                        updateImageView(
                            linearLayout = UploadImageKTP,
                            uri = uri,
                            imageViewId = R.id.imgKTP
                        )
                    }

                    REQUEST_CODE_IMAGE + 1 -> {
                        selectedImageKK = uri
                        sharedViewModel.setSelectedImageKartuKeluarga(uri)
                        updateImageView(
                            linearLayout = UploadImageKK,
                            uri = uri,
                            imageViewId = R.id.imgKK
                        )
                    }

                    REQUEST_CODE_IMAGE + 2 -> {
                        selectedImageRekening = uri
                        sharedViewModel.setSelectedImageBukuRekening(uri)
                        updateImageView(
                            linearLayout = UploadImageRekening,
                            uri = uri,
                            imageViewId = R.id.imgRekening
                        )
                    }

                    REQUEST_CODE_IMAGE + 3 -> {
                        selectedImageAkte = uri
                        sharedViewModel.setSelectedImageAkteKelahiran(uri)
                        updateImageView(
                            linearLayout = UploadImageAkte,
                            uri = uri,
                            imageViewId = R.id.imgAkte
                        )
                    }

                    REQUEST_CODE_IMAGE + 4 -> {
                        selectedImageBukuNikah = uri
                        sharedViewModel.setSelectedImageBukuPernikahan(uri)
                        updateImageView(
                            linearLayout = UploadImageBukuNikah,
                            uri = uri,
                            imageViewId = R.id.imgBukuPernikahan
                        )
                    }
                }
            }
        }
    }

    private fun updateImageView(linearLayout: LinearLayout, uri: Uri, imageViewId: Int) {
        val imageView = linearLayout.findViewById<ImageView>(imageViewId)
        imageView?.setImageURI(uri)
    }
    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }

    private fun onBackPressed() {
        parentFragmentManager.popBackStack()
    }

    private fun showDialogCheck(nik: String?, nama: String?, tglLahir: String?, tempatLahir: String?, jk: String?, pekerjaan: String?, alamat: String?, sizes: String?, imageUri: Uri?,
    selectedImageKTP: Uri?, selectedImageKK: Uri?, selectedImageRekening: Uri?, selectedImageAkte: Uri?, selectedImageBukuNikah: Uri?) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_data_checkl)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val buttonNext = dialog.findViewById<Button>(R.id.btnNext)
        buttonNext.setOnClickListener {
            dialog.dismiss()
            val bundle = Bundle()
            bundle.putString("TEST", "TEST")
            bundle.putString("nik", nik)
            bundle.putString("nama", nama)
            bundle.putString("tanggalLahir", tglLahir)
            bundle.putString("tempatLahir", tempatLahir)
            bundle.putString("jeniskelamin", jk)
            bundle.putString("pekerjaan", pekerjaan)
            bundle.putString("alamat", alamat)
            bundle.putString("sizes", sizes)
            bundle.putParcelable("imageUri", imageUri)
            bundle.putParcelable("KTP", selectedImageKTP)
            bundle.putParcelable("KK", selectedImageKK)
            bundle.putParcelable("Rekening", selectedImageRekening)
            bundle.putParcelable("Akte", selectedImageAkte)
            bundle.putParcelable("Buku Nikah", selectedImageBukuNikah)

            val fragment = Dokumen3_K()
            fragment.arguments = bundle
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit()
        }

        val buttonRepeat = dialog.findViewById<Button>(R.id.btnRepeat)
        buttonRepeat.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}