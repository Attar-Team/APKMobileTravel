package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView belumPunyaAkun = findViewById(R.id.belumPunyaAkun);
        EditText passwordEditText = findViewById(R.id.password);
        EditText rePasswordEditText = findViewById(R.id.re_password);
        TextView errorMassageTextView = findViewById(R.id.errorMessage);
        CardView btn_register = findViewById(R.id.btn_register);

        belumPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivityRevisi2.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                String rePassword = rePasswordEditText.getText().toString();

                if (!password.equals(rePassword)){
                    errorMassageTextView.setText("Password harus sama !");
                    rePasswordEditText.requestFocus();
                } else if (rePassword.isEmpty()) {
                    errorMassageTextView.setText("Password tidak boleh kosong");
                } else if (password.length() < 8){
                    errorMassageTextView.setText("Password minimal harus 8 kata!");
                }else {
                    //LANJUTKAN REGISTER DISINI
                }
            }
        });
//        rePasswordEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String password = passwordEditText.getText().toString();
//                String rePassword = s.toString();
//
//                if (rePassword.isEmpty()){
//                    errorMassageTextView.setText("");
//                } else if(password.length() < 8){
//                    errorMassageTextView.setText("Password minimal harus 8 kata");
//                    errorMassageTextView.setTextColor(Color.RED);
//                }
//                else if (!rePassword.equals(password)){
//                    errorMassageTextView.setText("Password tidak sama !");
//                    errorMassageTextView.setTextColor(Color.RED);
//                }
//                else {
//                    errorMassageTextView.setText("Password sudah Sama");
//                    errorMassageTextView.setTextColor(Color.GREEN);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }
}