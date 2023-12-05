package com.example.rahmatantravel.api.pemesananResponse

import com.google.gson.annotations.SerializedName

class PemesananPostResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("information") val info: String
)