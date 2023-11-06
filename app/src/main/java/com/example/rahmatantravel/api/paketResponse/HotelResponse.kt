package com.example.rahmatantravel.api.paketResponse

import com.google.gson.annotations.SerializedName

data class HotelResponse(
    @SerializedName("nama_hotel")
    val namaHotel: String,
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("bintang")
    val bintang: Int,
    @SerializedName("foto_hotel")
    val fotoHotel: String
)
