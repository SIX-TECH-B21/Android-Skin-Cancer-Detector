package com.bangkit.myproject.data.source

import androidx.lifecycle.LiveData
import com.bangkit.myproject.data.source.local.entity.ArticleEntity
import com.bangkit.myproject.data.source.local.entity.DiagnoseEntity
import com.bangkit.myproject.vo.Resource
import com.denzcoskun.imageslider.models.SlideModel

interface MainDataSource {
    fun getArticles() : LiveData<Resource<List<ArticleEntity>>>

    fun getBanners() : LiveData<List<SlideModel>>

    fun getDiagnose() : LiveData<List<DiagnoseEntity>>

    fun getDiagnoseById(id: Int) : LiveData<DiagnoseEntity>

    fun insertDiagnose(name: String, age: Int, sex: Boolean, result: String,
                           percentage: String, image:String)
}