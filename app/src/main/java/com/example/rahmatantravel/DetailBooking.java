package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DetailBooking extends AppCompatActivity {
    private ImageView backToMenuLainnya;
    private SwitchCompat switchCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);

        backToMenuLainnya = findViewById(R.id.backToMenuLainnya);
        backToMenuLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        switchCompat = findViewById(R.id.switchData);

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = switchCompat.isChecked();
                onSwitchClick(isChecked);
            }
        });

    }

    public void onSwitchClick(Boolean isChecked){
        if (isChecked){
            switchCompat.setThumbResource(R.drawable.thumb);
            switchCompat.setTrackResource(R.drawable.track);
        }
        else{
            switchCompat.setThumbResource(R.drawable.thumb);
            switchCompat.setTrackResource(R.drawable.track);
        }
    }

}