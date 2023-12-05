package com.example.rahmatantravel.api

import com.google.gson.annotations.SerializedName

data class UserPostResponse (
    @SerializedName("status") val status : Int,
    @SerializedName("message") val message : String,
    @SerializedName("nama") val nama : String,
    @SerializedName("user_id") val userID : Int,
    @SerializedName("token") val token : String
    )
