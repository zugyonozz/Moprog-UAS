package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set halaman awal (Home)
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // Navigasi lengkap untuk 5 menu
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.menu_bookmark -> {
                    loadFragment(BookmarkFragment())
                    true
                }
                R.id.menu_upload -> {
                    loadFragment(UploadFragment())
                    true
                }
                R.id.menu_interact -> {
                    // Penanganan untuk fragment Social
                    loadFragment(SocialFragment())
                    true
                }
                R.id.menu_profile -> {
                    // Penanganan untuk fragment Profile
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}