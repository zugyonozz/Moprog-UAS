package com.example.myapplication.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentUploadBinding

class UploadFragment : Fragment() {

    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!

    // Variabel buat nyimpen URI gambar yang dipilih user
    private var selectedImageUri: Uri? = null

    // --- LAUNCHER GALERI ---
    // Ini cara modern buka galeri. 'image/*' artinya filter cuma file gambar.
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            // Kalau user jadi milih gambar, simpan URI-nya & tampilkan
            selectedImageUri = uri
            binding.imgPreview.setImageURI(uri)
            binding.imgPreview.setPadding(0, 0, 0, 0) // Hapus padding icon kamera
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Klik area gambar -> Buka Galeri
        binding.cardImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        // 2. Klik Upload
        binding.btnUpload.setOnClickListener {
            uploadHouse()
        }
    }

    private fun uploadHouse() {
        // Validasi input
        val title = binding.inputTitle.editText?.text.toString()
        val price = binding.inputPrice.editText?.text.toString()

        if (selectedImageUri == null) {
            Toast.makeText(context, "Please select an image first!", Toast.LENGTH_SHORT).show()
            return
        }

        if (title.isEmpty() || price.isEmpty()) {
            Toast.makeText(context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        // --- SIMULASI UPLOAD ---
        // Karena kita belum punya backend yang nerima Multipart (POST),
        // kita pura-pura sukses dulu ya.

        // Di dunia nyata, di sini kamu convert URI -> File -> MultipartBody.Part
        // Terus panggil ApiService.uploadHouse(...)

        Toast.makeText(context, "Uploading $title...", Toast.LENGTH_SHORT).show()

        // Reset form setelah "upload"
        binding.inputTitle.editText?.text?.clear()
        binding.inputPrice.editText?.text?.clear()
        binding.imgPreview.setImageResource(android.R.drawable.ic_menu_camera)
        binding.imgPreview.setPadding(60, 60, 60, 60)
        selectedImageUri = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}