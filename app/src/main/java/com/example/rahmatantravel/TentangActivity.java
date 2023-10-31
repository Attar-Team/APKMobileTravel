package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class TentangActivity extends AppCompatActivity {
    private WebView googleMapWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        initializeUI();

//        CardView maps = findViewById(R.id.maps);
//        maps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGoogleMaps();
//            }
//        });

        CardView cardWa = findViewById(R.id.cardWa);

        cardWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsapp();
            }
        });
    }

    private void initializeUI(){
        String iframe = "<iframe src=https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d3949.0923910294287!2d113.6399914749853!3d-8.19344769183829!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x2dd6940300000001%3A0x1f2a06ab12ddc149!2sRahmatan%20Tour%20%26%20Travel!5e0!3m2!1sid!2sid!4v1698732913155!5m2!1sid!2sid width=600 height=450 frameborder=0 style=border:0</iframe>";
        googleMapWebView = (WebView) findViewById(R.id.googlemap_webView);
        googleMapWebView.getSettings().setJavaScriptEnabled(true);
        googleMapWebView.loadData(iframe,"text/html","utf-8");
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