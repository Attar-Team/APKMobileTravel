package com.example.rahmatantravel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView

import com.example.rahmatantravel.Adapter.PesananAdapter;
import com.example.rahmatantravel.Models.PesananModels;
import com.example.rahmatantravel.SharedPref.PrefManager
import com.example.rahmatantravel.api.RetrofitClient
import com.example.rahmatantravel.api.pemesananResponse.APIPesanan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList;

class belumLunasPesanan : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_belum_lunas_pesanan, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        imageView = view.findViewById(R.id.imageView)

        // Sembunyikan imageView di awal
        imageView.visibility = View.GONE

        val prefManager = PrefManager(requireContext())
        val loginData = prefManager.getLoginData()

        val userID = loginData.first ?.toInt()
        logData("User ID", userID.toString())

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        RetrofitClient.instance.getPemesananByStatus("belumLunas", "$userID").enqueue(
            object  : Callback<APIPesanan> {
                override fun onResponse(
                    call: Call<APIPesanan>,
                    response: Response<APIPesanan>
                ) {
                    if (response.isSuccessful){
                        if (response.body() != null){
                            val responseBody = response.body()
                            logData("Response Success", responseBody ?.data.toString())
                            val data = responseBody ?.data
                            if (data.isNullOrEmpty()) {
                                // Jika data kosong, tampilkan imageView dan sembunyikan recyclerView
                                imageView.visibility = View.VISIBLE
                                recyclerView.visibility = View.GONE
                            } else {
                                // Jika data tidak kosong, tampilkan recyclerView dan sembunyikan imageView
                                imageView.visibility = View.GONE
                                recyclerView.visibility = View.VISIBLE
                                // Update recyclerView dengan data
                                recyclerView = view.findViewById(R.id.recyclerView)
                                imageView = view.findViewById(R.id.imageView)

                                // Sembunyikan imageView di awal
                                imageView.visibility = View.GONE
                                val pesananAdapter = data?.let { PesananAdapter(it) }
                                recyclerView.layoutManager = layoutManager
                                recyclerView.adapter = pesananAdapter
                            }
                        }else{
                            logData("Response Body Null", "Body is null")
                        }
                    }else{
                        val responseBody = response.body()
                        logData("Response Failed", response.message())
                        logData("Response Failed", responseBody ?.status.toString())
                    }
                }

                override fun onFailure(call: Call<APIPesanan>, t: Throwable) {
                    logData("Response on Failure", t.message.toString())
                }

            }
        )

        return view
    }

    private fun logData(tag: String, data: String) {
        Log.d(tag, "$tag: $data")
    }
}
