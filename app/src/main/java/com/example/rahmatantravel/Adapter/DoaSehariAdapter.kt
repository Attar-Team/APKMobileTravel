package com.example.rahmatantravel.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rahmatantravel.Models.DoaSehariModels
import com.example.rahmatantravel.R
import com.example.rahmatantravel.Adapter.DoaSehariAdapter.ListViewHolder


class DoaSehariAdapter(private val modelBacaan: MutableList<DoaSehariModels>) :
    RecyclerView.Adapter<DoaSehariAdapter.ListViewHolder>(), Filterable {

    private val modelBacaanListFull: List<DoaSehariModels>

    init {
        modelBacaanListFull = ArrayList(modelBacaan)
    }

    override fun getFilter():Filter{
        return modelBacaanFilter
    }
    private val modelBacaanFilter: Filter = object :  Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults{
            val filteredList: MutableList<DoaSehariModels> = ArrayList()
            if (constraint == null || constraint.length == 0){
                filteredList.addAll(modelBacaanListFull)
            }else{
                val filterPattern = constraint.toString().toLowerCase()
                for (modelDoaFilter in modelBacaanListFull){
                    val title = modelDoaFilter.strTitle
                    if (title != null && title.toLowerCase().contains(filterPattern)){
                        filteredList.add(modelDoaFilter)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults?) {
            modelBacaan.clear()
            if (results != null){
                modelBacaan.addAll(results.values as Collection<DoaSehariModels>)
            }
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_doa_sehari,viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val dataModel = modelBacaan[i]
        listViewHolder.tvId.text = dataModel.strId
        listViewHolder.tvTitle.text = dataModel.strTitle
        listViewHolder.tvArabic.text = dataModel.strArabic
        listViewHolder.tvLatin.text = dataModel.strLatin
        listViewHolder.tvTerjemahan.text = dataModel.strTranslation
    }

    override fun getItemCount(): Int {
        return modelBacaan.size
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvId: TextView
        var tvTitle: TextView
        var tvArabic: TextView
        var tvLatin: TextView
        var tvTerjemahan: TextView

        init {
            tvId = itemView.findViewById(R.id.tvId)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvArabic = itemView.findViewById(R.id.tvArabic)
            tvLatin = itemView.findViewById(R.id.tvLatin)
            tvTerjemahan = itemView.findViewById(R.id.tvTerjemahan)
        }
    }
}