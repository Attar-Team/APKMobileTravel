package com.example.rahmatantravel.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rahmatantravel.R;
import com.example.rahmatantravel.activity.adapter.SurahAdapter;
import com.example.rahmatantravel.databinding.ActivityQuranBinding;
import com.example.rahmatantravel.model.DataSurahItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class QuranActivity extends AppCompatActivity implements IViewSurah {
    private SurahPresenter presenter;
    private SurahAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ActivityQuranBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuranBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SetCollapsing();
        presenter = new SurahPresenter(this, this);
        presenter.onGetDetailSurah();
    }
    private void SetCollapsing() {
        binding.collapsingToolbar.setTitle(getString(R.string.alquran));
        binding.collapsingToolbar.setCollapsedTitleTextColor(this.getResources().getColor(R.color.redOrange));
        binding.collapsingToolbar.setExpandedTitleColor(this.getResources().getColor(R.color.transparant));
    }
    private void SetListSurah(List<DataSurahItem> listSurah) {
        layoutManager = new LinearLayoutManager(this);
        adapter = new SurahAdapter(listSurah, this);
        binding.rvListSurah.setHasFixedSize(true);
        binding.rvListSurah.setLayoutManager(layoutManager);
        binding.rvListSurah.setAdapter(adapter);
    }
    @Override
    public void getDetailSurah(List<DataSurahItem> data) {
        SetListSurah(data);
    }

    @Override
    public void onErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
