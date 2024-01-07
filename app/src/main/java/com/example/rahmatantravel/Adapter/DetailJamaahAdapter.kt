package com.example.rahmatantravel.Adapter;

import android.content.Context
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.L
import com.example.rahmatantravel.Models.DetailJamaahModels
import com.example.rahmatantravel.R
import com.example.rahmatantravel.api.customerResponse.CustomerResponse
import com.example.rahmatantravel.api.customerResponse.DataCustomerResponse
import com.example.rahmatantravel.api.paketResponse.APIResponse
import com.example.rahmatantravel.api.paketResponse.HargaPaketResponse
import com.example.rahmatantravel.api.paketResponse.PaketResponse

class DetailJamaahAdapter(
    private val context: Context,
    private val dataList: ArrayList<DetailJamaahModels>,
    private val namaJamaah: List<String>,
    private val nikJamaah : List<String>,
    private val hargaPaketResponseList: APIResponse
) : RecyclerView.Adapter<DetailJamaahAdapter.DetailJamaahViewHolder>() {

    var selectedNamaArray = ArrayList<String>()
    var selectedHargaArray = ArrayList<Int>()
    var selectedNIKArray = ArrayList<String>()
    var selectedIDHarga = ArrayList<String>()

    var totalTotalan: Int = 0

    fun getTotalHarga(): Int {
        return selectedHargaArray.sum()
    }

    fun mapNamaNIK(): Map<String, String> {
        return selectedNIKArray.zip(selectedNamaArray).toMap()
    }

    fun mapIDHarga(): Map<String, Int> {
        return selectedIDHarga.zip(selectedHargaArray).toMap()
    }

    inner class DetailJamaahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Deklarasi tampilan yang akan digunakan di dalam ViewHolder
        val urutanData: TextView = itemView.findViewById(R.id.urutanData)
        val autoComplete: AutoCompleteTextView = itemView.findViewById(R.id.autoComplete)
        val radioHarga: RadioGroup = itemView.findViewById(R.id.radioHarga)
        val buttonHapus: CardView = itemView.findViewById(R.id.buttonHapus)

        // Fungsi untuk mengikat data ke tampilan
        fun bind(detailJamaah: DetailJamaahModels, namaJamaah: List<String>, nikJamaah: List<String>) {
            urutanData.text = detailJamaah.urutanData.toString()

            val mapNamaNik = nikJamaah.zip(namaJamaah).toMap()

            // Menyiapkan adapter untuk AutoCompleteTextView
            val adapter = ArrayAdapter(context, R.layout.list_item_jamaah, nikJamaah)
            autoComplete.setAdapter(adapter)
            autoComplete.setText(detailJamaah.autoCompleteTextView, false)
            autoComplete.setOnItemClickListener { _, _, position1, _ ->
                val selectedItem = nikJamaah[position1]
                val selectedNik = mapNamaNik[selectedItem]
                detailJamaah.autoCompleteTextView = selectedItem

                val indexToUpdate = detailJamaah.urutanData - 1

                updateArrayAtIndex(selectedNamaArray, indexToUpdate, selectedItem)

                // Penggunaan fungsi untuk memperbarui selectedNIKArray
                if (selectedNik != null) {
                    updateArrayAtIndex(selectedNIKArray, indexToUpdate, selectedNik)
                }
            }


            // Menampilkan rincian paket dengan menggunakan data dari APIResponse
            displayPaketDetails(hargaPaketResponseList, detailJamaah.urutanData)

            if (dataList.size == 1) {
                buttonHapus.visibility = View.GONE
            } else {
                buttonHapus.setOnClickListener {
                    onDeleteButtonClick(detailJamaah.urutanData - 1)
                }
            }
        }

        // Metode untuk menghapus item
        fun onDeleteButtonClick(position: Int) {
            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }

        // Fungsi untuk menampilkan rincian paket pada RadioGroup
        fun displayPaketDetails(apiResponse: APIResponse, index: Int) {
            val paket = apiResponse.data[0].paket[0]

            // Membersihkan semua RadioButton yang mungkin sudah ada di RadioGroup
            radioHarga.removeAllViews()

            // Iterasi melalui array harga dan menambahkan RadioButton ke RadioGroup
            for (harga in paket.harga) {
                val radioButton = RadioButton(context)
                radioButton.text = " ${harga.jenis} \n ${harga.harga}"

                // Menetapkan listener jika diperlukan
                radioButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        handleRadioButtonSelection(paket, harga, index)
                    }
                }

                // Menambahkan RadioButton ke RadioGroup
                radioHarga.addView(radioButton)
            }
        }

        // Fungsi untuk menangani logika saat RadioButton dipilih
        private fun handleRadioButtonSelection(paket: PaketResponse, harga: HargaPaketResponse, index: Int) {
            val selectedHargaPaket = Pair(paket, harga)

            // Memanggil fungsi untuk memperbarui selectedHargaArray
            updateIntArrayAtIndex(selectedHargaArray, index - 1, selectedHargaPaket.second.harga)
            totalTotalan = selectedHargaArray.sum()
            Log.d("Total Harga", totalTotalan.toString())

            // Memanggil fungsi untuk memperbarui selectedIDHarga
            updateStringArrayAtIndex(selectedIDHarga, index - 1, selectedHargaPaket.second.hargaPaketId?.toString() ?: "")
            Log.d("Array ID Harga", selectedIDHarga.toString())
        }

        // Fungsi umum untuk memperbarui nilai pada indeks tertentu dalam array Int
        private fun updateIntArrayAtIndex(array: ArrayList<Int>, index: Int, value: Int) {
            updateArrayAtIndex(array, index, value) // Memanggil fungsi umum
        }

        // Fungsi umum untuk memperbarui nilai pada indeks tertentu dalam array String
        private fun updateStringArrayAtIndex(array: ArrayList<String>, index: Int, value: String) {
            updateArrayAtIndex(array, index, value) // Memanggil fungsi umum
        }

        // Fungsi umum untuk memperbarui nilai pada indeks tertentu dalam array
        private fun <T> updateArrayAtIndex(array: ArrayList<T>, index: Int, value: T) {
            // Memastikan indeks yang akan diperbarui sudah ada dalam batas array
            if (index in 0 until array.size) {
                // Memperbarui nilai pada indeks tertentu
                array[index] = value
            } else {
                // Menambahkan elemen-elemen nol atau nilai default sebelumnya
                while (array.size <= index) {
                    array.add(value) // Menambahkan elemen baru
                }
            }

            // Log untuk debugging
            Log.d("Updated Array", array.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailJamaahViewHolder {
        // Membuat ViewHolder baru saat dibutuhkan
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_detailjamaah, parent, false)
        return DetailJamaahViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailJamaahViewHolder, position: Int) {
        // Mengikat data ke ViewHolder
        val detailJamaah = dataList[position]
        holder.bind(detailJamaah, namaJamaah, nikJamaah)
    }

    override fun getItemCount(): Int {
        // Mengembalikan jumlah item dalam adapter
        return dataList.size
    }
}
