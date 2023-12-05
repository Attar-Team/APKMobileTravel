package com.example.rahmatantravel.api.artikelResponse

import com.google.gson.annotations.SerializedName

class APIArtikel (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<ArtikelResponse>
)
