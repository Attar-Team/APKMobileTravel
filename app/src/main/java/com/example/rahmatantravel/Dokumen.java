package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.rahmatantravel.Fragment.Dokumen1_K;
import com.example.rahmatantravel.Fragment.Dokumen3_K;
import com.example.rahmatantravel.Models.SharedViewModel;

public class Dokumen extends AppCompatActivity{
//    ActivityMainBinding binding;
//    String[] descriptionData={"Biodata, Dokument, Pasport"};
//    int current_state=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokumen);

        SharedViewModel sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);


//        StepsView spb = findViewById(R.id.spb);
//
//        spb.setLabels(descriptionData)
//                .setBarColorIndicator(Color.BLACK)
//                .setProgressColorIndicator(getResources().getColor(R.color.orange))
//                .setLabelColorIndicator(getResources().getColor(R.color.orange))
//                .setCompletedPosition(0)
//                .drawView();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Dokumen1_K()).commit();
        }
    }
//    @Override
//    public void onNextButtonClicked(){
//        current_state++;
//        if (current_state == 1){
//            Dokumen2 dokumen2 = new Dokumen2();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frameLayout, dokumen2)
//                    .commit();
//
//            dokumen2.setOnNextButtonClickListener(this);
//        } else if (current_state == 2) {
//            Dokumen3 dokumen3 = new Dokumen3();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frameLayout, dokumen3)
//                    .commit();
//        }
//
//    }
//    @Override
//    public void onBackButtonClicked() {
//        if (current_state > 0) {
//            // Jika ada state sebelumnya, kurangi current_state
//            current_state--;
//
//            if (current_state == 0) {
//                // Kembali ke fragment Dokumen1
//                Dokumen1 dokumen1 = new Dokumen1();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frameLayout, dokumen1)
//                        .commit();
//
//                // Set listener untuk menanggapi "Next" dari Dokumen1Fragment
//                dokumen1.setOnNextButtonClickListener(this);
//            } else if (current_state == 1) {
//                // Kembali ke fragment Dokumen2
//                Dokumen2 dokumen2 = new Dokumen2();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frameLayout, dokumen2)
//                        .commit();
//
//                // Set listener untuk menanggapi "Next" dari Dokumen2Fragment
//                dokumen2.setOnNextButtonClickListener(this);
//            }
//        } else {
//            // Jika sudah pada state awal, panggil metode onBackPressed dari superclass
//            super.onBackPressed();
//        }
//    }
}