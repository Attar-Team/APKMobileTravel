package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Tampilpaket extends AppCompatActivity {
    private CardView buttonkembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilpaket);


        buttonkembali = findViewById(R.id.buttonkemabli);
        buttonkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tampilpaket.this, HomeFragment.class);
                startActivity(intent);
            }
        });
    }
}