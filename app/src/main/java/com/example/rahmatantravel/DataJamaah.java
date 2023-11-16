package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DataJamaah extends AppCompatActivity {

    private ImageView tambahData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_jamaah);

        tambahData = findViewById(R.id.tambahData);

        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataJamaah.this, Dokumen.class);
                startActivity(intent);
            }
        });
    }
}