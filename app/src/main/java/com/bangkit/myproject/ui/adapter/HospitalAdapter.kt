package com.bangkit.myproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.myproject.data.source.remote.response.HospitalsItem
import com.bangkit.myproject.databinding.ItemHospitalBinding

class HospitalAdapter : RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    private val listHospital = ArrayList<HospitalsItem>()

    fun setHospital(hospital:  List<HospitalsItem>? ) {
        if (hospital == null) return
        this.listHospital.clear()
        this.listHospital.addAll(hospital)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val itemHospitalBinding = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalViewHolder(itemHospitalBinding)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val hospital = listHospital[position]
        holder.bind(hospital)
    }

    override fun getItemCount(): Int {
        return listHospital.size
    }

    inner class HospitalViewHolder(private val binding: ItemHospitalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hospital: HospitalsItem) {
            binding.nameHospital.text = hospital.name
            binding.addressHospital.text = hospital.address
        }
    }
}