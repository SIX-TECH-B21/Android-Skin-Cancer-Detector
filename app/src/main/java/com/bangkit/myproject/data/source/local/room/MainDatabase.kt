package com.bangkit.myproject.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkit.myproject.data.source.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false )
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao() : MainDao

    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context) : MainDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "skin_health.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}