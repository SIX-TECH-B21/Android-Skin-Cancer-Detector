package com.bangkit.myproject.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.myproject.R
import com.bangkit.myproject.data.source.remote.response.DoctorsItem
import com.bangkit.myproject.databinding.ActivityDetailDoctorBinding
import com.bangkit.myproject.ui.adapter.HospitalAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailDoctorActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding:ActivityDetailDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.detail_doktor)

        val extraData = intent.getParcelableExtra<DoctorsItem>(EXTRA_DATA)


        val adapter = HospitalAdapter()
        adapter.setHospital(extraData?.hospitals)
        adapter.notifyDataSetChanged()


        Glide.with(this).load(extraData?.image).apply(RequestOptions()).into(binding.imgProfile)

        with(binding.rvRumahSakit) {
            layoutManager = LinearLayoutManager(this@DetailDoctorActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        binding.nameDoctorDetail.text = extraData?.name
    }
}