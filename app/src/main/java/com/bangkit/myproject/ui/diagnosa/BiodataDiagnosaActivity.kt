package com.bangkit.myproject.ui.diagnosa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.myproject.R
import com.bangkit.myproject.databinding.ActivityBiodataDiagnosaBinding

class BiodataDiagnosaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiodataDiagnosaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiodataDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_bar_diagnosa)

        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }

        binding.btnLanjut.setOnClickListener {
            val intent = Intent(this, ScanDiagnosaActivity::class.java)
            startActivity(intent)
        }
    }
}