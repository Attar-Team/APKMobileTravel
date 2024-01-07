package com.example.rahmatantravel.Adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rahmatantravel.R
import com.example.rahmatantravel.api.Pemesanan
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DataJamaahAgenAdapter(private val dataList: List<Pemesanan>,private val onItemClick: (Pemesanan) -> Unit) :
        RecyclerView.Adapter<DataJamaahAgenAdapter.DataJamaahAgenViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataJamaahAgenViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_data_jamaah_agen, parent, false)
                return DataJamaahAgenViewHolder(view)
        }

        override fun onBindViewHolder(holder: DataJamaahAgenViewHolder, position: Int) {
                holder.namaJamaah.text = dataList[position].namaCustomer
                holder.tanggalTransaksi.text = formatDate(dataList[position].tanggalPemesanan)
                holder.jumlahKomisi.text = "Rp. ${dataList[position].komisi}"

                holder.itemView.setOnClickListener {
                        onItemClick.invoke(dataList[position])
                }
        }

        override fun getItemCount(): Int {
                return dataList.size
        }

        private fun formatDate(inputDate: String): String {
                try {
                        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                        val date = inputFormat.parse(inputDate)
                        return outputFormat.format(date)
                } catch (e: ParseException) {
                        e.printStackTrace()
                }
                return ""
        }

        inner class DataJamaahAgenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val namaJamaah: TextView = itemView.findViewById(R.id.namaJamaah)
                val tanggalTransaksi: TextView = itemView.findViewById(R.id.tanggalTransaksi)
                val jumlahKomisi: TextView = itemView.findViewById(R.id.jumlahKomisi)
        }
}

