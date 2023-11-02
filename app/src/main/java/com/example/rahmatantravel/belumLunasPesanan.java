package com.example.rahmatantravel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rahmatantravel.Adapter.PesananAdapter;
import com.example.rahmatantravel.Models.PesananModels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link belumLunasPesanan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class belumLunasPesanan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycleBelumLunasPesanan;
    private PesananAdapter pesananAdapter2;
    private ArrayList<PesananModels> pesananModelsArrayList2;

    public belumLunasPesanan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment belumLunasPesanan.
     */
    // TODO: Rename and change types and number of parameters
    public static belumLunasPesanan newInstance(String param1, String param2) {
        belumLunasPesanan fragment = new belumLunasPesanan();
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
        View view = inflater.inflate(R.layout.fragment_belum_lunas_pesanan, container, false);
        addDataBelumLunasPesanan();
        recycleBelumLunasPesanan = (RecyclerView) view.findViewById(R.id.recycleBelumLunasPesanan);
        pesananAdapter2 = new PesananAdapter(pesananModelsArrayList2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);

        if (pesananAdapter2 == null){
            pesananAdapter2 = new PesananAdapter(pesananModelsArrayList2);
        }
        recycleBelumLunasPesanan.setLayoutManager(layoutManager);
        recycleBelumLunasPesanan.setAdapter(pesananAdapter2);

        return view;
    }
    void addDataBelumLunasPesanan(){
        pesananModelsArrayList2 = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = dateFormat.parse("2023-01-21");
            Date date2 = dateFormat.parse("2023-06-10");
            Date date3 = dateFormat.parse("2023-01-04");
            Date date4 = dateFormat.parse("2023-04-08");
            Date date5 = dateFormat.parse("2023-02-10");


            pesananModelsArrayList2.add(new PesananModels("RMTN1423132", "Paket Umroh 21 Nov 2023", date1, "BSI", "Belum Lunas"));
            pesananModelsArrayList2.add(new PesananModels("RMTN1423133", "Paket Umroh 13 Okt 2023", date2, "BSI", "Belum Lunas"));
            pesananModelsArrayList2.add(new PesananModels("RMTN1423134", "Paket Umroh 05 Des 2023", date3, "BSI", "Belum Lunas"));
            pesananModelsArrayList2.add(new PesananModels("RMTN1423135", "Paket Umroh 01 Jun 2023", date4, "BSI", "Belum Lunas"));
            pesananModelsArrayList2.add(new PesananModels("RMTN1423136", "Paket Umroh 14 Jul 2023", date5, "BSI", "Belum Lunas"));

            PesananAdapter adapter2 = new PesananAdapter(pesananModelsArrayList2);
            recycleBelumLunasPesanan.setAdapter(adapter2);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("DateError", "Error parsing date: " + e.getMessage());
        }
    }
}