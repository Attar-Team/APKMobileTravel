package com.example.rahmatantravel.api.customerResponse

import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    @SerializedName("status")
    val status : String,

    @SerializedName("message")
    val message : String,

    @SerializedName("information")
    val info : String
)
