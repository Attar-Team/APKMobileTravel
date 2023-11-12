package com.example.rahmatantravel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.DetailJamaahModels;
import com.example.rahmatantravel.R;

import java.util.ArrayList;

public class DetailJamaahAdapter extends RecyclerView.Adapter<DetailJamaahAdapter.DetailJamaahViewHolder> {
    private ArrayList<DetailJamaahModels>dataList;

    public DetailJamaahAdapter(ArrayList<DetailJamaahModels> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DetailJamaahAdapter.DetailJamaahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_detailjamaah, parent,false);
        return new DetailJamaahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailJamaahAdapter.DetailJamaahViewHolder holder, int position) {
        DetailJamaahModels detailJamaah = dataList.get(position);
        holder.urutanData.setText(dataList.get(position).getUrutanData());
        holder.namaJamaah.setText(dataList.get(position).getUrutanData());
        holder.asalKota.setText(dataList.get(position).getAsalKota());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DetailJamaahViewHolder extends RecyclerView.ViewHolder {
        private TextView urutanData,namaJamaah, asalKota;
        public DetailJamaahViewHolder(@NonNull View itemView) {
            super(itemView);
            urutanData = (TextView) itemView.findViewById(R.id.urutanData);
            namaJamaah = (TextView) itemView.findViewById(R.id.namaJamaah);
            asalKota = (TextView) itemView.findViewById(R.id.asalKota);
        }
    }
}
