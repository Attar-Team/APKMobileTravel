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

class prosesPesanan : Fragment() {

    private lateinit var recyclerProsesPesanan: RecyclerView
    private lateinit var pesananAdapter: PesananAdapter
    private lateinit var pesananModelsArrayList: ArrayList<PesananModels>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_proses_pesanan, container, false)

        return view
    }
}
