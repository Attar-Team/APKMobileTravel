package com.example.rahmatantravel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.DetailJamaahModels;
import com.example.rahmatantravel.R;

import java.util.ArrayList;

public class DetailJamaahAdapter extends RecyclerView.Adapter<DetailJamaahAdapter.DetailJamaahViewHolder> {
    private ArrayList<DetailJamaahModels>dataList;
    private Context context;

    public DetailJamaahAdapter(Context context, ArrayList<DetailJamaahModels> dataList)
    {
        this.dataList = dataList;
        this.context = context;
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
        holder.urutanData.setText(String.valueOf(detailJamaah.getUrutanData()));
//        holder.namaJamaah.setText(detailJamaah.getNamaJamaah());
//        holder.asalKota.setText(detailJamaah.getAsalKota());
//        holder.recyclerKamar.setAdapter(kamarHotelAdapter);
//        kamarHotelAdapter.notifyDataSetChanged();

        ArrayList<String> dataPilihan = new ArrayList<>();
        dataPilihan.add("Jamaah 1");
        dataPilihan.add("Jamaah 2");
        dataPilihan.add("Jamaah 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.list_item_jamaah, dataPilihan);
        holder.autoComplete.setAdapter(adapter);
        holder.autoComplete.setText(detailJamaah.getAutoCompleteTextView(), false);
        holder.autoComplete.setOnItemClickListener((parent, view, position1, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position1);
            Toast.makeText(context, "Anda memilih : " + selectedItem, Toast.LENGTH_SHORT).show();
            detailJamaah.setAutoCompleteTextView(selectedItem);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DetailJamaahViewHolder extends RecyclerView.ViewHolder {
        private TextView urutanData, namaJamaah, asalKota;
        private AutoCompleteTextView autoComplete;
        public RecyclerView recyclerKamar;
        public DetailJamaahViewHolder(@NonNull View itemView) {
            super(itemView);
            autoComplete = itemView.findViewById(R.id.autoComplete);
            urutanData = (TextView) itemView.findViewById(R.id.urutanData);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
//            recyclerKamar.setLayoutManager(layoutManager);

//            namaJamaah = (TextView) itemView.findViewById(R.id.namaJamaah);
//            asalKota = (TextView) itemView.findViewById(R.id.asalKota);
        }
    }
}
