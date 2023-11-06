package com.example.rahmatantravel.api

import com.example.rahmatantravel.api.paketResponse.APIResponse
import com.example.rahmatantravel.api.UserPostRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {

    @POST("apiLogin")
    fun post(
        @Body body: UserPostRequest
    ): Call<UserPostResponse>

    @GET("apiGetKeberangkatan")
    fun getPaket(): Call<APIResponse>

    @GET("apiGetKeberangkatan/{id}")
    fun getPaketById(@Path("id") id: Int): Call<APIResponse>

}