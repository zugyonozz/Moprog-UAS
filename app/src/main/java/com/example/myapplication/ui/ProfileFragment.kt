package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load Foto Profil pakai Glide biar otomatis bulat
        Glide.with(this)
            .load("https://i.pravatar.cc/300?u=rara") // Ganti sama foto aslimu kalau berani
            .circleCrop()
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(binding.imgProfile)

        // Logika tombol klik (dummy)
        binding.btnEditProfile.setOnClickListener {
            Toast.makeText(context, "Edit Profile Clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.btnLogout.setOnClickListener {
            Toast.makeText(context, "Logout Clicked! Mau ninggalin aku ya?", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}