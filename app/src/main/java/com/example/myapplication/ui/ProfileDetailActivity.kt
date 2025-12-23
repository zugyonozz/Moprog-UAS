package com.example.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityProfileDetailBinding

/**
 * Ini halaman detailnya, Rara.
 * Di sini user bisa lihat info lebih lengkap atau ngedit data mereka.
 */
class ProfileDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set data dummy dulu biar nggak kosong amat kayak hati kamu.
        setupInitialData()

        binding.btnSaveProfile.setOnClickListener {
            saveProfileChanges()
        }

        binding.btnBackDetail.setOnClickListener {
            finish() // Tutup activity dan balik ke fragment sebelumnya
        }
    }

    private fun setupInitialData() {
        with(binding) {
            etFullName.setText("Rara Zuu")
            etEmail.setText("rara.zuu@example.com")
            etPhone.setText("+62 812 3456 7890")
            etBio.setText("Suka belajar dari pondasi, apalagi kalau diajarin Zuu.")
        }
    }

    private fun saveProfileChanges() {
        // Logika buat nyimpen data (biasanya nembak API lagi pakai Retrofit)
        Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
}