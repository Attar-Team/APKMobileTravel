package com.example.rahmatantravel.Models

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import okhttp3.RequestBody

class SharedViewModel : ViewModel() {
    private var selectedImageUri: Uri? = null
    private var selectedImageKTP: Uri? = null
    private var selectedImageKartuKeluarga: Uri? = null
    private var selectedImageBukuRekening: Uri? = null
    private var selectedImageAkteKelahiran: Uri? = null
    private var selectedImageBukuPernikahan: Uri? = null
    private var selectedImagePaspor1: Uri? = null
    private var selectedImagePaspor2: Uri? = null
    private var selectedImageBuktiPembayaran: Uri? = null
    private var fotoProfile: Uri? = null

    fun getSelectedImageUri(): Uri? {
        return selectedImageUri
    }

    fun setSelectedImageUri(uri: Uri?) {
        selectedImageUri = uri
    }

    fun getSelectedImageKTP(): Uri? {
        return selectedImageKTP
    }

    fun setSelectedImageKTP(uri: Uri?) {
        selectedImageKTP = uri
    }

    fun getSelectedImageKartuKeluarga(): Uri? {
        return selectedImageKartuKeluarga
    }

    fun setSelectedImageKartuKeluarga(uri: Uri?) {
        selectedImageKartuKeluarga = uri
    }

    fun getSelectedImageBukuRekening(): Uri? {
        return selectedImageBukuRekening
    }

    fun setSelectedImageBukuRekening(uri: Uri?) {
        selectedImageBukuRekening = uri
    }

    fun getSelectedImageAkteKelahiran(): Uri? {
        return selectedImageAkteKelahiran
    }

    fun setSelectedImageAkteKelahiran(uri: Uri?) {
        selectedImageAkteKelahiran = uri
    }

    fun getSelectedImageBukuPernikahan(): Uri? {
        return selectedImageBukuPernikahan
    }

    fun setSelectedImageBukuPernikahan(uri: Uri?) {
        selectedImageBukuPernikahan = uri
    }

    fun getSelectedImagePaspor1(): Uri? {
        return selectedImagePaspor1
    }

    fun setSelectedImagePaspor1(uri: Uri?) {
        selectedImagePaspor1 = uri
    }

    fun getSelectedImagePaspor2(): Uri? {
        return selectedImagePaspor2
    }

    fun setSelectedImagePaspor2(uri: Uri?) {
        selectedImagePaspor2 = uri
    }

    fun getSelectedImageBuktiPembayaran(): Uri? {
        return selectedImageBuktiPembayaran
    }

    fun setSelectedImageBuktiPembayaran(uri: Uri?) {
        selectedImageBuktiPembayaran = uri
    }

    fun getProfileImageUri(): Uri? {
        return fotoProfile
    }

    fun setProfileImageUri(uri: Uri?) {
        fotoProfile = uri
        Log.d("SharedViewModel", "setProfileImageUri: $uri")
    }

}
