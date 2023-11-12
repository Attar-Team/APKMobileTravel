package com.example.rahmatantravel;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahmatantravel.Adapter.DetailJamaahAdapter;
import com.example.rahmatantravel.Adapter.PesananAdapter;
import com.example.rahmatantravel.Models.DetailJamaahModels;
import com.example.rahmatantravel.Models.PesananModels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailBooking extends AppCompatActivity {
    private ImageView backToMenuLainnya;
    private SwitchCompat switchCompat;

    private RecyclerView recycleDetailJamaah;
    private DetailJamaahAdapter detailJamaahAdapter;
    private ArrayList<DetailJamaahModels> detailJamaahModelsArrayList;
    private ImageView btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);


        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemCount = detailJamaahAdapter.getItemCount() + 1;
                String newItemName = "Nama Jamaah" + itemCount;
                String newItemCity = "Kota Asal" +itemCount;

                detailJamaahModelsArrayList.add(new DetailJamaahModels(itemCount, newItemName, newItemCity));
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

        switchCompat = findViewById(R.id.switchData);

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = switchCompat.isChecked();
                onSwitchClick(isChecked);
            }
        });
        addDataDetailJamaah();
        recycleDetailJamaah = (RecyclerView) findViewById(R.id.recycleDetailJamaah);
        detailJamaahAdapter = new DetailJamaahAdapter(detailJamaahModelsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        if (detailJamaahAdapter == null){
            detailJamaahAdapter = new DetailJamaahAdapter(detailJamaahModelsArrayList);
        }
        recycleDetailJamaah.setLayoutManager(layoutManager);
        recycleDetailJamaah.setAdapter(detailJamaahAdapter);

    }
    void addDataDetailJamaah(){
        detailJamaahModelsArrayList = new ArrayList<>();

        try {
            detailJamaahModelsArrayList.add(new DetailJamaahModels(1, "Isi Nama Jamaah", "Jember"));
            DetailJamaahAdapter adapter = new DetailJamaahAdapter(detailJamaahModelsArrayList);
            recycleDetailJamaah.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("DateError", "Error parsing date: " + e.getMessage());
        }
    }

    public void onSwitchClick(Boolean isChecked){
        if (isChecked){
            switchCompat.setThumbResource(R.drawable.thumb);
            switchCompat.setTrackResource(R.drawable.track);
        }
        else{
            switchCompat.setThumbResource(R.drawable.thumb);
            switchCompat.setTrackResource(R.drawable.track);
        }
    }

}