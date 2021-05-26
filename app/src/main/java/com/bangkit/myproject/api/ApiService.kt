package com.bangkit.myproject.api

import com.bangkit.myproject.data.source.remote.response.ScanResponse
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {

    @POST("./")
    fun scanDiagnosa() : Call<ScanResponse>
}