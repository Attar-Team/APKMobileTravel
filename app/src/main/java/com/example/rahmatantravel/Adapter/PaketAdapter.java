package com.example.rahmatantravel.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.PaketHomeModels;
import com.example.rahmatantravel.Models.PaketModels;
import com.example.rahmatantravel.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PaketAdapter extends RecyclerView.Adapter<PaketAdapter.PaketViewHolder> {
    private ArrayList<PaketModels> dataList;
//    private ArrayList<PaketModels> filteredList;

    public PaketAdapter(ArrayList<PaketModels> dataList)
    {
        this.dataList = dataList;
//        this.filteredList = new ArrayList<>(dataList);
    }

//    public void setFilteredList(List<PaketModels> filteredList){
//        this.filteredList = (ArrayList<PaketModels>) filteredList;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public PaketAdapter.PaketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_paket, parent, false);
        return new PaketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaketAdapter.PaketViewHolder holder, int position) {
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

    public class PaketViewHolder extends RecyclerView.ViewHolder {
        private TextView judulPaket, tanggalBerangkat, waktuPaket, rateHotel,hargaPaket;

        public PaketViewHolder(View view) {
            super(view);
            judulPaket = (TextView) itemView.findViewById(R.id.judulPaket);
            tanggalBerangkat = (TextView) itemView.findViewById(R.id.tanggalBerangkat);
            waktuPaket = (TextView) itemView.findViewById(R.id.waktuPaket);
            rateHotel = (TextView) itemView.findViewById(R.id.rateHotel);
            hargaPaket = (TextView) itemView.findViewById(R.id.hargaPaket);
        }
    }
}
