package com.example.rahmatantravel.api

import com.example.rahmatantravel.api.paketResponse.APIResponse
import com.example.rahmatantravel.api.customerResponse.CustomerResponse
import com.example.rahmatantravel.api.customerResponse.DataCustomerResponse
import com.example.rahmatantravel.api.customerResponse.PhotoResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface API {

    @POST("apiLogin")
    fun post(
        @Body body: UserPostRequest
    ): Call<UserPostResponse>

    @POST("apiTambahProfileCustomer")
    @Multipart
    fun tambahProfile(
        @Part ("NIK") NIK: String,
//        @Part ("user_id") user_id: String,
        @Part ("nama") NamaLengkap: String,
        @Part ("tempat_lahir") TempatLahir: String,
        @Part ("tanggal_lahir") TanggalLahir: String,
        @Part ("alamat") Alamat: String,
        @Part ("jenis_kelamin") JenisKelamin: String,
        @Part ("pekerjaan") Pekerjaan: String,
        @Part ("ukuran_baju") Sizes: String,
        @Part ("no_telp") NoTelp: String,
        @Part ("nomor_pasport") NomorPasport: String,
        @Part ("nama_pasport") NamaPasport: String,
        @Part ("tempat_penerbitan") TempatPenerbitanPasport: String,
        @Part ("tgl_penerbitan") TanggalPenerbitanPasport: String,
        @Part FotoCustomer: MultipartBody.Part?,
        @Part FotoPasport: MultipartBody.Part?,
        @Part FotoPasport2: MultipartBody.Part?,
        @Part FotoKTP: MultipartBody.Part?,
        @Part FotoKK: MultipartBody.Part?,
        @Part FotoRekening: MultipartBody.Part?,
        @Part FotoAkte: MultipartBody.Part?,
        @Part FotoBukuNikah: MultipartBody.Part?
    ): Call<PhotoResponse>

    @POST("uploadPasport")
    @Multipart
    fun uploadPasport(
        @Part ("nama") nama: String,
        @Part foto1 : MultipartBody.Part?,
        @Part foto2 : MultipartBody.Part?
    ) : Call<PhotoResponse>

    @GET("apiGetKeberangkatan")
    fun getPaket(): Call<APIResponse>

    @GET("apiGetKeberangkatan/{id}")
    fun getPaketById(@Path("id") id: Int): Call<APIResponse>

    @GET("apiGetProfileCustomer/{id}")
    fun getProfileCustomer(@Path("id") id: Int): Call<List<DataCustomerResponse>>

}