package com.example.rahmatantravel.api

import com.google.gson.annotations.SerializedName

data class UserPostResponse (
    @SerializedName("status") val status : String,
    @SerializedName("response") val response : Int
    )
