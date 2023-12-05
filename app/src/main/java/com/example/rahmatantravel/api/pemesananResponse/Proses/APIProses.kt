package com.example.rahmatantravel.api.pemesananResponse.Proses

import com.example.rahmatantravel.api.pemesananResponse.Lunas.LunasResponse
import com.google.gson.annotations.SerializedName

data class APIProses(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<ProsesResponse>
)
