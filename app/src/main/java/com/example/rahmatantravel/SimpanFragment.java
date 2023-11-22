package com.example.rahmatantravel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.rahmatantravel.Adapter.PaketAdapter;
import com.example.rahmatantravel.Adapter.SimpanAdapter;
import com.example.rahmatantravel.Models.PaketModels;
import com.example.rahmatantravel.Models.SimpanModels;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpanFragment extends Fragment {
    private RecyclerView recycleSimpan;
    private SimpanAdapter simpanAdapter;
    private ArrayList<SimpanModels> simpanModelsArrayList;
    private SearchView searchView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SimpanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimpanFragment newInstance(String param1, String param2) {
        SimpanFragment fragment = new SimpanFragment();
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
        View view = inflater.inflate(R.layout.fragment_simpan, container, false);
        addDataSimpan();
        recycleSimpan = (RecyclerView) view.findViewById(R.id.recycleSimpan);
        simpanAdapter = new SimpanAdapter(simpanModelsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        if (simpanAdapter == null){
            simpanAdapter = new SimpanAdapter(simpanModelsArrayList);
        }
        recycleSimpan.setLayoutManager(layoutManager);
        recycleSimpan.setAdapter(simpanAdapter);

        return view;
    }
    void addDataSimpan(){
        simpanModelsArrayList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = dateFormat.parse("2023-01-21");
            Date date2 = dateFormat.parse("2023-06-10");
            Date date3 = dateFormat.parse("2023-01-04");
            Date date4 = dateFormat.parse("2023-04-08");

            simpanModelsArrayList.add(new SimpanModels("Paket Umroh 21 Januari 2023", date1, 10, 5, 30000000));
            simpanModelsArrayList.add(new SimpanModels("Paket Haji 10 Juni 2023", date2, 9, 5, 29000000));
            simpanModelsArrayList.add(new SimpanModels("Paket Wisata Turki 04 Januari 2023", date3, 4, 5, 5000000));
            simpanModelsArrayList.add(new SimpanModels("Paket Umroh 08 April 2023", date4, 12, 5, 28000000));

            SimpanAdapter adapter = new SimpanAdapter(simpanModelsArrayList);
            recycleSimpan.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("DateError", "Error parsing date: " + e.getMessage());
        }
    }
}