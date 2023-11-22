package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Pembayaran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        ImageView backToMenuLainnya = findViewById(R.id.backToMenuLainnya);
        backToMenuLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        LinearLayout pilihBankLayout = findViewById(R.id.PilihBank);
        RadioButton rbCash = findViewById(R.id.rb_cash);
        RadioButton rbTransfer = findViewById(R.id.rb_transfer);

        rbCash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    rbTransfer.setChecked(false);
                }
                else {

                }
            }
        });
        rbTransfer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    rbCash.setChecked(false);
                    pilihBankLayout.setVisibility(View.VISIBLE);
                }else {
                    pilihBankLayout.setVisibility(View.GONE);
                }
            }
        });
        CardView btn_selanjutnya = findViewById(R.id.btn_selanjutnya);
        btn_selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pembayaran.this, VerifikasiManual.class);
                startActivity(intent);
            }
        });
    }
}