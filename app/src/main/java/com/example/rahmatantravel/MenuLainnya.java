package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.rahmatantravel.activity.QuranActivity;

public class MenuLainnya extends AppCompatActivity {

    private CardView SmenuUmroh, SmenuHaji, SmenuTour, SmenuDoa, SmenuKiblat, SmenuQuran, SmenuGallery, SmenuArtikel, SmenuTentang;
    private ImageView arrowback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lainnya);

        SmenuUmroh = findViewById(R.id.SmenuUmroh);
        SmenuHaji = findViewById(R.id.SmenuHaji);
        SmenuTour = findViewById(R.id.SmenuTour);
        SmenuDoa = findViewById(R.id.SmenuDoa);
        SmenuKiblat = findViewById(R.id.SmenuKiblat);
        SmenuQuran = findViewById(R.id.SmenuQuran);
        SmenuGallery = findViewById(R.id.SmenuGallery);
        SmenuArtikel = findViewById(R.id.SmenuArticle);
        SmenuTentang = findViewById(R.id.SmenuTentang);
        arrowback = findViewById(R.id.arrowback);

        SmenuUmroh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLainnya.this, UmrohActivity.class);
                startActivity(intent);
            }
        });

        SmenuHaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLainnya.this, HajiActivity.class);
                startActivity(intent);
            }
        });

        SmenuTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLainnya.this, TourActivity.class);
                startActivity(intent);
            }
        });

        SmenuDoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLainnya.this, DoaActivity.class);
                startActivity(intent);
            }
        });

        SmenuKiblat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLainnya.this, KiblatActivity.class);
                startActivity(intent);
            }
        });

        SmenuQuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLainnya.this, QuranActivity.class);
                startActivity(intent);
            }
        });

        SmenuGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLainnya.this, GalleryActivity.class);
                startActivity(intent);
            }
        });

        SmenuArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLainnya.this, ArtikelActivity.class);
                startActivity(intent);
            }
        });

        SmenuTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLainnya.this, TentangActivity.class);
                startActivity(intent);
            }
        });

        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
}