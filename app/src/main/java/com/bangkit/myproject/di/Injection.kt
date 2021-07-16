package com.bangkit.myproject.di

import android.content.Context
import com.bangkit.myproject.data.source.MainRepository
import com.bangkit.myproject.data.source.local.LocalDataSource
import com.bangkit.myproject.data.source.local.room.MainDatabase
import com.bangkit.myproject.data.source.remote.RemoteDataSource
import com.bangkit.myproject.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context) : MainRepository {

        val database = MainDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()

        val localDataSource = LocalDataSource.getInstance(database.mainDao())
        val appExecutors = AppExecutors()
        return MainRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}