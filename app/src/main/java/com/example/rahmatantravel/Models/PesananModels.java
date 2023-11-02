package com.example.rahmatantravel.Models;

import java.util.Date;

public class PesananModels {
    private String idPesanan;
    private String judulPaket;
    private Date tanggalTransaksi;
    private String methodePembayaran;
    private String statusPesanan;

    public PesananModels(String idPesanan, String judulPaket, Date tanggalTransaksi, String methodePembayaran, String statusPesanan){
        this.idPesanan = idPesanan;
        this.judulPaket = judulPaket;
        this.tanggalTransaksi = tanggalTransaksi;
        this.methodePembayaran = methodePembayaran;
        this.statusPesanan = statusPesanan;
    }
    public String getIdPesanan(){
        return idPesanan;
    }
    public void setIdPesanan(String idPesanan){
        this.idPesanan = idPesanan;

    }
    public String getJudulPaket(){
        return judulPaket;
    }
    public void setJudulPaket(String judulPaket){
        this.judulPaket = judulPaket;
    }
    public Date getTanggalTransaksi(){
        return tanggalTransaksi;
    }
    public void setTanggalTransaksi(Date tanggalTransaksi){
        this.tanggalTransaksi = tanggalTransaksi;
    }
    public String getMethodePembayaran(){
        return methodePembayaran;
    }
    public void setMethodePembayaran(String methodePembayaran){
        this.methodePembayaran = methodePembayaran;
    }
    public String getStatusPesanan(){
        return statusPesanan;
    }
    public void setStatusPesanan(String statusPesanan){
        this.statusPesanan = statusPesanan;
    }
}
