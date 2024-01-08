package com.example.rahmatantravel.activity;

import com.example.rahmatantravel.model.DataSurahItem;

import java.util.List;

public interface IViewSurah {
    void getDetailSurah(List<DataSurahItem> data);
    void onErrorMsg(String msg);
}
