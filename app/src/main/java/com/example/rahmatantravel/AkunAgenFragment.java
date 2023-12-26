package com.example.rahmatantravel;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AkunAgenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AkunAgenFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AkunAgenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AkunAgenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AkunAgenFragment newInstance(String param1, String param2) {
        AkunAgenFragment fragment = new AkunAgenFragment();
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
        View view = inflater.inflate(R.layout.fragment_akun_agen, container, false);
        LinearLayout undangjamaah = view.findViewById(R.id.undangjamaah);
        LinearLayout dompet = view.findViewById(R.id.dompet);
        LinearLayout informasijamaah = view.findViewById(R.id.informasiJamaah);
        LinearLayout ubahPW = view.findViewById(R.id.ubahPW);
        LinearLayout ubahProfile = view.findViewById(R.id.ubahProfile);
        LinearLayout logout = view.findViewById(R.id.logout);

        undangjamaah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UndangJamaah.class);
                startActivity(intent);
            }
        });
        dompet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Dompet.class);
                startActivity(intent);
            }
        });
        informasijamaah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataJamaahAgen.class);
                startActivity(intent);
            }
        });
        ubahPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UbahKataSandi.class);
                startActivity(intent);
            }
        });
        ubahProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UbahProfile.class);
                startActivity(intent);
            }
        });
        return view;
    }
}