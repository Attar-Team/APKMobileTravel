package com.example.rahmatantravel.api

import com.example.rahmatantravel.api.artikelResponse.APIArtikel
import com.example.rahmatantravel.api.customerResponse.APICustomer
import com.example.rahmatantravel.api.paketResponse.APIResponse
import com.example.rahmatantravel.api.customerResponse.PhotoResponse
import com.example.rahmatantravel.api.pemesananResponse.APIPesanan
import com.example.rahmatantravel.api.pemesananResponse.PemesananPostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path

interface API {

    @POST("apiLogin")
    fun post(
        @Body body: UserPostRequest
    ): Call<UserPostResponse>

    @POST("apiRegister")
    @Multipart
    fun register(
        @PartMap data: Map<String,String>
    ): Call<RegisterPostResponse>

    @POST("apiTambahProfileCustomer")
    @Multipart
    fun tambahProfile(
        @Part ("NIK") NIK: String,
        @Part ("user_id") user_id: String,
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

    @POST("apiTambahPemesanan")
    @Multipart
    fun tambahPemesanan(@PartMap data: Map<String, @JvmSuppressWildcards RequestBody>,
    @Part image: MultipartBody.Part?) : Call<PemesananPostResponse>

    @GET("apiGetKeberangkatan")
    fun getPaket(): Call<APIResponse>

    @GET("apiGetKeberangkatan/{id}")
    fun getPaketById(@Path("id") id: Int): Call<APIResponse>

    @GET("apiGetProfileCustomer/{id}")
    fun getProfileCustomer(@Path("id") id: Int): Call<APICustomer>

    @GET("apiGetPemesananByStatus/{statusPembayaran}/{userID}")
    fun getPemesananByStatus(
        @Path("statusPembayaran") statusPembayaran: String,
        @Path("userID") userID: String
    ): Call<APIPesanan>

    @GET("apiGetKeberangkatanByMenu/{menu}")
    fun getPaketByMenu(@Path("menu") menu: String): Call<APIResponse>

    @GET("apiGetArtikel")
    fun getArtikel(): Call<APIArtikel>

}