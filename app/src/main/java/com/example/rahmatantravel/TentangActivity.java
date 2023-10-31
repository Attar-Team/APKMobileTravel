package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class TentangActivity extends AppCompatActivity {

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

        CardView cardWa = findViewById(R.id.cardWa);

        cardWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsapp();
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
        Uri uri = Uri.parse("https://wa.me/+62895366960593");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}