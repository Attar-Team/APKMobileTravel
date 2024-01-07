package com.example.rahmatantravel.api

import com.google.gson.annotations.SerializedName

data class CheckReferalResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: UserData
)

data class UserData(
    @SerializedName("NIK")
    val nik: String,
    @SerializedName("nama")
    val nama: String
)

