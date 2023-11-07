package com.example.rahmatantravel;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahmatantravel.Adapter.PaketAdapter;
import com.example.rahmatantravel.Adapter.PaketHomeAdapter;
import com.example.rahmatantravel.Adapter.PesananAdapter;
import com.example.rahmatantravel.Models.PaketHomeModels;
import com.example.rahmatantravel.Models.PaketModels;
import com.example.rahmatantravel.Models.PesananModels;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SemuaPaket extends AppCompatActivity {

    private RecyclerView recycleSemuaPaket;
    private PaketAdapter paketAdapter;
    private ArrayList<PaketModels> paketModelsArrayList;
    private SearchView searchView;
    private ImageView backToMenuLainnya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semua_paket);
        SearchView searchView = findViewById(R.id.searchView);
        TextView searchSrcText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchSrcText.setTextSize(12);
        searchSrcText.setTextColor(0xFF808080);
        Typeface customTypeFace = getResources().getFont(R.font.poppinsmedium);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        backToMenuLainnya = findViewById(R.id.backToMenuLainnya);

        backToMenuLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


//        searchView = findViewById(R.id.searchView);
//        searchView.clearFocus();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterList(newText);
//                return false;
//            }
//        });
        addDataPaket();
        recycleSemuaPaket = (RecyclerView) findViewById(R.id.recycleSemuaPaket);
        paketAdapter = new PaketAdapter(paketModelsArrayList);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        if (paketAdapter == null){
            paketAdapter = new PaketAdapter(paketModelsArrayList);
        }
        recycleSemuaPaket.setLayoutManager(layoutManager1);
        recycleSemuaPaket.setAdapter(paketAdapter);
    }

//    private void filterList(String Text) {
//        List<PaketModels> filteredList = new ArrayList<>();
//        for(PaketModels item : paketModelsArrayList){
//            if (item.getJudulPaket().toLowerCase().contains(Text.toLowerCase())){
//                filteredList.add(item);
//            }
//        }
//        if (paketAdapter != null){
//                PaketAdapter.setFilteredList(filteredList);
//        }else{
//            Toast.makeText(this, "Adapter is not initialized", Toast.LENGTH_SHORT).show();
//        }
//    }

    void addDataPaket(){
        paketModelsArrayList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = dateFormat.parse("2023-01-21");
            Date date2 = dateFormat.parse("2023-06-10");
            Date date3 = dateFormat.parse("2023-01-04");
            Date date4 = dateFormat.parse("2023-04-08");

            paketModelsArrayList.add(new PaketModels("Paket Umroh 21 Januari 2023", date1, 10, 5, 30000000));
            paketModelsArrayList.add(new PaketModels("Paket Haji 10 Juni 2023", date2, 9, 5, 29000000));
            paketModelsArrayList.add(new PaketModels("Paket Wisata Turki 04 Januari 2023", date3, 4, 5, 5000000));
            paketModelsArrayList.add(new PaketModels("Paket Umroh 08 April 2023", date4, 12, 5, 28000000));

            PaketAdapter adapter = new PaketAdapter(paketModelsArrayList);
            recycleSemuaPaket.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
            Log.e("DateError", "Error parsing date: " + e.getMessage());
        }

    }
}