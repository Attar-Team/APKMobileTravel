package com.example.rahmatantravel;

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rahmatantravel.Models.SharedViewModel
import com.example.rahmatantravel.SharedPref.PrefManager

class AkunFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_akun, container, false)
        val ubahProfile = view.findViewById<LinearLayout>(R.id.ubahProfile)
        val ubahPW = view.findViewById<LinearLayout>(R.id.ubahPW)
        val dataJamaah = view.findViewById<LinearLayout>(R.id.DataJamaah)
        val logoutButton = view.findViewById<TextView>(R.id.logout)
        val namaJamaah = view.findViewById<TextView>(R.id.NamaJamaah)
        val bantuan = view.findViewById<LinearLayout>(R.id.pusatBantuan)
        val imageProfile = view.findViewById<ImageView>(R.id.imageProfil)
        val cardAgen = view.findViewById<CardView>(R.id.cardAgen)
        val pusatAgen = view.findViewById<LinearLayout>(R.id.pusatAgen)

        val prefManager = PrefManager(requireContext())

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val image = sharedViewModel.getProfileImageUri()
        Log.d("image", image.toString())
        if (image != null) {
            imageProfile.setImageURI(image)
        }

        cardAgen.visibility = View.GONE

        prefManager.getUserName().let {
            namaJamaah.text = it.toString()
        }

        prefManager.getLoginData().let {
            if (it.third == "agen") {
                cardAgen.visibility = View.VISIBLE
            }
        }

        val phoneNumber = "+62895366960593"
        val url = "https://wa.me/$phoneNumber"

        bantuan.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        dataJamaah.setOnClickListener {
            val intent = Intent(activity, DataJamaah::class.java)
            startActivity(intent)
        }

        ubahProfile.setOnClickListener {
            val intent = Intent(activity, UbahProfile::class.java)
            startActivity(intent)
        }

        ubahPW.setOnClickListener {
            val intent = Intent(activity, UbahKataSandi::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->
                    // Hapus data login
                    prefManager.clearLoginData()

                    // Kembali ke halaman utama
                    startActivity(Intent(requireContext(), SplashScreen::class.java))
                    requireActivity().finish()
                })
                .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->
                    // Batalkan logout
                    dialog.dismiss()
                })
                .show()
        }

        pusatAgen.setOnClickListener {
            val intent = Intent(activity, PusatAgen::class.java)
            startActivity(intent)
        }

        return view
    }
}
