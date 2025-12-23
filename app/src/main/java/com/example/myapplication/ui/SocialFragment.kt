package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentSocialBinding
import com.example.myapplication.adapter.SocialAdapter
import com.example.myapplication.model.SocialPostModel

class SocialFragment : Fragment() {

    private var _binding: FragmentSocialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSocialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyPosts = listOf(
            SocialPostModel(1, "Budi Interior", "https://i.pravatar.cc/150?u=1", "https://images.unsplash.com/photo-1616486338812-3dadae4b4ace", "3h ago", 450, "Simple and clean living room setup."),
            SocialPostModel(2, "Siti Design", "https://i.pravatar.cc/150?u=2", "https://images.unsplash.com/photo-1618221195710-dd6b41faaea6", "5h ago", 1200, "Luxury bedroom inspiration for 2024!")
        )

        binding.rvSocial.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SocialAdapter(dummyPosts)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}