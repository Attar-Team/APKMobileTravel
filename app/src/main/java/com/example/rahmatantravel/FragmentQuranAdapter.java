package com.example.rahmatantravel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentQuranAdapter extends FragmentStateAdapter {
    public FragmentQuranAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SurahQuran();
            case 1:
                return new JuzQuran();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Jumlah tab (Jus dan Surah)
    }
}