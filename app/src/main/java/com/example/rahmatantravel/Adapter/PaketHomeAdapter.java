package com.example.rahmatantravel.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.ArtikelModels;
import com.example.rahmatantravel.Models.PaketHomeModels;
import com.example.rahmatantravel.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PaketHomeAdapter extends RecyclerView.Adapter<PaketHomeAdapter.PaketHomeViewHolder>{

    private ArrayList<PaketHomeModels>dataList;

    public PaketHomeAdapter(ArrayList<PaketHomeModels> dataList){
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public PaketHomeAdapter.PaketHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pakethome, parent, false);
        return new PaketHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaketHomeAdapter.PaketHomeViewHolder holder, int position) {
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

    public class PaketHomeViewHolder extends RecyclerView.ViewHolder {
        private TextView judulPaket, tanggalBerangkat, waktuPaket, rateHotel,hargaPaket;
        public PaketHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            judulPaket = (TextView) itemView.findViewById(R.id.judulPaket);
            tanggalBerangkat = (TextView) itemView.findViewById(R.id.tanggalBerangkat);
            waktuPaket = (TextView) itemView.findViewById(R.id.waktuPaket);
            rateHotel = (TextView) itemView.findViewById(R.id.rateHotel);
            hargaPaket = (TextView) itemView.findViewById(R.id.hargaPaket);
        }
    }
}
