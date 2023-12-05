package com.example.rahmatantravel

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
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
import com.example.rahmatantravel.api.artikelResponse.APIArtikel
import com.example.rahmatantravel.api.paketResponse.APIResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.text.SimpleDateFormat
import java.util.*

class HomeFragmentK : Fragment() {

    private lateinit var artikelRecyclerView: RecyclerView
    private lateinit var paketRecyclerView: RecyclerView
    private lateinit var artikelAdapter: ArtikelAdapter
    private lateinit var artikelModelsArrayList: ArrayList<ArtikelModels>
    private lateinit var menuUmroh: CardView
    private lateinit var menuHaji: CardView
    private lateinit var menuTour: CardView
    private lateinit var menuLainnya: CardView
    private lateinit var selengkapnyaGallery: TextView
    private lateinit var selengkapnyaArtikel: TextView
    private lateinit var selengkapnyaPaket: TextView
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

        artikelRecyclerView = view.findViewById(R.id.recycleViewArtikel)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        artikelRecyclerView.layoutManager = layoutManager

        paketRecyclerView = view.findViewById(R.id.recycleViewPaket)
        val layoutManager2 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        paketRecyclerView.layoutManager = layoutManager2

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading_home)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        RetrofitClient.instance.getArtikel().enqueue(object : Callback<APIArtikel>{
            override fun onResponse(call: Call<APIArtikel>, response: Response<APIArtikel>) {
                if (response.isSuccessful){
                    val apiResponse = response.body()
                    if (apiResponse != null) {
                        logData("Artikel Success", apiResponse.data.toString())
                        artikelAdapter = ArtikelAdapter(apiResponse.data)
                        artikelRecyclerView.adapter = artikelAdapter
                    }else{
                        logData("Artikel is null", response.code().toString())
                    }
                }else{
                    logData("Artikel Fail", response.code().toString())
                }
            }

            override fun onFailure(call: Call<APIArtikel>, t: Throwable) {
                logData("Article on Failure", t.message.toString())
            }
        })

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

                        dialog.dismiss()
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

        selengkapnyaPaket = view.findViewById(R.id.selengkapnyaPaket);
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


        selengkapnyaPaket.setOnClickListener {
            startActivity(Intent(activity, SemuaPaket::class.java))
        }
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

    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }

    private fun updateWaktuSekarang() {
        val currentTime = Date()
        val formattedTime = sdf.format(currentTime)
        waktuSekarang.text = formattedTime
    }

    private fun onCLick(position: Int, title: String) {
        Toast.makeText(requireActivity(), "Position: $position Title: $title", Toast.LENGTH_SHORT).show()
    }
}
