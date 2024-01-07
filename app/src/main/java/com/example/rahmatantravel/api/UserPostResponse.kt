package com.example.rahmatantravel.api

import com.google.gson.annotations.SerializedName

data class UserPostResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("level")
    val level: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("foto")
    val foto: String
)

