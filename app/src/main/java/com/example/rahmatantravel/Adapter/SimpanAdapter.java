package com.example.rahmatantravel.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.SimpanModels;
import com.example.rahmatantravel.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SimpanAdapter extends RecyclerView.Adapter<SimpanAdapter.SimpanViewHolder> {
    private ArrayList<SimpanModels> dataList;

    public SimpanAdapter(ArrayList<SimpanModels>dataList){
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public SimpanAdapter.SimpanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_paket, parent, false);
        return new SimpanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpanAdapter.SimpanViewHolder holder, int position) {
        holder.judulPaket.setText(dataList.get(position).getJudulPaket());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String formattedDate = dateFormat.format(dataList.get(position).getTanggalBerangkat());
        holder.tanggalBerangkat.setText(formattedDate);
        holder.waktuPaket.setText(String.valueOf(dataList.get(position).getWaktuPaket()));
        holder.rateHotel.setText(String.valueOf(dataList.get(position).getRateHotel()));
        holder.hargaPaket.setText(String.valueOf(dataList.get(position).getHargaPaket()));
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class SimpanViewHolder extends RecyclerView.ViewHolder {
        private TextView judulPaket, tanggalBerangkat, waktuPaket, rateHotel,hargaPaket;
        public SimpanViewHolder(@NonNull View itemView) {
            super(itemView);
            judulPaket = (TextView) itemView.findViewById(R.id.judulPaket);
            tanggalBerangkat = (TextView) itemView.findViewById(R.id.tanggalBerangkat);
            waktuPaket = (TextView) itemView.findViewById(R.id.waktuPaket);
            rateHotel = (TextView) itemView.findViewById(R.id.rateHotel);
            hargaPaket = (TextView) itemView.findViewById(R.id.hargaPaket);
        }
    }
}
