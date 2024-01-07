package com.example.rahmatantravel.api

import com.google.gson.annotations.SerializedName

data class PesananAgenResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Pemesanan>
)

data class Pemesanan(
    @SerializedName("pemesanan_id")
    val pemesananId: String,
    @SerializedName("nama_customer")
    val namaCustomer: String?,
    @SerializedName("status")
    val status: String,
    @SerializedName("tanggal_pemesanan")
    val tanggalPemesanan: String,
    @SerializedName("komisi")
    val komisi: String
)

