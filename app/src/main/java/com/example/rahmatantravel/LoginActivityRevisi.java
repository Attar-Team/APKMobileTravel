package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivityRevisi extends AppCompatActivity {

    private TextView txt_login;
    private LinearLayout btnUseEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_revisi);

        txt_login = findViewById(R.id.txt_login);
        btnUseEmail = findViewById(R.id.btnUseEmail);

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityRevisi.this, LoginActivityRevisi2.class);
                startActivity(intent);
            }
        });
        btnUseEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityRevisi.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}