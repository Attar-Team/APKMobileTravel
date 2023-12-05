package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class TentangActivity extends AppCompatActivity {
    private WebView googleMapWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        CardView maps = findViewById(R.id.maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMaps();
            }
        });

        ImageView backToMenuLainnya = findViewById(R.id.backToMenuLainnya);
        CardView cardWa = findViewById(R.id.cardWa);
        CardView cardIg = findViewById(R.id.cardIg);
        CardView cardTt = findViewById(R.id.cardTt);


        backToMenuLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cardWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsapp();
            }
        });
        cardIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagram();
            }
        });
        cardTt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTiktok();
            }
        });
    }

    private void openGoogleMaps(){
        Uri gmmIntentUri = Uri.parse("https://maps.app.goo.gl/qKqw2AY8Ya1nTFCP8");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(mapIntent);
        }
        else {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            startActivity(browserIntent);
        }
    }

    private void openWhatsapp(){
        Uri uri = Uri.parse("https://wa.me/62895366960593");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.whatsapp");

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);
        }
    }
    private void openInstagram(){
        Uri uri = Uri.parse("https://instagram.com/rahmatantourandtravel?igshid=NzZlODBkYWE4Ng==");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.instagram.android");

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);
        }
    }

    private void openTiktok(){
        Uri uri = Uri.parse("https://www.tiktok.com/@rahmatantourtravel?_t=8gyExpS5e5q&_r=1");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.zhiliaoapp.musically");

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(browserIntent);
        }
    }
}