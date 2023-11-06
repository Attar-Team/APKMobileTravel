package com.example.rahmatantravel.api.paketResponse

import com.google.gson.annotations.SerializedName

data class KeberangkatanResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("tanggal") val tanggal : String,
    @SerializedName("status") val status : String,
    @SerializedName("seats") val seats : Int,
    @SerializedName("paket") val paket : List<PaketResponse>
)