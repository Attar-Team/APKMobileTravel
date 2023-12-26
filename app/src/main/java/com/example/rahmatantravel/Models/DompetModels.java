package com.example.rahmatantravel.Models;

import java.util.Date;

public class DompetModels {
    private Integer Saldo;
    private Date tanggalIncome;

    public DompetModels(Integer Saldo, Date tanggalIncome){
        this.Saldo = Saldo;
        this.tanggalIncome = tanggalIncome;
    }
    public Integer getSaldo(){
        return Saldo;
    }
    public void setSaldo(Integer Saldo){
        this.Saldo = Saldo;
    }
    public Date getTanggalIncome(){
        return getTanggalIncome();
    }
    public void setTanggalIncome(Date tanggalIncome){
        this.tanggalIncome = tanggalIncome;
    }
}
