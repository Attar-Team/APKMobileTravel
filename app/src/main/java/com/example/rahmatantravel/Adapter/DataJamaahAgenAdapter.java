package com.example.rahmatantravel.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.DataJamaahAgenModels;
import com.example.rahmatantravel.R;

import java.util.ArrayList;

public class DataJamaahAgenAdapter extends RecyclerView.Adapter<DataJamaahAgenAdapter.DataJamaahAgenViewHolder> {
    private ArrayList<DataJamaahAgenModels>dataList;
    private DataJamaahAgenAdapter(ArrayList<DataJamaahAgenModels>dataList){this.dataList = dataList; }
    @NonNull
    @Override
    public DataJamaahAgenAdapter.DataJamaahAgenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_data_jamaah_agen, parent, false);
        return new DataJamaahAgenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataJamaahAgenAdapter.DataJamaahAgenViewHolder holder, int position) {
        holder.namaJamaah.setText(dataList.get(position).getNamaJamaah());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DataJamaahAgenViewHolder extends RecyclerView.ViewHolder {
        private TextView namaJamaah;
        public DataJamaahAgenViewHolder(@NonNull View itemView){
            super(itemView);
            namaJamaah = (TextView) itemView.findViewById(R.id.namaJamaah);
        }
    }
}
