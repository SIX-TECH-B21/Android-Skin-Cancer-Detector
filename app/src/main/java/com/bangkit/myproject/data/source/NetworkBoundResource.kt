package com.bangkit.myproject.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bangkit.myproject.data.source.remote.ApiResponse
import com.bangkit.myproject.data.source.remote.StatusResponse
import com.bangkit.myproject.utils.AppExecutors
import com.bangkit.myproject.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val executors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDb()

        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (shouldFetch(it)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.succes(newData)
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun createCall() : LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) {
            result.value = Resource.loading(it)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response.statusResponse) {
                StatusResponse.SUCCESS ->
                    executors.diskIO().execute {
                        saveCallResult(response.body)
                        executors.mainThread().execute {
                            result.addSource(loadFromDb()) {
                                result.value = Resource.succes(it)
                            }
                        }
                    }
                StatusResponse.EMPTY -> executors.mainThread().execute {
                    result.addSource(loadFromDb()) {
                        result.value = Resource.succes(it)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        result.value = Resource.error(response.message, it)
                    }
                }
            }
        }
    }

    protected abstract fun shouldFetch(it: ResultType?): Boolean

    protected abstract fun loadFromDb(): LiveData<ResultType>

    fun asLiveData() : LiveData<Resource<ResultType>> = result
}