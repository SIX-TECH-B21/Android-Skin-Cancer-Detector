package com.bangkit.myproject.data.source

import androidx.lifecycle.LiveData
import com.bangkit.myproject.data.source.local.entity.ArticleEntity
import com.bangkit.myproject.vo.Resource
import com.denzcoskun.imageslider.models.SlideModel

interface MainDataSource {
    fun getArticles() : LiveData<Resource<List<ArticleEntity>>>

    fun getBanners() : LiveData<List<SlideModel>>
}