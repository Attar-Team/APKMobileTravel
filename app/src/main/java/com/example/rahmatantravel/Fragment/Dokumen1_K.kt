package com.example.rahmatantravel.Fragment

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
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.rahmatantravel.Models.SharedViewModel
import com.example.rahmatantravel.R
import com.example.rahmatantravel.api.UploadRequestBody
import com.google.android.material.textfield.TextInputLayout
import java.io.File
import java.util.Calendar

class Dokumen1_K : Fragment() {

    private lateinit var tvUploadImage: TextView
    private lateinit var nikLayout: TextInputLayout
    private lateinit var namaLayout: TextInputLayout
    private lateinit var tanggalLahirEditText: EditText
    private lateinit var tempatLahirLayout: TextInputLayout
    private lateinit var pekerjaanLayout: TextInputLayout
    private lateinit var alamatLayout: TextInputLayout
    private lateinit var buttonNext: CardView
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var radioGroupSize: RadioGroup
    private lateinit var imageView: ImageView

    private lateinit var sharedViewModel: SharedViewModel

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dokumen1, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Inisiasi Konten View
        tvUploadImage = view.findViewById(R.id.tvUploadImage)
        nikLayout = view.findViewById(R.id.TextFieldNIK)
        namaLayout = view.findViewById(R.id.TextFieldNama)
        tanggalLahirEditText = view.findViewById(R.id.TextFieldTanggalLahir)
        tempatLahirLayout = view.findViewById(R.id.TextFieldTempatLahir)
        pekerjaanLayout = view.findViewById(R.id.TextFieldPekerjaan)
        alamatLayout = view.findViewById(R.id.TextFieldAlamat)
        buttonNext = view.findViewById(R.id.btn_selanjutnya)
        radioGroupGender = view.findViewById(R.id.RadioGroupGender)
        radioGroupSize = view.findViewById(R.id.RadioGroupSize)
        imageView = view.findViewById(R.id.imgFotoProfil)

        // Inisiasi View Model
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val selectedImageUri = sharedViewModel.getSelectedImageUri() // Mengambil URI gambar yang dipilih

        if (selectedImageUri != null) {
            imageView.setImageURI(selectedImageUri) // Mengatur imageView menjadi gambar yang telah dipilih
        }

        val photo =
            selectedImageUri?.let { getFileName(it) }?.let { File(requireContext().cacheDir, it) } // Mengambil nama file gambar terpilih

        if (photo != null) {
            logData("NAMA FILE", photo.name.toString()) // Log data nama file gambar
        }

        tanggalLahirEditText.setOnClickListener {
            showDatePickerDialog()  // Menjalankan Fungsi datePicker
        }

        tvUploadImage.setOnClickListener {
            imagePicker()   // Menjalankan Fungsi imagePicker
        }

        buttonNext.setOnClickListener {
            if (isAllFieldsValid()) { // Memeriksa apakah semua field terisi

                val nik = nikLayout.editText?.text.toString()
                val nama = namaLayout.editText?.text.toString()
                val tempatLahir = tempatLahirLayout.editText?.text.toString()
                val tanggalLahir = "$selectedYear-${selectedMonth + 1}-$selectedDay"    // Mengubah data menjadi String untuk di kirimkan ke fragment selanjutnya
                val jenisKelamin = getSelectedRadioButtonText(radioGroupGender)
                val pekerjaan = pekerjaanLayout.editText?.text.toString()
                val alamat = alamatLayout.editText?.text.toString()
                val sizes = getSelectedRadioButtonText(radioGroupSize)

                val image = sharedViewModel.getSelectedImageUri()    // Mengambil gambar dari Shared ViewModel

                logData("Data NIK", nik)
                logData("Data Nama", nama)
                logData("Data Tanggal Lahir", tanggalLahir)
                logData("Data Tempat Lahir", tempatLahir)
                logData("Data Jenis Kelamin", jenisKelamin)  // Log data
                logData("Data Pekerjaan", pekerjaan)
                logData("Data Alamat", alamat)
                logData("Data Sizes", sizes)
                logData("Data Image Uri", image.toString())

                if (image != null) { // Pengecekan apakah terdapat gambar terpilih

                    showDialogCheck(nik, nama, tempatLahir, tanggalLahir,jenisKelamin, pekerjaan, alamat, sizes, image)  // Memanggil fungsi showDialogCheck

                } else {

                    Toast.makeText(requireContext(), "Mohon pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(requireContext(), "Mohon isi semua field", Toast.LENGTH_SHORT).show()
            }
        }

        return view
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
                tanggalLahirEditText.setText("$selectedYear-${selectedMonth + 1}-$selectedDay")
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun getSelectedRadioButtonText(radioGroup: RadioGroup): String {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        return if (selectedRadioButtonId != View.NO_ID) {
            view?.findViewById<RadioButton>(selectedRadioButtonId)?.text?.toString() ?: ""
        } else {
            ""
        }
    }

    private fun isAllFieldsValid(): Boolean {
        return !nikLayout.editText?.text.isNullOrBlank() &&
                !namaLayout.editText?.text.isNullOrBlank() &&
                !tempatLahirLayout.editText?.text.isNullOrBlank() &&
                radioGroupGender.checkedRadioButtonId != View.NO_ID &&
                !pekerjaanLayout.editText?.text.isNullOrBlank() &&
                !alamatLayout.editText?.text.isNullOrBlank() &&
                radioGroupSize.checkedRadioButtonId != View.NO_ID
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

    private fun imagePicker() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == AppCompatActivity.RESULT_OK) {
            data?.data?.let { uri ->
                sharedViewModel.setSelectedImageUri(uri)
                val imageView = view?.findViewById<ImageView>(R.id.imgFotoProfil)
                if (imageView != null) {
                    imageView.setImageURI(uri)
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_IMAGE = 101
    }

    private fun showDialogCheck(nik : String, name : String, tempatLahir : String, tanggalLahir : String, jeniskelamin : String, pekerjaan : String, alamat : String, sizes : String, imageUri : Uri?) {
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
            bundle.putString("nama", name)
            bundle.putString("tanggalLahir", tanggalLahir)
            bundle.putString("tempatLahir", tempatLahir)
            bundle.putString("jeniskelamin",jeniskelamin)
            bundle.putString("pekerjaan", pekerjaan)
            bundle.putString("alamat", alamat)
            bundle.putString("sizes", sizes)
            bundle.putParcelable("imageUri", imageUri)

            val Dokumen2 = Dokumen2_K()
            Dokumen2.arguments = bundle

            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, Dokumen2)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        val buttonRepeat = dialog.findViewById<Button>(R.id.btnRepeat)
        buttonRepeat.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
