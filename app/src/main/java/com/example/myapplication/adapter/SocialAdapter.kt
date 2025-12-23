package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemPostBinding
import com.example.myapplication.model.SocialPostModel

class SocialAdapter(private val posts: List<SocialPostModel>) :
    RecyclerView.Adapter<SocialAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        with(holder.binding) {
            tvUserName.text = post.userName
            tvTime.text = post.timeAgo
            tvLikes.text = post.likesCount.toString()
            tvCaption.text = post.caption

            // Load Avatar (Rounded)
            Glide.with(holder.itemView.context)
                .load(post.userAvatar)
                .circleCrop()
                .into(imgAvatar)

            // Load Post Image
            Glide.with(holder.itemView.context)
                .load(post.postImage)
                .into(imgPost)
        }
    }

    override fun getItemCount(): Int = posts.size
}