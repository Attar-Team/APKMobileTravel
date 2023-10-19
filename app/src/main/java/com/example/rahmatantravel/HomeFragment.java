package com.example.rahmatantravel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Time;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private CardView menuUmroh;
    private CardView menuHaji;
    private CardView menuTour;
    private CardView menuLainnya;
    private TextView hariIniTextView;
    private TextView waktuSekarang;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm z", Locale.getDefault());
    private Handler timeHandler;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        waktuSekarang = view.findViewById(R.id.waktuSekarang);

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

        timeHandler = new Handler(Looper.getMainLooper()){
            public void handleMessage(@NonNull Message msg){
                super.handleMessage(msg);
                updateWaktuSekarang();
                timeHandler.sendEmptyMessageDelayed(0, 1000);
            }
        };
        timeHandler.sendEmptyMessage(0);


        // Menginisialisasi CardView
        menuUmroh = view.findViewById(R.id.menuUmroh);
        menuHaji = view.findViewById(R.id.menuHaji);
        menuTour = view.findViewById(R.id.menuTour);
        menuLainnya = view.findViewById(R.id.menuLainnya);

        hariIniTextView = view.findViewById(R.id.hariIni);

        Date today = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String formattedDate = dateFormat.format(today);

        hariIniTextView.setText(formattedDate);


        // Menambahkan OnClickListener ke setiap CardView
        menuUmroh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memulai UmrohActivity
                Intent intent = new Intent(getActivity(), UmrohActivity.class);
                startActivity(intent);
            }
        });

        menuHaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memulai HajiActivity
                Intent intent = new Intent(getActivity(), HajiActivity.class);
                startActivity(intent);
            }
        });

        menuTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memulai TourActivity
                Intent intent = new Intent(getActivity(), TourActivity.class);
                startActivity(intent);
            }
        });

        menuLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memulai MenuLainnyaActivity (jika ada)
                Intent intent = new Intent(getActivity(), MenuLainnya.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void updateWaktuSekarang() {
        Date currentTime = new Date();
        String formattedTime =sdf.format(currentTime);
        waktuSekarang.setText(formattedTime);
    }
}