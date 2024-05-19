package com.example.uts

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    // Nama file SharedPreferences
    private val PREFS_NAME = "UserData"

    private lateinit var textViewEmail: TextView
    private lateinit var textViewNama: TextView
    private lateinit var textViewNIM: TextView
    private lateinit var textViewKelas: TextView
    private lateinit var buttonLogout: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Inisialisasi TextView dan Button
        textViewEmail = view.findViewById(R.id.textviewemail)
        textViewNama = view.findViewById(R.id.textviewnama)
        textViewNIM = view.findViewById(R.id.textviewnim)
        textViewKelas = view.findViewById(R.id.textviewkelas)
        buttonLogout = view.findViewById(R.id.buttonlogout)

        // Mendapatkan data pengguna dari SharedPreferences dan menampilkannya di TextView
        displayUserData()

        // Mengatur listener untuk button logout
        buttonLogout.setOnClickListener {
            // Hapus data pengguna dari SharedPreferences
            clearUserData()
            // Pindah ke halaman login
            startActivity(Intent(requireContext(), Login::class.java))
            // Tutup activity saat ini (ProfileFragment)
            requireActivity().finish()
        }

        return view
    }

    // Metode untuk mendapatkan data pengguna dari SharedPreferences
    @SuppressLint("SetTextI18n")
    private fun displayUserData() {
        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userData = sharedPreferences.getString("userData", "")

        // Memeriksa apakah data pengguna tersedia
        if (!userData.isNullOrEmpty()) {
            // Memecah data pengguna menjadi bagian-bagian terpisah (email, nama, nim, kelas)
            val userDataArray = userData.split(",")
            if (userDataArray.size == 4) {
                // Menampilkan data pengguna di TextView
                textViewEmail.text = " ${userDataArray[0]}"
                textViewNama.text = " ${userDataArray[1]}"
                textViewNIM.text = " ${userDataArray[2]}"
                textViewKelas.text = " ${userDataArray[3]}"
            }
        }
    }

    // Metode untuk menghapus data pengguna dari SharedPreferences (logout)
    private fun clearUserData() {
        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}