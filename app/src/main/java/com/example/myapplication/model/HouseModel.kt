package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HouseModel(
    val id: Int,
    val title: String,
    val price: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val rating: Double,
    // Tambahan deskripsi buat halaman detail (kalau di JSON ada)
    val description: String = "No description available for this luxury home. Please contact agent for more details."
) : Parcelable