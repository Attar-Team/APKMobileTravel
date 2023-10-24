package com.example.rahmatantravel.api

import com.example.projectpostapi.api.UserPostRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {

    @POST("apiLogin")
    fun post(
        @Body body: UserPostRequest
    ): Call<UserPostResponse>

}