package com.example.rahmatantravel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bdtopcoder.smart_slider.SliderAdapter;
import com.bdtopcoder.smart_slider.SliderItem;
import com.example.rahmatantravel.Adapter.ArtikelAdapter;
import com.example.rahmatantravel.Adapter.PaketHomeAdapter;
import com.example.rahmatantravel.Models.ArtikelModels;
import com.example.rahmatantravel.Models.PaketHomeModels;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //Recycle Artikel
    private RecyclerView artikelRecyclerView, paketRecyclerView;
    private ArtikelAdapter artikelAdapter;
    private PaketHomeAdapter paketAdapter;
    private ArrayList<ArtikelModels> artikelModelsArrayList;
    private ArrayList<PaketHomeModels> paketModelsArrayList;

    private CardView menuUmroh;
    private CardView menuHaji;
    private CardView menuTour;
    private CardView menuLainnya;
    private CardView tampilpaket;
    private TextView hariIniTextView;
    private TextView waktuSekarang;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm z", Locale.getDefault());
    private Handler timeHandler;

    private TextView selengkapnyaGallery;

    private TextView selengkapnyaArtikel;


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

        ViewPager2 viewPager2 = view.findViewById(R.id.smartSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.galeri3,"image 3"));
        sliderItems.add(new SliderItem(R.drawable.galeri1,"Image 1"));
        sliderItems.add(new SliderItem(R.drawable.galeri2,"Image 2"));
        sliderItems.add(new SliderItem(R.drawable.galeri3,"image 3"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2,3000));

        viewPager2.setCurrentItem(1);

        new SliderAdapter(this::onCLick);



        addDataPaket();
        paketRecyclerView = (RecyclerView) view.findViewById(R.id.recycleViewPaket);
        paketAdapter = new PaketHomeAdapter(paketModelsArrayList);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);

        if (paketAdapter == null){
            paketAdapter = new PaketHomeAdapter(paketModelsArrayList);
        }
        paketRecyclerView.setLayoutManager(layoutManager1);
        paketRecyclerView.setAdapter(paketAdapter);
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

        selengkapnyaGallery = view.findViewById(R.id.selengkapnyaGallery);
        selengkapnyaArtikel = view.findViewById(R.id.selengkapnyaArtikel);

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

        selengkapnyaGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memulai MenuLainnyaActivity (jika ada)
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                startActivity(intent);
            }
        });

        selengkapnyaArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memulai MenuLainnyaActivity (jika ada)
                Intent intent = new Intent(getActivity(), ArtikelActivity.class);
                startActivity(intent);
            }
        });
//        tampilpaket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Memulai MenuLainnyaActivity (jika ada)
//                Intent intent = new Intent(getActivity(), Tampilpaket   .class);
//                startActivity(intent);
//            }
//        });
        return view;
    }
    private void updateWaktuSekarang() {
        Date currentTime = new Date();
        String formattedTime =sdf.format(currentTime);
        waktuSekarang.setText(formattedTime);
    }

    private void onCLick(int position, String title, View view) {
        Toast.makeText(requireActivity(), "Position: " + position + " Title: " + title, Toast.LENGTH_SHORT).show();
    }

    void addDataArtikel(){
        artikelModelsArrayList = new ArrayList<>();
        artikelModelsArrayList.add(new ArtikelModels("Jamaah Haji Indonesia tiba di Madinah", "Hari ini jamaah Indonesia tiba di Madinah dengan Selamat Alhamdulillah", "14 November 2023"));
        artikelModelsArrayList.add(new ArtikelModels("Jamaah Kebelet Berak", "Terdapat jamaah kebelat berak di pesawat yang dimana jamaah belum makan", "14 Juli 2023"));
        artikelModelsArrayList.add(new ArtikelModels("Jamaah Menangis Tersedu Sedu", "Beberapa jamaah menangis bahagia karena impiannya untuk umroh terwujudkan", "14 September 2023"));
        artikelModelsArrayList.add(new ArtikelModels("Covid Melanda", "Hari ini covid", "14 Januari 2023"));
    }
    void addDataPaket(){
        paketModelsArrayList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = dateFormat.parse("2023-01-21");
            Date date2 = dateFormat.parse("2023-06-10");
            Date date3 = dateFormat.parse("2023-01-04");
            Date date4 = dateFormat.parse("2023-04-08");

            paketModelsArrayList.add(new PaketHomeModels("Paket Umroh 21 Januari 2023", date1, 10, 5, 30000000));
            paketModelsArrayList.add(new PaketHomeModels("Paket Haji 10 Juni 2023", date2, 9, 5, 29000000));
            paketModelsArrayList.add(new PaketHomeModels("Paket Wisata Turki 04 Januari 2023", date3, 4, 5, 5000000));
            paketModelsArrayList.add(new PaketHomeModels("Paket Umroh 08 April 2023", date4, 12, 5, 28000000));

            PaketHomeAdapter adapter = new PaketHomeAdapter(paketModelsArrayList);
            paketRecyclerView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
            Log.e("DateError", "Error parsing date: " + e.getMessage());
        }

    }
}
