package com.example.rahmatantravel;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, HomeFragmentK()).commit()
        }

        bottomNavigationView.background = null
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragmentK())
                R.id.pesanan -> replaceFragment(PesananFragment())
                R.id.akun -> replaceFragment(AkunFragment())
            }
            true
        }

        // Set selected item in BottomNavigationView
        val selectedItemId = intent.getIntExtra("selectedItemId", R.id.home) // Default ke home jika tidak ada extra
        bottomNavigationView.selectedItemId = selectedItemId
        if (intent.hasExtra("fragmentToLoad")) {
            val fragmentToLoad = intent.getStringExtra("fragmentToLoad")

            if (fragmentToLoad == "PesananFragment") {
                // Load PesananFragment
                val pesananFragment = PesananFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, pesananFragment)
                    .commit()
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
