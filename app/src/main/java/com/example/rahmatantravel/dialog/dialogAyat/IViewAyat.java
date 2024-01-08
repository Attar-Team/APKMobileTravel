package com.example.rahmatantravel.dialog.dialogAyat;

import com.example.rahmatantravel.model.DataAyatItem;

import java.util.List;

public interface IViewAyat {
    void onGetDataAyat(List<DataAyatItem> data);
    void onErrorMsg(String msg);
}
