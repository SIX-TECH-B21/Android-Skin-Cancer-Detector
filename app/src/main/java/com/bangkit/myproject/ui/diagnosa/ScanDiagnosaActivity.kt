package com.bangkit.myproject.ui.diagnosa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.myproject.R
import com.bangkit.myproject.databinding.ActivityScanDiagnosaBinding

class ScanDiagnosaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanDiagnosaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }
    }
}