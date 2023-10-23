package com.example.rahmatantravel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PesananFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PesananFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView textProses;
    TextView textBelumLunas;
    TextView textLunas;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PesananFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PesananFragment newInstance(String param1, String param2) {
        PesananFragment fragment = new PesananFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_pesanan, container, false);

        TabLayout tabLayout = rootView.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = rootView.findViewById(R.id.viewPager2);

        FragmentPesananAdapter adapter = new FragmentPesananAdapter(getChildFragmentManager(), getLifecycle());
        tabLayout.addTab(tabLayout.newTab().setText("Proses"));
        tabLayout.addTab(tabLayout.newTab().setText("Belum Lunas"));
        tabLayout.addTab(tabLayout.newTab().setText("Lunas"));

        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not implemented yet
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not implemented yet
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        return rootView;

    }

//    private void onTextViewClick(TextView clickedTextView) {
//        // Reset warna teks ke hitam untuk semua TextView
//        textProses.setTextColor(getResources().getColor(R.color.black));
//        textBelumLunas.setTextColor(getResources().getColor(R.color.black));
//        textLunas.setTextColor(getResources().getColor(R.color.black));
//
//        // Setel warna teks menjadi oranye untuk TextView yang diklik
//        clickedTextView.setTextColor(getResources().getColor(R.color.orange));
//
//        // Selanjutnya, Anda dapat memanggil metode untuk mengganti fragment yang sesuai di sini.
//        // Contoh: replaceFragment(new ProsesFragment());
//        Fragment fragmentToReplace = null;
//        if (clickedTextView == textProses) {
//            fragmentToReplace = new prosesPesanan();
//        } else if (clickedTextView == textBelumLunas) {
//            fragmentToReplace = new belumLunasPesanan();
//        } else if (clickedTextView == textLunas) {
//            fragmentToReplace = new lunasPesanan();
//        }
//        if (fragmentToReplace != null) {
//            replaceFragment(fragmentToReplace);
//        }
//    }

//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getChildFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        // Animasi masuk (fragment baru muncul)
//        fragmentTransaction.setCustomAnimations(R.anim.slide_in_pesanan, R.anim.fade_out); // Atur animasi sesuai dengan preferensi Anda
//
//        // Animasi keluar (fragment lama menghilang)
//        fragmentTransaction.setCustomAnimations(R.anim.slide_in_pesanan, R.anim.fade_out); // Atur animasi sesuai dengan preferensi Anda
//
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
}