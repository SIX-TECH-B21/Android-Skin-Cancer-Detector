package com.bangkit.myproject.data.source.local

import androidx.lifecycle.LiveData
import com.bangkit.myproject.data.source.local.entity.ArticleEntity
import com.bangkit.myproject.data.source.local.room.MainDao

class LocalDataSource private constructor(private val mainDao: MainDao) {

    companion object {
        private var INSTAMCE: LocalDataSource? = null

        fun getInstance(mMainDao: MainDao) : LocalDataSource =
            INSTAMCE ?: LocalDataSource(mMainDao)
    }

    fun getArticles() : LiveData<List<ArticleEntity>> = mainDao.getArticles()

    fun insertArticles(articleEntity: List<ArticleEntity>) = mainDao.insertArticles(articleEntity)
}