package com.example.rahmatantravel.api.artikelResponse

import com.google.gson.annotations.SerializedName

class ArtikelResponse (
    @SerializedName("artikel_id") val artikelID: Int,
    @SerializedName("user_id") val userID: Int,
    @SerializedName("judul") val judulArtikel: String,
    @SerializedName("isi") val isiArtikel: String,
    @SerializedName("foto") val foto: String
)
