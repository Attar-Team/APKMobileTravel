package com.example.projectpostapi.api

import com.google.gson.annotations.SerializedName

data class PostResponse (
    val userId : String?,
    val id : Int,
    val title: String,
    @SerializedName ("body") val text : String
        )