package com.bangkit.myproject.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkit.myproject.data.source.local.entity.ArticleEntity

@Dao
interface MainDao {

    @Query("SELECT * FROM articles")
    fun getArticles() : LiveData<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articleEntity: List<ArticleEntity>)
}