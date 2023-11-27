package com.example.rahmatantravel

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rahmatantravel.Adapter.DoaSehariAdapter
import com.example.rahmatantravel.Models.DoaSehariModels
import com.google.gson.JsonObject
import java.io.InputStream
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonParser
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.ArrayList



class Doa_Sehari_Hari : AppCompatActivity() {
    private lateinit var backToMenuLainnya: ImageView
    var doaSehariAdapter: DoaSehariAdapter? = null
    var doaSehariModels: MutableList<DoaSehariModels> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doa_sehari_hari)
        val searchDoa = findViewById<SearchView>(R.id.searchDoa)
        val rvListDoa = findViewById<RecyclerView>(R.id.rvListDoa)
        backToMenuLainnya = findViewById<ImageView>(R.id.backToMenuLainnya)

        backToMenuLainnya.setOnClickListener {
            onBackPressed()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21){
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
        searchDoa.setImeOptions(EditorInfo.IME_ACTION_DONE)
        searchDoa.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                doaSehariAdapter?.filter?.filter(newText)
                return true
            }
        })
        val searchPlateId = searchDoa.getContext().resources.getIdentifier("android:id/search_plate", null, null)
        val searchPlate = searchDoa.findViewById<View>(searchPlateId)
        searchPlate?.setBackgroundColor(Color.TRANSPARENT)
        rvListDoa.setLayoutManager(LinearLayoutManager(this))
        rvListDoa.setHasFixedSize(true)

        getDoaHarian()
    }
    private fun getDoaHarian(){
        try {
            val rvListDoa = findViewById<RecyclerView>(R.id.rvListDoa)
            val inputStream: InputStream = resources.openRawResource(R.raw.doaseharihari)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val strResponse = String(buffer, Charsets.UTF_8)
            try {
                val jsonObject = JSONObject(strResponse)
                val jsonArray = jsonObject.getJSONArray("data")
                for (i in 0 until jsonArray.length()) {
                    val jsonObjectData = jsonArray.getJSONObject(i)
                    val dataModel = DoaSehariModels()
                    dataModel.strId = jsonObjectData.getString("id")
                    dataModel.strTitle = jsonObjectData.getString("title")
                    dataModel.strArabic = jsonObjectData.getString("arabic")
                    dataModel.strLatin = jsonObjectData.getString("latin")
                    dataModel.strTranslation = jsonObjectData.getString("translation")
                    doaSehariModels.add(dataModel)
                }
                doaSehariAdapter = DoaSehariAdapter(doaSehariModels)
                rvListDoa.adapter = doaSehariAdapter
            }catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        catch (ignored: IOException) {
        }
    }
    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }
}