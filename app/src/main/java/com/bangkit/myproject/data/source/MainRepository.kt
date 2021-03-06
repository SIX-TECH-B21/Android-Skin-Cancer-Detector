package com.bangkit.myproject.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.myproject.data.source.local.LocalDataSource
import com.bangkit.myproject.data.source.local.entity.ArticleEntity
import com.bangkit.myproject.data.source.local.entity.DiagnoseEntity
import com.bangkit.myproject.data.source.remote.ApiResponse
import com.bangkit.myproject.data.source.remote.RemoteDataSource
import com.bangkit.myproject.data.source.remote.response.ArticleResponseItem
import com.bangkit.myproject.data.source.remote.response.BannersResponseItem
import com.bangkit.myproject.utils.AppExecutors
import com.bangkit.myproject.vo.Resource
import com.denzcoskun.imageslider.models.SlideModel

class MainRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : MainDataSource {

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
        ): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(remoteData, localDataSource, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getArticles(): LiveData<Resource<List<ArticleEntity>>> {

        return object :
            NetworkBoundResource<List<ArticleEntity>, List<ArticleResponseItem>>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<List<ArticleResponseItem>>> {
                return remoteDataSource.getArticles()
            }

            override fun saveCallResult(data: List<ArticleResponseItem>) {
                val articleList = ArrayList<ArticleEntity>()
                for (response in data) {
                    val articleEntity = ArticleEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.images,
                        response.created
                    )
                    articleList.add(articleEntity)
                }
                localDataSource.insertArticles(articleList)
            }

            override fun shouldFetch(it: List<ArticleEntity>?): Boolean {
                return it == null || it.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<ArticleEntity>> {
                return localDataSource.getArticles()
            }

        }.asLiveData()
    }

    override fun getBanners(): LiveData<List<SlideModel>> {
        val bannersModel = MutableLiveData<List<SlideModel>>()
        remoteDataSource.getBanners(object : RemoteDataSource.LoadBanners {
            override fun getBanners(list: List<BannersResponseItem>) {
                val listBanner = ArrayList<SlideModel>()
                for (response in list) {
                    val bannerEntity = SlideModel(
                        response.image
                    )
                    listBanner.add(bannerEntity)
                }
                bannersModel.postValue(listBanner)
            }
        })
        return bannersModel
    }

    override fun getDiagnose(): LiveData<List<DiagnoseEntity>> {
        return localDataSource.getDiagnose()
    }

    override fun getDiagnoseById(id: Int): LiveData<DiagnoseEntity> {
        return localDataSource.getDiagnoseById(id)
    }

    override fun insertDiagnose(
        name: String,
        age: Int,
        sex: Boolean,
        result: String,
        percentage: String,
        image: String,
    ) {
        appExecutors.diskIO().execute {
            localDataSource.insertDiagnose(name, age, sex, result, percentage, image)
        }
    }
}