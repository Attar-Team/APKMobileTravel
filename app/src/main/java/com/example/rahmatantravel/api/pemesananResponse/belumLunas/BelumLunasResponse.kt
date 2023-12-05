package com.example.rahmatantravel.api.pemesananResponse.belumLunas

import com.google.gson.annotations.SerializedName

data class BelumLunasResponse(
    @SerializedName("pemesanan_id") val pemesanan_id: Int,
    @SerializedName("agen_id") val agen_id: Int,
    @SerializedName("jenis_pembayaran") val jenis_pembayaran: String,
    @SerializedName("status") val status: String,
    @SerializedName("tanggal_pemesanan") val tanggal_pemesanan: String,
    @SerializedName("catatan_pemesanan") val catatan_pemesanan: String,
    @SerializedName("sudah_bayar") val sudah_bayar: Int,
    @SerializedName("total_tagihan") val total_tagihan: Long
)