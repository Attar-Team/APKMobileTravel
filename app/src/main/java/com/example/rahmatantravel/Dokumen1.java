package com.example.rahmatantravel;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dokumen1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dokumen1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private OnNextButtonClickListener onNextButtonClickListener;
//
//    public interface OnNextButtonClickListener{
//        void onNextButtonClicked();
//    }
//    public void setOnNextButtonClickListener(OnNextButtonClickListener listener){
//        this.onNextButtonClickListener = listener;
//    }
//    private void onNextButtonClick(){
//        if(onNextButtonClickListener != null){
//            onNextButtonClickListener.onNextButtonClicked();
//        }
//    }



    public Dokumen1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dokumen1.
     */
    // TODO: Rename and change types and number of parameters
    public static Dokumen1 newInstance(String param1, String param2) {
        Dokumen1 fragment = new Dokumen1();
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
        View view = inflater.inflate(R.layout.fragment_dokumen1, container, false);


        CheckBox checkbox1 = view.findViewById(R.id.cbLaki);
        CheckBox checkbox2 = view.findViewById(R.id.cbPerempuan);
        CheckBox cbS       = view.findViewById(R.id.cbS);
        CheckBox cbM       = view.findViewById(R.id.cbM);
        CheckBox cbL       = view.findViewById(R.id.cbL);
        CheckBox cbXL      = view.findViewById(R.id.cbXL);
        CheckBox cbXXL     = view.findViewById(R.id.cbXXL);
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Jika Checkbox 1 terpilih, nonaktifkan Checkbox 2
                checkbox2.setEnabled(!isChecked);

                // Tambahan: Lakukan sesuatu jika Checkbox 1 berubah status
                if (isChecked) {
                    // Lakukan sesuatu ketika Checkbox 1 terpilih
                } else {
                    // Lakukan sesuatu ketika Checkbox 1 tidak terpilih
                }
            }
        });

        // Inisialisasi listener untuk Checkbox 2
        checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Jika Checkbox 2 terpilih, nonaktifkan Checkbox 1
                checkbox1.setEnabled(!isChecked);

                // Tambahan: Lakukan sesuatu jika Checkbox 2 berubah status
                if (isChecked) {
                    // Lakukan sesuatu ketika Checkbox 2 terpilih
                } else {
                    // Lakukan sesuatu ketika Checkbox 2 tidak terpilih
                }
            }
        });

        cbS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Jika Checkbox 1 terpilih, nonaktifkan Checkbox 2
                cbM.setEnabled(!isChecked);
                cbL.setEnabled(!isChecked);
                cbXL.setEnabled(!isChecked);
                cbXXL.setEnabled(!isChecked);

                // Tambahan: Lakukan sesuatu jika Checkbox 1 berubah status
                if (isChecked) {
                    // Lakukan sesuatu ketika Checkbox 1 terpilih
                } else {
                    // Lakukan sesuatu ketika Checkbox 1 tidak terpilih
                }
            }
        });
        cbM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Jika Checkbox 1 terpilih, nonaktifkan Checkbox 2
                cbS.setEnabled(!isChecked);
                cbL.setEnabled(!isChecked);
                cbXL.setEnabled(!isChecked);
                cbXXL.setEnabled(!isChecked);

                // Tambahan: Lakukan sesuatu jika Checkbox 1 berubah status
                if (isChecked) {
                    // Lakukan sesuatu ketika Checkbox 1 terpilih
                } else {
                    // Lakukan sesuatu ketika Checkbox 1 tidak terpilih
                }
            }
        });
        cbL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Jika Checkbox 1 terpilih, nonaktifkan Checkbox 2
                cbM.setEnabled(!isChecked);
                cbS.setEnabled(!isChecked);
                cbXL.setEnabled(!isChecked);
                cbXXL.setEnabled(!isChecked);

                // Tambahan: Lakukan sesuatu jika Checkbox 1 berubah status
                if (isChecked) {
                    // Lakukan sesuatu ketika Checkbox 1 terpilih
                } else {
                    // Lakukan sesuatu ketika Checkbox 1 tidak terpilih
                }
            }
        });
        cbXL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Jika Checkbox 1 terpilih, nonaktifkan Checkbox 2
                cbM.setEnabled(!isChecked);
                cbL.setEnabled(!isChecked);
                cbS.setEnabled(!isChecked);
                cbXXL.setEnabled(!isChecked);

                // Tambahan: Lakukan sesuatu jika Checkbox 1 berubah status
                if (isChecked) {
                    // Lakukan sesuatu ketika Checkbox 1 terpilih
                } else {
                    // Lakukan sesuatu ketika Checkbox 1 tidak terpilih
                }
            }
        });
        cbXXL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Jika Checkbox 1 terpilih, nonaktifkan Checkbox 2
                cbM.setEnabled(!isChecked);
                cbL.setEnabled(!isChecked);
                cbXL.setEnabled(!isChecked);
                cbS.setEnabled(!isChecked);

                // Tambahan: Lakukan sesuatu jika Checkbox 1 berubah status
                if (isChecked) {
                    // Lakukan sesuatu ketika Checkbox 1 terpilih
                } else {
                    // Lakukan sesuatu ketika Checkbox 1 tidak terpilih
                }
            }
        });


        CardView btn_selanjutnya = view.findViewById(R.id.btn_selanjutnya);
        btn_selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout, new Dokumen2()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}