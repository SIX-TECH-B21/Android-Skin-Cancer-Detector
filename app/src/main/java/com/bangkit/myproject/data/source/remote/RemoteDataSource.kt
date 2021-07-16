package com.bangkit.myproject.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.myproject.api.ApiConfig
import com.bangkit.myproject.data.source.remote.response.ArticleResponseItem
import com.bangkit.myproject.data.source.remote.response.BannersResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"
        @Volatile
        private var instance : RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply {
                    instance = this
                }
            }
    }

    fun getArticles() : LiveData<ApiResponse<List<ArticleResponseItem>>> {
        val client = ApiConfig.getApiService().getArticles()
        val resultArticles = MutableLiveData<ApiResponse<List<ArticleResponseItem>>>()
        client.enqueue(object : Callback<List<ArticleResponseItem>>{
            override fun onResponse(
                call: Call<List<ArticleResponseItem>>,
                response: Response<List<ArticleResponseItem>>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        resultArticles.value = ApiResponse.success(it)
                    }
                } else {

                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ArticleResponseItem>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }

        })
        return resultArticles
    }

    fun getBanners(callBack: LoadBanners) {
        val client = ApiConfig.getApiService().getBanners()
        client.enqueue(object : Callback<List<BannersResponseItem>>{
            override fun onResponse(
                call: Call<List<BannersResponseItem>>,
                response: Response<List<BannersResponseItem>>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callBack.getBanners(it)
                    }
                } else {
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<BannersResponseItem>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }

        })
    }

    interface LoadBanners {
        fun getBanners(list: List<BannersResponseItem>)
    }
}