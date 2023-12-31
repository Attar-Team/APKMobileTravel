package com.example.rahmatantravel.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rahmatantravel.R
import com.example.rahmatantravel.api.paketResponse.HotelResponse
import com.example.rahmatantravel.api.paketResponse.KeberangkatanResponse
import com.example.rahmatantravel.api.paketResponse.PaketResponse
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PaketViewAdapter (private val keberangkatanResponse: List<KeberangkatanResponse>,
                        private val paketResponse: List<PaketResponse>,
                        private val hotelResponse: List<HotelResponse>) :
    RecyclerView.Adapter<PaketViewAdapter.PaketHomeViewHolder>() {

    var onItemClick : ((KeberangkatanResponse, PaketResponse, HotelResponse) -> Unit)? = null

        inner class PaketHomeViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
            val judulPaket = itemView.findViewById<TextView>(R.id.judulPaket)
            val tanggalBerangkat = itemView.findViewById<TextView>(R.id.tanggalBerangkat)
            val waktuPaket = itemView.findViewById<TextView>(R.id.waktuPaket)
            val rateHotel = itemView.findViewById<TextView>(R.id.rateHotel)
            val hargaPaket = itemView.findViewById<TextView>(R.id.hargaPaket)
            val gambarPaket = itemView.findViewById<ImageView>(R.id.brosurPaket)

            fun bind(keberangkatanResponse: KeberangkatanResponse, paketResponse: PaketResponse, hotelResponse: HotelResponse){
                val tanggalPaketFormatted = formatDate(keberangkatanResponse.tanggal)

                val harga = formatCurrency(paketResponse.harga[0].harga)
                val status = keberangkatanResponse.status

                judulPaket.text = paketResponse.nama_paket
                tanggalBerangkat.text = tanggalPaketFormatted
                waktuPaket.text = paketResponse.lama_hari.toString()
                rateHotel.text = status
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaketHomeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_pakethome, parent, false)
        return PaketHomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return minOf(keberangkatanResponse.size, paketResponse.size, hotelResponse.size)
    }

    override fun onBindViewHolder(holder: PaketHomeViewHolder, position: Int) {
        val keberangkatanItem = keberangkatanResponse[position]
        val paketItem = paketResponse[position]
        val hotelItem = hotelResponse[position]
        holder.bind(keberangkatanItem, paketItem, hotelItem)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(keberangkatanItem, paketItem, hotelItem)
        }
    }
}