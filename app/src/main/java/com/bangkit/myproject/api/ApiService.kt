package com.bangkit.myproject.api

import com.bangkit.myproject.data.source.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("banners")
    fun getBanners() : Call<List<BannersResponseItem>>

    @GET("articles")
    fun getArticles() : Call<List<ArticleResponseItem>>

    @GET("detections/{id}")
    fun getDetailDetections(
        @Path("id") id: String
    ) : Call<DetectionResponse>

    @Multipart
    @POST("detections")
    fun postDataDetections(
        @Part file: MultipartBody.Part,
        @Part("Latitude") latitude: Double,
        @Part("Logtitude") logtitude: Double,
        @Part("Name") name: RequestBody?,
        @Part("Age") age: Int?,
        @Part("Sex") sex: Boolean
    ) : Call<DetectionResponseStatus>
}