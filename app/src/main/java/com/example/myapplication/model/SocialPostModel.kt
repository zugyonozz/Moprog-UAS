package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SocialPostModel(
    val id: Int,
    val userName: String,
    val userAvatar: String, // URL foto profil
    val postImage: String,  // URL foto postingan
    val timeAgo: String,
    val likesCount: Int,
    val caption: String
) : Parcelable