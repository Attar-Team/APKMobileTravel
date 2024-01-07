package com.example.rahmatantravel.Adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rahmatantravel.Models.PaketModels
import com.example.rahmatantravel.R
import com.example.rahmatantravel.api.paketResponse.APIResponse
import com.example.rahmatantravel.api.paketResponse.HotelResponse
import com.example.rahmatantravel.api.paketResponse.KeberangkatanResponse
import com.example.rahmatantravel.api.paketResponse.PaketResponse
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PaketAdapter(private var keberangkatanResponse: List<KeberangkatanResponse>,
                   private var paketResponse: List<PaketResponse>,
                   private var hotelResponse: List<HotelResponse>) : RecyclerView.Adapter<PaketAdapter.PaketViewHolder>() {

    var onItemClick : ((KeberangkatanResponse, PaketResponse, HotelResponse) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaketViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_paket, parent, false)
        return PaketViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaketViewHolder, position: Int) {
        val keberangkatanItem = keberangkatanResponse[position]
        val paketItem = paketResponse[position]
        val hotelItem = hotelResponse[position]
        holder.bind(keberangkatanItem, paketItem, hotelItem)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(keberangkatanItem, paketItem, hotelItem)
        }
    }

    override fun getItemCount(): Int {
        return minOf(keberangkatanResponse.size, paketResponse.size, hotelResponse.size)
    }

    fun setFilteredList(keberangkatanResponse: List<KeberangkatanResponse>, paketResponse: List<PaketResponse>, hotelResponse: List<HotelResponse>) {
        this.keberangkatanResponse = keberangkatanResponse
        this.paketResponse = paketResponse
        this.hotelResponse = hotelResponse
        notifyDataSetChanged()
    }

    fun getKeberangkatanResponse(): List<KeberangkatanResponse> {
        return keberangkatanResponse
    }

    fun getPaketResponse(): List<PaketResponse> {
        return paketResponse
    }

    fun getHotelResponse(): List<HotelResponse> {
        return hotelResponse
    }

    inner class PaketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val judulPaket: TextView = itemView.findViewById(R.id.judulPaket)
        val tanggalBerangkat: TextView = itemView.findViewById(R.id.tanggalBerangkat)
        val waktuPaket: TextView = itemView.findViewById(R.id.waktuPaket)
        val rateHotel: TextView = itemView.findViewById(R.id.rateHotel)
        val hargaPaket: TextView = itemView.findViewById(R.id.hargaPaket)
        val gambarPaket: ImageView = itemView.findViewById(R.id.brosurPaket)

        fun bind(keberangkatanResponse: KeberangkatanResponse, paketResponse: PaketResponse, hotelResponse: HotelResponse){
            val tanggalPaketFormatted = formatDate(keberangkatanResponse.tanggal)

            val harga = formatCurrency(paketResponse.harga[0].harga)

            judulPaket.text = paketResponse.nama_paket
            tanggalBerangkat.text = tanggalPaketFormatted
            waktuPaket.text = paketResponse.lama_hari.toString()
            rateHotel.text = hotelResponse.bintang.toString()
            hargaPaket.text = "Mulai dari $harga"

            Glide.with(itemView.context)
                .load("https://rahmatanumrah.000webhostapp.com/uploads/foto_brosur/${paketResponse.foto_paket}")
                .into(gambarPaket)
        }

        fun formatDate(inputDate: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val date: Date = inputFormat.parse(inputDate) ?: Date()
            return outputFormat.format(date)
        }

        fun formatCurrency(number: Int): String {
            val format = NumberFormat.getCurrencyInstance(Locale("id", "ID")) // Locale untuk Indonesia
            return format.format(number)
        }
    }
}

