package com.example.rahmatantravel.Models;

import java.util.Date;

public class PaketHomeModels {
    private String judulPaket;
    private Date tanggalBerangkat;
    private Integer waktuPaket;
    private Integer rateHotel;
    private Integer hargaPaket;

    public PaketHomeModels(String judulPaket, Date tanggalBerangkat, Integer waktuPaket, Integer rateHotel, Integer hargaPaket){
        this.judulPaket = judulPaket;
        this.tanggalBerangkat = tanggalBerangkat;
        this.waktuPaket = waktuPaket;
        this.rateHotel = rateHotel;
        this.hargaPaket = hargaPaket;
    }
    public String getJudulPaket(){
        return judulPaket;
    }
    public void setJudulPaket(String judulPaket){
        this.judulPaket = judulPaket;
    }
    public Date getTanggalBerangkat(){
        return tanggalBerangkat;
    }
    public void setTanggalBerangkat(Date tanggalBerangkat){
        this.tanggalBerangkat = tanggalBerangkat;
    }
    public Integer getWaktuPaket(){

        return waktuPaket;
    }
    public void setWaktuPaket(Integer waktuPaket){

        this.waktuPaket = waktuPaket;
    }
    public Integer getRateHotel(){

        return rateHotel;
    }
    public void setRateHotel(Integer rateHotel){

        this.rateHotel = rateHotel;
    }
    public Integer getHargaPaket(){
        return hargaPaket;
    }
    public void setHargaPaket(Integer hargaPaket){
        this.hargaPaket = hargaPaket;
    }
}
