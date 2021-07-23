package com.bangkit.myproject.diseaselist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.myproject.R

class RecyAdapter(private val mList: List<DiseaseData>, private val onItemClick: (position: Int) -> Unit): RecyclerView.Adapter<RecyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_disease, parent, false)

        return ViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemViewModel = mList[position]

        holder.txtView.text = ItemViewModel.lng + " (" + ItemViewModel.shrt + ")"
    }

    override fun getItemCount(): Int {

        return mList.size
    }

    class ViewHolder(val ItemView: View, private val onItemClick: (position: Int) -> Unit): RecyclerView.ViewHolder(ItemView), View.OnClickListener {
        val txtView: TextView = ItemView.findViewById(R.id.disease_text)

        init {
            ItemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val pos = bindingAdapterPosition
            onItemClick(layoutPosition)
        }


    }

}