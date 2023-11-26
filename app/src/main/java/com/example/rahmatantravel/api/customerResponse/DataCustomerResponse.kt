package com.example.rahmatantravel.api.customerResponse

import com.google.gson.annotations.SerializedName

data class DataCustomerResponse (
    @SerializedName("NIK") val nik: String,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("nama") val nama: String,
    @SerializedName("tempat_lahir") val tempatLahir: String,
    @SerializedName("tanggal_lahir") val tanggalLahir: String,
    @SerializedName("alamat") val alamat: String,
    @SerializedName("jenis_kelamin") val jenisKelamin: String,
    @SerializedName("pekerjaan") val pekerjaan: String,
    @SerializedName("ukuran_baju") val ukuranBaju: String,
    @SerializedName("no_telp") val noTelp: String,
    @SerializedName("nomor_pasport") val nomorPasport: String,
    @SerializedName("nama_pasport") val namaPasport: String,
    @SerializedName("tempat_penerbita") val tempatPenerbita: String,
    @SerializedName("tgl_penerbitan") val tglPenerbitan: String,
    @SerializedName("foto_customer") val fotoCustomer: String,
    @SerializedName("foto_pasport") val fotoPasport: String,
    @SerializedName("foto_pasport2") val fotoPasport2: String,
    @SerializedName("foto_ktp") val fotoKTP: String,
    @SerializedName("foto_kk") val fotoKK: String,
    @SerializedName("foto_rekening") val fotoRekening: String,
    @SerializedName("foto_akte") val fotoAkte: String,
    @SerializedName("foto_pernikahan") val fotoPernikahan: String
)