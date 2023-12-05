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

class PesananFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_pesanan, container, false)

        val tabLayout = rootView.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = rootView.findViewById<ViewPager2>(R.id.viewPager2)

        val adapter = FragmentPesananAdapter(childFragmentManager, lifecycle)
        tabLayout.addTab(tabLayout.newTab().setText("Belum Lunas"))
        tabLayout.addTab(tabLayout.newTab().setText("Lunas"))

        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Not implemented yet
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Not implemented yet
            }
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        return rootView
    }
}
