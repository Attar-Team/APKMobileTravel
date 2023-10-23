package com.example.rahmatantravel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentPesananAdapter extends FragmentStateAdapter {

    public FragmentPesananAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new prosesPesanan();
        } else if (position == 1) {
            return new belumLunasPesanan();
        } else
            return new lunasPesanan();
    }
}