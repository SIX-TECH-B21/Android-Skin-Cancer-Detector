package com.bangkit.myproject.diseaselist

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.myproject.R

class DiseaseListActivity : AppCompatActivity() {

//    val prefPos: SharedPreferences = getSharedPreferences("NewFeature1_Data", MODE_PRIVATE)
//    var prefEditor: SharedPreferences.Editor = prefPos.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_list)

        setTitle("Disease List")

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val diseaseState: DiseaseStateData = DiseaseStateData()
        var diseaseData: ArrayList<String>? = null
        val data = ArrayList<DiseaseData>()

        for (i in 0..diseaseState.getLength()-1){
            diseaseData = diseaseState.getDataPos(i)
            data.add(DiseaseData(diseaseData.get(0), diseaseData.get(1)))
        }

//        data.add(DiseaseData("akiec","Actinic Keratoses"))
//        data.add(DiseaseData("bcc","Basal Cell Carcinoma"))

        val adapter = RecyAdapter( data, {
            position -> processItemClick(position)
        } )

        recyclerview.adapter = adapter

    }

    private fun processItemClick(position: Int){

        val pos: Int

        if(position == null){
            pos = 0
        }else{
            pos = position
        }

        val prefPos: SharedPreferences = getSharedPreferences("NewFeature1_Data", MODE_PRIVATE)
        var prefEditor: SharedPreferences.Editor = prefPos.edit()

        prefEditor.putInt("disease_list_page", pos)
        prefEditor.apply()

        val intent = Intent(this, DiseaseDetailActivity::class.java)
        startActivity(intent)

    }

}