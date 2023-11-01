package com.example.rahmatantravel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.ArtikelModels;
import com.example.rahmatantravel.R;

import java.util.ArrayList;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ArtikelViewHolder> {

    private ArrayList<ArtikelModels> dataList;

    public ArtikelAdapter(ArrayList<ArtikelModels>dataList){
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public ArtikelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_artikel, parent, false);
        return new ArtikelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtikelViewHolder holder, int position) {
        holder.judulArtikel.setText(dataList.get(position).getJudulArtikel());
        holder.deskripsiArtikel.setText(dataList.get(position).getDeksripsiArtikel());
        holder.tanggalPosting.setText((CharSequence) dataList.get(position).getTanggalPosting());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public static class ArtikelViewHolder extends RecyclerView.ViewHolder{
        private TextView judulArtikel, deskripsiArtikel, tanggalPosting;

        public ArtikelViewHolder(@NonNull View itemView) {
            super(itemView);
            judulArtikel = (TextView) itemView.findViewById(R.id.judulArtikel);
            deskripsiArtikel = (TextView) itemView.findViewById(R.id.deskripsiArtikel);
            tanggalPosting = (TextView) itemView.findViewById(R.id.tanggalPosting);
        }
    }
}
