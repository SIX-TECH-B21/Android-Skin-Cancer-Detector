package com.bangkit.myproject.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bangkit.myproject.data.source.local.entity.ArticleEntity
import com.bangkit.myproject.data.source.local.entity.DiagnoseEntity

@Dao
interface MainDao {

    @Query("SELECT * FROM articles")
    fun getArticles(): LiveData<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articleEntity: List<ArticleEntity>)

    @Query("SELECT * FROM diagnose")
    fun getDiagnose(): LiveData<List<DiagnoseEntity>>

    @Query("SELECT * FROM diagnose WHERE id = :id")
    fun getDiagnoseById(id: Int): LiveData<DiagnoseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiagnose(diagnoseEntity: List<DiagnoseEntity>)

    @Query("INSERT INTO diagnose VALUES(null, :name, :age, :sex, :result, :percentage, :image)")
    fun insertDiagnoseTest(
        name: String,
        age: Int,
        sex: Boolean,
        result: String,
        percentage: String,
        image: String,
    )

}