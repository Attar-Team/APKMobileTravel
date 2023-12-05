package com.example.rahmatantravel.api.pemesananResponse.belumLunas

import com.google.gson.annotations.SerializedName

data class APIBelumLunas(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<BelumLunasResponse>
)
