package com.bangkit.myproject.data.source.local

import androidx.lifecycle.LiveData
import com.bangkit.myproject.data.source.local.entity.ArticleEntity
import com.bangkit.myproject.data.source.local.entity.DiagnoseEntity
import com.bangkit.myproject.data.source.local.room.MainDao

class LocalDataSource private constructor(private val mainDao: MainDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mMainDao: MainDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mMainDao)
    }

    fun getArticles(): LiveData<List<ArticleEntity>> = mainDao.getArticles()

    fun insertArticles(articleEntity: List<ArticleEntity>) = mainDao.insertArticles(articleEntity)

    fun getDiagnose(): LiveData<List<DiagnoseEntity>> = mainDao.getDiagnose()

    fun getDiagnoseById(id: Int): LiveData<DiagnoseEntity> = mainDao.getDiagnoseById(id)

    fun insertDiagnose(diagnoseEntity: List<DiagnoseEntity>) =
        mainDao.insertDiagnose(diagnoseEntity)

    fun insertDiagnoseTest(
        name: String, age: Int, sex: Boolean, result: String,
        percentage: String, image: String,
    ) = mainDao.insertDiagnoseTest(name, age, sex, result, percentage, image)

}