package com.example.rahmatantravel.api.paketResponse

import com.google.gson.annotations.SerializedName

data class HargaPaketResponse(
    @SerializedName("harga_paket_id")
    val hargaPaketId: Int,

    @SerializedName("jenis")
    val jenis: String,

    @SerializedName("diskon")
    val diskon: Int,

    @SerializedName("harga")
    val harga: Int
)