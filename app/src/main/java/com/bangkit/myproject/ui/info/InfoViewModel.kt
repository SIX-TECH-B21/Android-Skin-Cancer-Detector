package com.bangkit.myproject.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pada saat aplikasi dijalankan terdapat 3 tombol navigasi yaitu home, history, dan info. " +
                "Pada saat awal aplikasi dibuka anda berada dihome, yang dimana disitu ada tombol scan dan diagnosa " +
                "jika anda klik tombol tersebut akan muncul popup permission location anda klik allow, setelah itu jangan " +
                "lupa menyalakan GPS anda yah untuk mendapatkan titik koordinat. " +
                "\n\n" +
                "Klik tombol scan dan diagnosa sekali lagi untuk mengisi biodata anda, setelah itu klik tombol berikutnya, " +
                "setelah mengisi biodata anda, klik icon camera " +
                "untuk mengambil gambar jika sudah selesai mengambil gambar, maka klik tombol diagnosa, maka sistem akan mengecek " +
                "apakah ada terindikasi penyakit kulit atau tidak." +
                "\n\n" +
                "Jika sudah selesai diagnosa, terdapat dua tombol yang pertama selesai  dan yang kedua icon bookmark, tombol selesai " +
                "jika diklik maka dia akan kembali ke home tanpa menyimpan hasil diagnosa tersebut. jika tombol icon bookmark diklik " +
                "maka hasil diagnosa tersebut disimpan dan beralih ke home." +
                "\n\n" +
                "Untuk melihat hasil diagnosa klik navigasi history maka akan muncul history diagnosa anda." +
                "\n\n" +
                "Navigasi info berfungsi untuk panduan cara memakai aplikasi ini." +
                "\n\n" +
                "Semoga membantu !"
    }
    val text: LiveData<String> = _text
}