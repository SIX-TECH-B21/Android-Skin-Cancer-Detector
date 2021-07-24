package com.bangkit.myproject.diseaselist

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.bangkit.myproject.R
import org.w3c.dom.Text

class DiseaseDetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_detail)

        val prefPos: SharedPreferences = getSharedPreferences("NewFeature1_Data", MODE_PRIVATE)
        val diseaseState: DiseaseStateData = DiseaseStateData()

        val pagePos: Int = prefPos.getInt("disease_list_page", 0)
        val diseaseData: ArrayList<String> = diseaseState.getDataPos(pagePos)

        val diseaseNameTitle: TextView = findViewById(R.id.disease_name_txt)
        val diseaseDescText: TextView = findViewById(R.id.description_txt)
        val diseaseSymptText: TextView = findViewById(R.id.symptoms_txt)

        setTitle(diseaseData.get(0).toUpperCase())

        diseaseNameTitle.text = diseaseData.get(1)
        diseaseDescText.text = diseaseData.get(2)
        diseaseSymptText.text = diseaseData.get(3)

    }
}