package com.example.rahmatantravel.api.paketResponse

import com.google.gson.annotations.SerializedName

data class PaketResponse(
    @SerializedName("paket_id") val id_paket: Int,
    @SerializedName("nama") val nama_paket: String,
    @SerializedName("menu") val menu: String,
    @SerializedName("lama_hari") val lama_hari: Int,
    @SerializedName("minim_dp") val minimDP: Int,
    @SerializedName("termasuk_harga") val termasuk_harga: List<String>,
    @SerializedName("tidak_termasuk_harga") val tidak_termasuk_harga: List<String>,
    @SerializedName("keunggulan") val keunggulan: String,
    @SerializedName("foto_paket") val foto_paket: String,
    @SerializedName("harga") val harga: List<HargaPaketResponse>,
    @SerializedName("hotel") val hotel: List<HotelResponse>
)
