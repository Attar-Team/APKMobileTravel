package com.example.rahmatantravel.api.customerResponse

import com.google.gson.annotations.SerializedName

data class PhotoResponse (
    @SerializedName("status")
    val status : Int,

    @SerializedName("message")
    val message : String,

//    @SerializedName("foto1")
//    val photo1 : String,
//
//    @SerializedName("foto2")
//    val photo2 : String,
//
//    @SerializedName("foto3")
//    val photo3 : String,
//
//    @SerializedName("foto4")
//    val photo4 : String

    @SerializedName("information")
    val info : String
)