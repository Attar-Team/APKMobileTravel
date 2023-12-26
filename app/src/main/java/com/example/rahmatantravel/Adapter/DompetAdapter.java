package com.example.rahmatantravel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.DompetModels;
import com.example.rahmatantravel.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DompetAdapter extends RecyclerView.Adapter<DompetAdapter.DompetViewHolder> {
    private ArrayList<DompetModels>dataList;
    public DompetAdapter(ArrayList<DompetModels>dataList){this.dataList = dataList; }
    @NonNull
    @Override
    public DompetAdapter.DompetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_dompet, parent, false);
        return new DompetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DompetAdapter.DompetViewHolder holder, int position) {
        holder.Saldo.setText(dataList.get(position).getSaldo());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String formattedDate = dateFormat.format(dataList.get(position).getSaldo());
        holder.tanggalIncome.setText(formattedDate);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DompetViewHolder extends RecyclerView.ViewHolder{
        private TextView Saldo, tanggalIncome;

        public DompetViewHolder(@NonNull View itemView) {
            super(itemView);
            Saldo = (TextView) itemView.findViewById(R.id.Saldo);
            tanggalIncome = (TextView) itemView.findViewById(R.id.tanggalIncome);
        }
    }
}
