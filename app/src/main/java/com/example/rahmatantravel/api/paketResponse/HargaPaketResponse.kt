package com.example.rahmatantravel.api.paketResponse

import com.google.gson.annotations.SerializedName

data class HargaPaketResponse(
    @SerializedName("jenis")
    val jenis: String,

    @SerializedName("diskon")
    val diskon: Int,

    @SerializedName("harga")
    val harga: Int
)