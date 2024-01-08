package com.example.rahmatantravel.network;

import com.example.rahmatantravel.model.DataAyatItem;
import com.example.rahmatantravel.model.DataSurahItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("data")
    Observable<List<DataSurahItem>> getListSurah();

    @GET("surat/{nomor}")
    Observable<List<DataAyatItem>> getDetailAyat(@Path("nomor")String nomor);

}
