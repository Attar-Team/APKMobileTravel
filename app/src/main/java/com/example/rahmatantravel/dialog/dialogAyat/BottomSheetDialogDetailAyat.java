package com.example.rahmatantravel.dialog.dialogAyat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.R;
import com.example.rahmatantravel.databinding.BottomSheetDialogBinding;
import com.example.rahmatantravel.dialog.dialogAyat.adapter.AyatAdapter;
import com.example.rahmatantravel.model.DataAyatItem;
import com.example.rahmatantravel.model.DataSurahItem;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class BottomSheetDialogDetailAyat extends BottomSheetDialog implements IViewAyat {
    private Context context;
    private AyatAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DataSurahItem dataSurah;
    private IViewAyat iView;
    private AyatPresenter presenter;
    private BottomSheetDialogBinding binding;

    public BottomSheetDialogDetailAyat(@NonNull Context context, DataSurahItem dataSurahItem) {
        super(context, R.style.BottomSheetDialogTheme);
        this.context = context;
        this.iView = this;
        this.dataSurah = dataSurahItem;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = BottomSheetDialogBinding.inflate(LayoutInflater.from(context));
        setContentView(binding.getRoot());
        SetContent();
        presenter = new AyatPresenter(iView, (Activity) context);
        presenter.onGetDetailAyat(dataSurah.getNomor());
    }

    private void SetContent() {
        binding.tvNameSurahArab.setText(dataSurah.getAsma());
        binding.tvNameSurah.setText(dataSurah.getNama());
        binding.tvKota.setText(dataSurah.getType());
        binding.tvNomorAyat.setText(String.valueOf(dataSurah.getAyat()));
    }

    @Override
    public void onGetDataAyat(List<DataAyatItem> data) {
        SetListAyat(data);
    }

    @Override
    public void onErrorMsg(String msg) {
        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
    }

    private void SetListAyat(List<DataAyatItem> data) {
        layoutManager = new LinearLayoutManager(context);
        adapter = new AyatAdapter(context, data);
        binding.rvListAyat.setHasFixedSize(true);
        binding.rvListAyat.setLayoutManager(layoutManager);
        binding.rvListAyat.setAdapter(adapter);
    }
}
