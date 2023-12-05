package com.example.rahmatantravel.api.pemesananResponse.Lunas

import com.google.gson.annotations.SerializedName

data class APILunas(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<LunasResponse>
)
