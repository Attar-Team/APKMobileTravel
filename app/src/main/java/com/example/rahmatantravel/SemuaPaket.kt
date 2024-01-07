package com.example.rahmatantravel

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rahmatantravel.Adapter.PaketAdapter
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.paketResponse.APIResponse
import com.example.rahmatantravel.api.paketResponse.PaketResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class SemuaPaket : AppCompatActivity() {

    private lateinit var recycleSemuaPaket: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var backToMenuLainnya: ImageView
    private lateinit var imageView: ImageView
    private lateinit var adapter: PaketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semua_paket)

        initializeViews()
        setUpSearchView()

        val dialog = createLoadingDialog()
        dialog.show()

        RetrofitClient.instance.getPaket().enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.isSuccessful) {
                    handleSuccessfulResponse(response.body(), dialog)
                } else {
                    handleErrorResponse(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                handleFailure(t.message)
            }
        })
    }

    private fun initializeViews() {
        searchView = findViewById(R.id.searchView)
        val searchSrcText = searchView.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        searchSrcText.textSize = 12f
        searchSrcText.setTextColor(0xFF808080.toInt())
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        backToMenuLainnya = findViewById(R.id.backToMenuLainnya)
        backToMenuLainnya.setOnClickListener {
            onBackPressed()
        }

        recycleSemuaPaket = findViewById(R.id.recyclerView)
        imageView = findViewById(R.id.imageView)
        imageView.visibility = View.GONE
    }

    private fun setUpSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                filterList(query)
                return true
            }
        })
    }

    private fun createLoadingDialog(): Dialog {
        val dialog = Dialog(this@SemuaPaket)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    private fun handleSuccessfulResponse(apiResponse: APIResponse?, dialog: Dialog) {
        dialog.dismiss()
        if (apiResponse != null && apiResponse.data.isNotEmpty()) {
            imageView.visibility = View.GONE
            recycleSemuaPaket.visibility = View.VISIBLE

            adapter = PaketAdapter(
                apiResponse.data,
                apiResponse.data.flatMap { it.paket },
                apiResponse.data.flatMap { it.paket.flatMap { it.hotel } }
            )

            setUpAdapterListeners(apiResponse)
            setUpRecyclerView()

        } else {
            imageView.visibility = View.VISIBLE
            recycleSemuaPaket.visibility = View.GONE
        }
    }

    private fun setUpAdapterListeners(apiResponse: APIResponse) {
        adapter.onItemClick = { keberangkatanItem, paketItem, hotelItem ->
            val intent = Intent(this@SemuaPaket, DetailPaket::class.java)
            intent.putExtra("keberangkatan_id", keberangkatanItem.id.toString())
            intent.putExtra("paket_id", paketItem.id_paket.toString())
            startActivity(intent)
        }
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this@SemuaPaket, LinearLayoutManager.VERTICAL, false)
        recycleSemuaPaket.layoutManager = layoutManager
        recycleSemuaPaket.adapter = adapter
        Log.d("recyclerView", "RecyclerView berhasil dibuat")
    }

    private fun handleErrorResponse(errorBody: String?) {
        Log.e("Error body", "onResponse: Error Body: $errorBody")
    }

    private fun handleFailure(errorMessage: String?) {
        Log.d("onFailure", "onFailure: $errorMessage")
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val isCaseSensitive = false // Setel true jika ingin case-sensitive, false jika case-insensitive
            val filteredList = mutableListOf<PaketResponse>()

            val paket = adapter.getPaketResponse()
            val hotel = adapter.getHotelResponse()
            val keberangkatan = adapter.getKeberangkatanResponse()

            for (i in paket) {
                val titleToMatch = if (isCaseSensitive) i.nama_paket else i.nama_paket.toLowerCase(Locale.ROOT)
                val queryToMatch = if (isCaseSensitive) query else query.toLowerCase(Locale.ROOT)

                if (titleToMatch.contains(queryToMatch)) {
                    filteredList.add(i)
                }
            }

            adapter.setFilteredList(keberangkatan, filteredList, hotel)
        }
    }
}

