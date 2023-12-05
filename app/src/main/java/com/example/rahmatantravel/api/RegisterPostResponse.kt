package com.example.rahmatantravel.api

import com.google.gson.annotations.SerializedName

class RegisterPostResponse (
    @SerializedName("status")   val status: String,
    @SerializedName("response")  val response: Int
)