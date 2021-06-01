package com.bangkit.myproject.ui.diagnosa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.myproject.R
import com.bangkit.myproject.data.source.remote.response.DetectionResponse
import com.bangkit.myproject.databinding.ActivityResultDiagnosaBinding
import com.bangkit.myproject.ui.adapter.DoctorAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ResultDiagnosaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DIAGNOSE = "extra_diagnose"
    }

    private lateinit var binding: ActivityResultDiagnosaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportActionBar?.title = getString(R.string.result_diagnosa)

        val result = intent.getParcelableExtra<DetectionResponse>(EXTRA_DIAGNOSE) as DetectionResponse


        val adapter = DoctorAdapter()


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

        binding.valueName.text = result.responden.name
        binding.valueJk.text = if (result.responden.sex)  getString(R.string.perempuan) else  getString(R.string.laki_laki)
        binding.valueAge.text = result.responden.age.toString().plus(" Tahun")
        binding.valueResultDiagnose.text = result.detection.category
        binding.labelSymptomsResultDiagnose.text = result.detection.category


        Glide.with(this).load(result.detection.images).apply(RequestOptions()).into(binding.resultImg)

        adapter.setDoctor(result.detection.doctors)
        adapter.notifyDataSetChanged()

        with(binding.rvDoctor) {
            layoutManager = LinearLayoutManager(this@ResultDiagnosaActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }
}