package com.example.rahmatantravel.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.PaketHomeModels;
import com.example.rahmatantravel.Models.PesananModels;
import com.example.rahmatantravel.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.PesananViewHolder> {
    private ArrayList<PesananModels> dataList;

    public PesananAdapter(ArrayList<PesananModels>dataList){
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public PesananAdapter.PesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pesanan, parent, false);
        return new PesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesananAdapter.PesananViewHolder holder, int position) {
        PesananModels pesanan = dataList.get(position);
        holder.idPesanan.setText(dataList.get(position).getIdPesanan());
        holder.judulPaket.setText(dataList.get(position).getJudulPaket());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String formattedDate = dateFormat.format(dataList.get(position).getTanggalTransaksi());
        holder.tanggalTransaksi.setText(formattedDate);
        holder.methodePembayaran.setText(dataList.get(position).getMethodePembayaran());

        if (pesanan.getStatusPesanan().equals("Proses Verifikasi")){
            holder.statusPesanan.setTextColor(Color.parseColor("#FFA500"));
        }
        else if (pesanan.getStatusPesanan().equals("Gagal Verifikasi")){
            holder.statusPesanan.setTextColor(Color.RED);
        }
        else if (pesanan.getStatusPesanan().equals("Belum Lunas")){
            holder.statusPesanan.setTextColor(Color.parseColor("#FF6000"));
        }
        else if (pesanan.getStatusPesanan().equals("Lunas")){
            holder.statusPesanan.setTextColor(Color.GREEN);
        }
        holder.statusPesanan.setText(pesanan.getStatusPesanan());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class PesananViewHolder extends RecyclerView.ViewHolder {
        private TextView idPesanan, judulPaket, methodePembayaran, tanggalTransaksi, statusPesanan;
        public PesananViewHolder(View itemView) {
            super(itemView);
            idPesanan = (TextView) itemView.findViewById(R.id.idPesanan);
            judulPaket = (TextView) itemView.findViewById(R.id.judulPaket);
            methodePembayaran = (TextView) itemView.findViewById(R.id.methodePembayaran);
            tanggalTransaksi = (TextView) itemView.findViewById(R.id.tanggalTransaksi);
            statusPesanan = (TextView) itemView.findViewById(R.id.statusPesanan);
        }
    }
}
