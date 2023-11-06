package com.example.rahmatantravel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bdtopcoder.smart_slider.SliderAdapter
import com.bdtopcoder.smart_slider.SliderItem
import com.example.rahmatantravel.Adapter.ArtikelAdapter
import com.example.rahmatantravel.Adapter.PaketHomeAdapter
import com.example.rahmatantravel.Models.ArtikelModels
import com.example.rahmatantravel.Models.PaketHomeModels
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.paketResponse.APIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HomeFragmentK : Fragment() {

    private lateinit var artikelRecyclerView: RecyclerView
    private lateinit var paketRecyclerView: RecyclerView
    private lateinit var artikelAdapter: ArtikelAdapter
    private lateinit var paketAdapter: PaketHomeAdapter
    private lateinit var artikelModelsArrayList: ArrayList<ArtikelModels>
    private lateinit var paketModelsArrayList: ArrayList<PaketHomeModels>
    private lateinit var menuUmroh: CardView
    private lateinit var menuHaji: CardView
    private lateinit var menuTour: CardView
    private lateinit var menuLainnya: CardView
    private lateinit var selengkapnyaGallery: TextView
    private lateinit var selengkapnyaArtikel: TextView
    private lateinit var hariIniTextView: TextView
    private lateinit var waktuSekarang: TextView
    private lateinit var sdf: SimpleDateFormat
    private lateinit var timeHandler: Handler

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_k, container, false)
        waktuSekarang = view.findViewById(R.id.waktuSekarang)
        sdf = SimpleDateFormat("HH:mm z", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        val viewPager2: ViewPager2 = view.findViewById(R.id.smartSlider)
        val sliderItems = arrayListOf(
            SliderItem(R.drawable.galeri3, "image 3"),
            SliderItem(R.drawable.galeri1, "Image 1"),
            SliderItem(R.drawable.galeri2, "Image 2"),
            SliderItem(R.drawable.galeri3, "image 3")
        )

        viewPager2.adapter = SliderAdapter(sliderItems, viewPager2, 3000)
        viewPager2.currentItem = 1
        SliderAdapter { position, title, _ ->
            onCLick(position, title)
        }
        addDataArtikel()
        artikelRecyclerView = view.findViewById(R.id.recycleViewArtikel)
        artikelAdapter = ArtikelAdapter(artikelModelsArrayList)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        artikelRecyclerView.layoutManager = layoutManager
        artikelRecyclerView.adapter = artikelAdapter

        paketRecyclerView = view.findViewById(R.id.recycleViewPaket)
        val layoutManager2 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        paketRecyclerView.layoutManager = layoutManager2

        RetrofitClient.instance.getPaket().enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {

                val responseCode = response.code().toString()

                if (response.isSuccessful){
                    Log.d("responseCode", responseCode)
                    val apiResponse = response.body()
                    if (apiResponse != null) {

                        Log.d("Data API", apiResponse.toString())
                        Log.d("Data Paket", apiResponse.data.flatMap { it.paket }.toString())
                        Log.d("Data Hotel", apiResponse.data.flatMap { it.paket.flatMap { it.hotel } }.toString())

                        val adapter = com.example.rahmatantravel.Adapter.PaketViewAdapter(
                            apiResponse.data,
                            apiResponse.data.flatMap { it.paket },
                            apiResponse.data.flatMap { it.paket.flatMap { it.hotel } }
                        )

                        Log.d("ID Keberangkatan", apiResponse.data.map { it.id }.toString())


                        adapter.onItemClick = { keberangkatanItem, paketItem, hotelItem ->
                            val intent = Intent(requireContext(), DetailPaket::class.java)
                            intent.putExtra("keberangkatan_id", keberangkatanItem.id.toString())
                            intent.putExtra("paket_id", paketItem.id_paket.toString())
                            startActivity(intent)
                        }

                        paketRecyclerView.adapter = adapter
                        Log.d("recyclerView", "RecyclerView bersasil dibuat")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("Error body", "onResponse: Error Body: $errorBody")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ${t.message}", t)

                if (t is retrofit2.HttpException) {
                    val errorBody = t.response()?.errorBody()?.string()
                    Log.e("MainActivity", "onFailure: Error Body: $errorBody")
                }
            }

        })

        timeHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                updateWaktuSekarang()
                sendEmptyMessageDelayed(0, 1000)
            }
        }
        timeHandler.sendEmptyMessage(0)

        menuUmroh = view.findViewById(R.id.menuUmroh)
        menuHaji = view.findViewById(R.id.menuHaji)
        menuTour = view.findViewById(R.id.menuTour)
        menuLainnya = view.findViewById(R.id.menuLainnya)
        selengkapnyaGallery = view.findViewById(R.id.selengkapnyaGallery)
        selengkapnyaArtikel = view.findViewById(R.id.selengkapnyaArtikel)
        hariIniTextView = view.findViewById(R.id.hariIni)
        val today = Date()
        val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        val formattedDate: String = dateFormat.format(today)
        hariIniTextView.text = formattedDate

        menuUmroh.setOnClickListener {
            startActivity(Intent(activity, UmrohActivity::class.java))
        }

        menuHaji.setOnClickListener {
            startActivity(Intent(activity, HajiActivity::class.java))
        }

        menuTour.setOnClickListener {
            startActivity(Intent(activity, TourActivity::class.java))
        }

        menuLainnya.setOnClickListener {
            startActivity(Intent(activity, MenuLainnya::class.java))
        }

        selengkapnyaGallery.setOnClickListener {
            startActivity(Intent(activity, GalleryActivity::class.java))
        }

        selengkapnyaArtikel.setOnClickListener {
            startActivity(Intent(activity, ArtikelActivity::class.java))
        }

        return view
    }

    private fun updateWaktuSekarang() {
        val currentTime = Date()
        val formattedTime = sdf.format(currentTime)
        waktuSekarang.text = formattedTime
    }

    private fun onCLick(position: Int, title: String) {
        Toast.makeText(requireActivity(), "Position: $position Title: $title", Toast.LENGTH_SHORT).show()
    }

    private fun addDataArtikel() {
        artikelModelsArrayList = ArrayList()
        artikelModelsArrayList.add(ArtikelModels("Jamaah Haji Indonesia tiba di Madinah", "Hari ini jamaah Indonesia tiba di Madinah dengan Selamat Alhamdulillah", "14 November 2023"))
        artikelModelsArrayList.add(ArtikelModels("Jamaah Kebelet Berak", "Terdapat jamaah kebelat berak di pesawat yang dimana jamaah belum makan", "14 Juli 2023"))
        artikelModelsArrayList.add(ArtikelModels("Jamaah Menangis Tersedu Sedu", "Beberapa jamaah menangis bahagia karena impiannya untuk umroh terwujudkan", "14 September 2023"))
        artikelModelsArrayList.add(ArtikelModels("Covid Melanda", "Hari ini covid", "14 Januari 2023"))
    }
}