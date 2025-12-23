package com.example.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.model.HouseModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Ambil data yang dikirim dari Home
        // "EXTRA_HOUSE" adalah kunci rahasia (key) yang harus sama dengan pengirim
        val house = intent.getParcelableExtra<HouseModel>("EXTRA_HOUSE")

        if (house != null) {
            setupUI(house)
        } else {
            Toast.makeText(this, "Error: Data rumah tidak ditemukan!", Toast.LENGTH_SHORT).show()
            finish() // Tutup halaman kalau datanya null
        }

        // Tombol Back
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Tombol Book Now (Cuma Toast doang buat contoh)
        binding.btnBookNow.setOnClickListener {
            Toast.makeText(this, "Booking ${house?.title}...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupUI(house: HouseModel) {
        with(binding) {
            tvDetailTitle.text = house.title
            tvDetailPrice.text = house.price
            tvDetailDesc.text = house.description

            // Format rating biar ada bintangnya
            tvDetailRating.text = "⭐ ${house.rating} (Review unavailable)"

            Glide.with(this@DetailActivity)
                .load(house.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(imgDetail)
        }
    }
}