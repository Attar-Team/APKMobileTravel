package com.example.rahmatantravel.api.pemesananResponse

import com.google.gson.annotations.SerializedName

class APIPesanan (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<PesananResponse>
)
