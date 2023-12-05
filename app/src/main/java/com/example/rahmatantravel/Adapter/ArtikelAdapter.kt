package com.example.rahmatantravel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide

import com.example.rahmatantravel.Models.ArtikelModels;
import com.example.rahmatantravel.R;
import com.example.rahmatantravel.api.artikelResponse.ArtikelResponse

import kotlin.collections.ArrayList


class ArtikelAdapter(private var dataList: List<ArtikelResponse>) : RecyclerView.Adapter<ArtikelAdapter.ArtikelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtikelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_artikel, parent, false)
        return ArtikelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtikelViewHolder, position: Int) {
        holder.judulArtikel.text = dataList[position].judulArtikel
        holder.deskripsiArtikel.text = dataList[position].isiArtikel
        Glide.with(holder.gambarArtikel)
            .load("https://rahmatanumrah.000webhostapp.com/uploads/foto_artikel/${dataList[position].foto}")
            .into(holder.gambarArtikel)
    }

    override fun getItemCount(): Int {
        return dataList.size ?: 0
    }

    class ArtikelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var judulArtikel: TextView = itemView.findViewById(R.id.judulArtikel)
        var deskripsiArtikel: TextView = itemView.findViewById(R.id.deskripsiArtikel)
        var gambarArtikel : ImageView = itemView.findViewById(R.id.gambarArtikel)
    }
}

