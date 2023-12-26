package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Dompet extends AppCompatActivity {
    private TextView btn_tariksaldo;
    private ImageView backToMenuLainnya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dompet);

        btn_tariksaldo = findViewById(R.id.btn_tariksaldo);
        backToMenuLainnya = findViewById(R.id.backToMenuLainnya);
        backToMenuLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_tariksaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dompet.this, TarikTunai.class);
                startActivity(intent);
            }
        });
    }
}