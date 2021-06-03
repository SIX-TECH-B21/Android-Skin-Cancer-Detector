package com.bangkit.myproject.ui.diagnosa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.myproject.MainActivity
import com.bangkit.myproject.R
import com.bangkit.myproject.data.source.remote.response.DetectionResponse
import com.bangkit.myproject.databinding.ActivityResultDiagnosaBinding
import com.bangkit.myproject.ui.adapter.DoctorAdapter
import com.bangkit.myproject.ui.detail.DetailDoctorActivity
import com.bangkit.myproject.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
class ResultDiagnosaActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_DIAGNOSE = "extra_diagnose"
    }

    private lateinit var binding: ActivityResultDiagnosaBinding
    private lateinit var diagnosaViewModel: DiagnosaViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.result_diagnosa)

        val factory = ViewModelFactory.getInstance(this)
        diagnosaViewModel = ViewModelProvider(this, factory)[DiagnosaViewModel::class.java]

        val result = intent.getParcelableExtra<DetectionResponse>(EXTRA_DIAGNOSE) as DetectionResponse
        val adapter = DoctorAdapter()

        adapter.onItemClick = {
            val intent = Intent(this, DetailDoctorActivity::class.java)
            intent.putExtra(DetailDoctorActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        val stringBuilderSymptoms = StringBuilder()
        for (i in result.detection.symptoms.indices) {
            if (i + 1 != result.detection.symptoms.size) {
                stringBuilderSymptoms.append(result.detection.symptoms[i])
                stringBuilderSymptoms.append("\n")
            } else {
                stringBuilderSymptoms.append(result.detection.symptoms[i])
            }
        }

        binding.valueSymptoms.text = stringBuilderSymptoms.toString()
        val stringBuilder = StringBuilder()

        for (index in result.detection.treatment.indices) {
            if (index + 1 != result.detection.treatment.size) {
                stringBuilder.append(result.detection.treatment[index])
                stringBuilder.append("\n")
            } else {
                stringBuilder.append(result.detection.treatment[index])
            }
        }
        binding.valueTreatment.text = stringBuilder.toString()

        val percentage = result.detection.percentage.subSequence(3, 5)
        val showPercentage = percentage.toString()

        binding.valueAccurate.text = showPercentage.plus("%")


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.indicator.setProgress(showPercentage.toInt(), true)
        }

        binding.valueName.text = result.responden.name
        binding.valueJk.text = if (result.responden.sex)  getString(R.string.perempuan) else  getString(R.string.laki_laki)
        binding.valueAge.text = result.responden.age.toString().plus(" Tahun")
        binding.valueResultDiagnose.text = result.detection.category
        binding.labelSymptomsResultDiagnose.text = result.detection.category


        Glide.with(this).load(result.detection.images).apply(RequestOptions()).error(R.drawable.no_image).into(binding.resultImg)

        adapter.setDoctor(result.detection.doctors)
        adapter.notifyDataSetChanged()

        with(binding.rvDoctor) {
            layoutManager = LinearLayoutManager(this@ResultDiagnosaActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        var statusSave = false
        setStatus(statusSave)

        binding.btnSave.setOnClickListener {
            statusSave = !statusSave
            diagnosaViewModel.insertDiagnoseTest(result.responden.name, result.responden.age, result.responden.sex,
                result.detection.category, result.detection.percentage, result.detection.images)
            setStatus(statusSave)
        }

        binding.btnClose.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setStatus(statusSave: Boolean) {
        if (statusSave) {
            binding.btnSave.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_24))
            Toast.makeText(this, "Berhasil Simpan Diagnosa", Toast.LENGTH_SHORT).show()
            binding.btnSave.isEnabled = false
        }
    }

}