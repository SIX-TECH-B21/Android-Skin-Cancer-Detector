package com.bangkit.myproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.myproject.data.source.remote.response.DoctorsItem
import com.bangkit.myproject.databinding.ItemDoctorBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class DoctorAdapter : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    private val listDoctor = ArrayList<DoctorsItem>()
    var onItemClick: ((DoctorsItem) -> Unit)? = null

    fun setDoctor(doctor:  List<DoctorsItem>? ) {
        if (doctor == null) return
        this.listDoctor.clear()
        this.listDoctor.addAll(doctor)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val itemDoctorBinding = ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(itemDoctorBinding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = listDoctor[position]
        holder.bind(doctor)
    }

    override fun getItemCount(): Int {
        return listDoctor.size
    }

    inner class DoctorViewHolder(private val binding: ItemDoctorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(doctor: DoctorsItem) {
            Glide.with(itemView).load(doctor.image).apply(RequestOptions()).into(binding.profilePhoto)
            binding.nameDoctor.text = doctor.name
            binding.specialist.text = doctor.speciality
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listDoctor[bindingAdapterPosition])
            }
        }
    }
}