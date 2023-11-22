package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.rahmatantravel.Adapter.DetailJamaahAdapter;
import com.example.rahmatantravel.Models.DetailJamaahModels;

import java.util.ArrayList;

public class DetailBooking extends AppCompatActivity {
    private ImageView backToMenuLainnya;
    private RecyclerView recycleDetailJamaah;
    private DetailJamaahAdapter detailJamaahAdapter;
    private ArrayList<DetailJamaahModels> detailJamaahModelsArrayList;
    private ImageView btn_add;
    private Button btn_booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);

        btn_booking = findViewById(R.id.btn_booking);
        btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailBooking.this, Pembayaran.class);
                startActivity(intent);
            }
        });
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemCount = detailJamaahAdapter.getItemCount() + 1;
                String newItemName = "Nama Jamaah" + itemCount;
                String newItemCity = "Kota Asal" +itemCount;
                DetailJamaahModels newDetailJamaah = new DetailJamaahModels(itemCount, newItemName, newItemCity);

                detailJamaahModelsArrayList.add(newDetailJamaah);
                detailJamaahAdapter.notifyItemInserted(itemCount);
                recycleDetailJamaah.smoothScrollToPosition(itemCount);
            }
        });
        backToMenuLainnya = findViewById(R.id.backToMenuLainnya);
        backToMenuLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        addDataDetailJamaah();
        recycleDetailJamaah = (RecyclerView) findViewById(R.id.recycleDetailJamaah);
        detailJamaahAdapter = new DetailJamaahAdapter(this, detailJamaahModelsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        if (detailJamaahAdapter == null){
            detailJamaahAdapter = new DetailJamaahAdapter(this, detailJamaahModelsArrayList);
        }
        recycleDetailJamaah.setLayoutManager(layoutManager);
        recycleDetailJamaah.setAdapter(detailJamaahAdapter);

    }
    void addDataDetailJamaah(){
        detailJamaahModelsArrayList = new ArrayList<>();
        detailJamaahModelsArrayList.add(new DetailJamaahModels(1, "", ""));

        try {
            DetailJamaahAdapter adapter = new DetailJamaahAdapter(this, detailJamaahModelsArrayList);
            recycleDetailJamaah.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("DateError", "Error parsing date: " + e.getMessage());
        }
    }

}