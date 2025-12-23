package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.adapter.HouseAdapter
import com.example.myapplication.api.ApiService
import com.example.myapplication.model.HouseModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// PERBAIKAN IMPORT: Mengambil R dan Binding dari namespace 'com.example.myapplication'
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var houseAdapter: HouseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchHouseData()
    }

    private fun setupRecyclerView() {
        houseAdapter = HouseAdapter(arrayListOf()) { house ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("EXTRA_HOUSE", house)
            startActivity(intent) // Berangkat!
        }

        binding.rvHouseList.apply {
            // --- INI BAGIAN KUNCINYA, RARA! ---
            // Angka 2 di bawah ini artinya DUA KOLOM.
            // Kalau mau 3 kolom, ganti jadi 3.
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            adapter = houseAdapter

            // Biar item nggak geser-geser aneh pas loading gambar beda ukuran
            (layoutManager as StaggeredGridLayoutManager).gapStrategy =
                StaggeredGridLayoutManager.GAP_HANDLING_NONE
        }
    }

    private fun fetchHouseData() {
        // Ganti URL ini dengan API kamu yang benar nanti
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-anon-87a4216892-interiorapp.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Contoh data dummy kalau API belum siap/error, biar kelihatan 2 kolomnya
                // Hapus bagian 'val dummyList' sampai 'setData(dummyList)' kalau API sudah beneran jalan
                /*
                val dummyList = listOf(
                    HouseModel(1, "Rumah 1", "Rp 1M", "https://via.placeholder.com/150", 4.5),
                    HouseModel(2, "Rumah 2", "Rp 2M", "https://via.placeholder.com/150", 4.0),
                    HouseModel(3, "Rumah 3", "Rp 3M", "https://via.placeholder.com/150", 5.0)
                )
                houseAdapter.setData(dummyList)
                */

                val response: List<HouseModel> = apiService.getHouses()
                if (response.isNotEmpty()) {
                    houseAdapter.setData(response)
                } else {
                    Toast.makeText(context, "Data kosong", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Log.e("HomeFragment", "Error: ${e.message}")
                // Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}