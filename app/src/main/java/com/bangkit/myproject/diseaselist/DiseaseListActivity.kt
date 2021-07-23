package com.bangkit.myproject.diseaselist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.myproject.R

class DiseaseListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_list)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<DiseaseData>()

        data.add(DiseaseData("akiec","Actinic Keratoses"))
        data.add(DiseaseData("bcc","Basal Cell Carcinoma"))

        val adapter = RecyAdapter( data, {
            position -> processItemClick(position)
        } )

        recyclerview.adapter = adapter

    }

    private fun processItemClick(position: Int){
//        use toast as sample for click item
        Toast.makeText(this, "Item has been clicked on position " + position.toString(), Toast.LENGTH_SHORT).show()
    }

}