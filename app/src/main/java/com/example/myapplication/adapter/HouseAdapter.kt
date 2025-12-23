package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.model.HouseModel
import com.example.myapplication.databinding.ItemHouseBinding

// Tambahkan parameter kedua: onItemClick
class HouseAdapter(
    private val listData: ArrayList<HouseModel>,
    private val onItemClick: (HouseModel) -> Unit // <-- Callback function
) : RecyclerView.Adapter<HouseAdapter.ViewHolder>() {

    fun setData(newList: List<HouseModel>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHouseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val house = listData[position]

        with(holder.binding) {
            tvTitle.text = house.title

            Glide.with(holder.itemView.context)
                .load(house.imageUrl)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(32)))
                .into(imgHouse)

            // --- LOGIKA KLIK ---
            // Saat item (root view) diklik, jalankan fungsi onItemClick
            root.setOnClickListener {
                onItemClick(house)
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder(val binding: ItemHouseBinding) : RecyclerView.ViewHolder(binding.root)
}