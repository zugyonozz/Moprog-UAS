package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentBookmarkBinding
import com.example.myapplication.adapter.HouseAdapter
import com.example.myapplication.model.HouseModel

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    // Kita reuse adapter yang sama, gak perlu bikin baru. Efisien kan?
    private lateinit var bookmarkAdapter: HouseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadBookmarkData()
    }

    private fun setupRecyclerView() {
        // Inisialisasi adapter dengan callback klik
        bookmarkAdapter = HouseAdapter(arrayListOf()) { house ->
            // Kalau diklik, tetep lari ke DetailActivity
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("EXTRA_HOUSE", house)
            startActivity(intent)
        }

        binding.rvBookmark.apply {
            // Nah, di sini bedanya!
            // Kalau Home pakai StaggeredGridLayout, Bookmark kita pakai LinearLayout (List biasa)
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarkAdapter
        }
    }

    private fun loadBookmarkData() {
        // TODO: Nanti kita ganti ini pakai Room Database (SQLite)
        // Sekarang pakai data dummy dulu biar kelihatan hasilnya

        val savedHouses = listOf(
            HouseModel(
                101,
                "Villa Bali Indah",
                "Rp 5.000.000.000",
                "https://images.unsplash.com/photo-1580587771525-78b9dba3b91d",
                4.9,
                "Saved from your dream list."
            ),
            HouseModel(
                102,
                "Rumah Minimalis",
                "Rp 850.000.000",
                "https://images.unsplash.com/photo-1518780664697-55e3ad937233",
                4.5,
                "Affordable and cozy."
            )
        )

        // Tampilkan Empty State kalau list kosong
        if (savedHouses.isEmpty()) {
            binding.rvBookmark.visibility = View.GONE
            binding.layoutEmpty.visibility = View.VISIBLE
        } else {
            binding.rvBookmark.visibility = View.VISIBLE
            binding.layoutEmpty.visibility = View.GONE
            bookmarkAdapter.setData(savedHouses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}