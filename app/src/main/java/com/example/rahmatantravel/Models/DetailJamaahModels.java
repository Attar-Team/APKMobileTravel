package com.example.rahmatantravel.Models;

import java.util.ArrayList;

public class DetailJamaahModels {
    private int urutanData;
    private String namaJamaah;
    private String asalKota;
    private String autoCompleteTextView;

    public DetailJamaahModels(Integer urutanData, String namaJamaah, String asalKota){
        this.urutanData = urutanData;
        this.namaJamaah = namaJamaah;
        this.asalKota = asalKota;
        this.autoCompleteTextView = autoCompleteTextView;
    }
    public String getAutoCompleteTextView(){
        return autoCompleteTextView;
    }
    public void setAutoCompleteTextView(String autoCompleteTextView){
        this.autoCompleteTextView = autoCompleteTextView;
    }
    public Integer getUrutanData(){
        return urutanData;
    }
    public void setUrutanData(Integer urutanData){
        this.urutanData = urutanData;
    }
    public String getNamaJamaah(){
        return namaJamaah;
    }
    public void setNamaJamaah(String namaJamaah){
        this.namaJamaah = namaJamaah;
    }
    public String getAsalKota(){
        return asalKota;
    }
    public void setAsalKota(String asalKota){
        this.asalKota = asalKota;
    }
}
