package com.bangkit.myproject.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bangkit.myproject.R
import com.bangkit.myproject.data.source.local.entity.DiagnoseEntity
import com.bangkit.myproject.databinding.ActivityDetailHistoryBinding
import com.bangkit.myproject.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailHistoryActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailHistoryBinding
    private lateinit var viewModel: DetailHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.detail_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailHistoryViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val getId = extras.getInt(EXTRA_DATA)
            viewModel.setSelectedHistoryDiagnose(getId)

            viewModel.historyDiagnose.observe(this, {
                if (it != null) {
                    showDetailHistoryDiagnose(it)
                }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showDetailHistoryDiagnose(detailHistory: DiagnoseEntity?) {
        detailHistory.let {
            Glide.with(this).load(detailHistory?.image).apply(RequestOptions())
                .error(R.drawable.no_image).into(binding.resultImg)
            binding.valueName.text = detailHistory?.name
            binding.valueJk.text =
                if (detailHistory?.sex == true) getString(R.string.perempuan) else getString(R.string.laki_laki)
            binding.valueAge.text = detailHistory?.age.toString().plus(" Tahun")
            binding.valueResultDiagnose.text = detailHistory?.result
            val percentage = detailHistory?.percentage?.subSequence(3, 5).toString()
            binding.valueAccurate.text = percentage.plus(" %")
            percentage.toInt()
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                binding.indicator.setProgress(percentage.toInt(), true)
            }
        }
    }
}