package com.example.rahmatantravel.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rahmatantravel.R
import com.example.rahmatantravel.api.customerResponse.DataCustomerResponse

class DataCustomerAdapter(private val dataCustomer: List<DataCustomerResponse>) :
    RecyclerView.Adapter<DataCustomerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewItem: TextView = itemView.findViewById(R.id.textNama)

        fun bind(dataCustomerResponse: DataCustomerResponse){
            textViewItem.text = dataCustomerResponse.nama
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_customer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataCustomer[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return dataCustomer.size
    }
}
