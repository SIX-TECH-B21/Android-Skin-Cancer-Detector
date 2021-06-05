package com.bangkit.myproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.myproject.data.source.MainRepository
import com.bangkit.myproject.data.source.local.entity.ArticleEntity
import com.bangkit.myproject.vo.Resource
import com.denzcoskun.imageslider.models.SlideModel

class HomeViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getBanners() : LiveData<List<SlideModel>> = mainRepository.getBanners()

    fun getArticles() : LiveData<Resource<List<ArticleEntity>>> = mainRepository.getArticles()
}