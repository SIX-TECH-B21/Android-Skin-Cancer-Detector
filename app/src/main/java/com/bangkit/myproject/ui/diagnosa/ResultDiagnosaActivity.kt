package com.bangkit.myproject.ui.diagnosa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.myproject.R
import com.bangkit.myproject.data.source.remote.response.ScanResponse
import com.bangkit.myproject.databinding.ActivityResultDiagnosaBinding

class ResultDiagnosaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DIAGNOSE = "extra_diagnose"
    }

    private lateinit var binding: ActivityResultDiagnosaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getParcelableExtra<ScanResponse>(EXTRA_DIAGNOSE) as ScanResponse

        val text = "ResultML : ${result.resultML.toString()},\n Name : ${result.name.toString()}"
        binding.received.text = text
    }
}