package com.bangkit.myproject.ui.diagnosa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.myproject.api.ApiConfig
import com.bangkit.myproject.data.source.remote.response.DetectionResponse
import com.bangkit.myproject.data.source.remote.response.DetectionResponseStatus
import com.bangkit.myproject.utils.Event
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScanViewModel : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    private val _getIdStatus = MutableLiveData<String>()
    val getIdStatus: LiveData<String> = _getIdStatus

    private val _result = MutableLiveData<DetectionResponse>()
    val listScan: LiveData<DetectionResponse> = _result

    fun postDiagnose(
        body: MultipartBody.Part,
        latitude: Double,
        logtitude: Double,
        name: RequestBody?,
        age: Int?,
        gender: Boolean
    ) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().postDataDetections(body, latitude, logtitude, name, age, gender)
        client.enqueue(object : Callback<DetectionResponseStatus>{
            override fun onResponse(
                call: Call<DetectionResponseStatus>,
                response: Response<DetectionResponseStatus>,
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _getIdStatus.value = response.body()?.detectionId
                    _toastText.value = Event(response.body()?.title.toString())
                } else {
                    _getIdStatus.value = response.body()?.detectionId
                    _toastText.value = Event(response.body()?.title.toString())
                }
            }

            override fun onFailure(call: Call<DetectionResponseStatus>, t: Throwable) {
                _isLoading.value = false
                _toastText.value = Event(t.message.toString())
            }

        })
    }

    fun getResultDetection(getDetectionId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailDetections(getDetectionId)
        client.enqueue(object : Callback<DetectionResponse> {
            override fun onResponse(
                call: Call<DetectionResponse>,
                response: Response<DetectionResponse>,
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _result.value = response.body()
                } else {
                    Log.e("Result", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetectionResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Failure", "onFailure : ${t.message.toString()}")
            }

        })
    }
}