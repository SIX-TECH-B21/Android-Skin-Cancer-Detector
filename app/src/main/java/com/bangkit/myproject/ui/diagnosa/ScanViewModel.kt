package com.bangkit.myproject.ui.diagnosa

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.myproject.MainActivity
import com.bangkit.myproject.api.ApiConfig
import com.bangkit.myproject.data.source.remote.response.ScanResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScanViewModel : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _result = MutableLiveData<ScanResponse>()
    val listScan: LiveData<ScanResponse> = _result

    fun postDiagnose() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().scanDiagnosa()
        client.enqueue(object : Callback<ScanResponse> {
            override fun onResponse(call: Call<ScanResponse>, response: Response<ScanResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _result.value = response.body()
                } else {
                    Log.e("Result", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ScanResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Failure", "onFailure : ${t.message.toString()}")
            }

        })
    }
}