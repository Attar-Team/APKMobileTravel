package com.example.rahmatantravel.Models;

public class ArtikelModels {
    private String judulArtikel;
    private String deksripsiArtikel;
    private String tanggalPosting;
    private String gambarArtikel;

    public ArtikelModels(String judulArtikel, String deksripsiArtikel, String tanggalPosting){
        this.judulArtikel = judulArtikel;
        this.deksripsiArtikel = deksripsiArtikel;
        this.tanggalPosting = tanggalPosting;
    }
    public String getJudulArtikel(){
        return judulArtikel;
    }
    public void setJudulArtikel(){
        this.judulArtikel = judulArtikel;
    }
    public String getDeksripsiArtikel(){
        return deksripsiArtikel;
    }
    public void setDeksripsiArtikel(){
        this.deksripsiArtikel = deksripsiArtikel;
    }
    public String getTanggalPosting(){
        return tanggalPosting;
    }
    public void setTanggalPosting(){
        this.tanggalPosting = tanggalPosting;
    }
}
