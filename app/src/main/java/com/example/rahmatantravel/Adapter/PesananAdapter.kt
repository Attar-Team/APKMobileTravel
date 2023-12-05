package com.example.rahmatantravel.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.Models.PaketHomeModels;
import com.example.rahmatantravel.Models.PesananModels;
import com.example.rahmatantravel.R;
import com.example.rahmatantravel.api.pemesananResponse.PesananResponse

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale

class PesananAdapter(private val dataList: List<PesananResponse>) : RecyclerView.Adapter<PesananAdapter.PesananViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pesanan, parent, false)
        return PesananViewHolder(view)
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {

        val pesanan = dataList[position]
        holder.judulPaket.text = pesanan.judul_paket
        val originalFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val targetFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val date = originalFormat.parse(pesanan.tanggal_pemesanan)
        val formattedDate = targetFormat.format(date)
        holder.tanggalTransaksi.text = formattedDate
        holder.methodePembayaran.text = pesanan.jenis_pembayaran

        holder.statusPesanan.setTextColor(when (pesanan.status) {
            "Proses Verifikasi" -> Color.parseColor("#FFA500")
            "Gagal Verifikasi" -> Color.RED
            "Belum Lunas" -> Color.parseColor("#FF6000")
            "Lunas" -> Color.GREEN
            else -> Color.BLACK
        })
        holder.statusPesanan.text = pesanan.status
    }

    override fun getItemCount() = dataList.size

    inner class PesananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val judulPaket: TextView = itemView.findViewById(R.id.judulPaket)
        val methodePembayaran: TextView = itemView.findViewById(R.id.methodePembayaran)
        val tanggalTransaksi: TextView = itemView.findViewById(R.id.tanggalTransaksi)
        val statusPesanan: TextView = itemView.findViewById(R.id.statusPesanan)
    }
}

