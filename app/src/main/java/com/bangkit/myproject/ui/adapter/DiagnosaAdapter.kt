package com.bangkit.myproject.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.myproject.data.source.local.entity.DiagnoseEntity
import com.bangkit.myproject.databinding.ItemHistoryDiagnoseBinding
import com.bangkit.myproject.ui.detail.DetailHistoryActivity

class DiagnosaAdapter : RecyclerView.Adapter<DiagnosaAdapter.DiagnosaViewHolder>() {

    private var listHistoryDiagnose = ArrayList<DiagnoseEntity>()
    var onItemClick: ((DiagnoseEntity) -> Unit)? = null

    fun setHistoryDiagnose(diagnoseEntity: List<DiagnoseEntity>?) {
        if (diagnoseEntity == null) return

        this.listHistoryDiagnose.clear()
        this.listHistoryDiagnose.addAll(diagnoseEntity)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagnosaViewHolder {
        val itemHistoryDiagnoseBinding = ItemHistoryDiagnoseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiagnosaViewHolder(itemHistoryDiagnoseBinding)
    }

    override fun onBindViewHolder(holder: DiagnosaViewHolder, position: Int) {
        val historyDiagnose = listHistoryDiagnose[position]
        holder.bind(historyDiagnose)
    }

    override fun getItemCount(): Int {
        return listHistoryDiagnose.size
    }

    inner class DiagnosaViewHolder(private val binding: ItemHistoryDiagnoseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(historyDiagnose: DiagnoseEntity) {
            with(binding) {
                namePasien.text = historyDiagnose.name
                gender.text = if (historyDiagnose.sex) "Perempuan" else "Laki - Laki"
                age.text = historyDiagnose.age.toString().plus( " Tahun")
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailHistoryActivity::class.java)
                    intent.putExtra(DetailHistoryActivity.EXTRA_DATA, historyDiagnose.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}