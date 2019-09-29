package com.peter.ziska.kiwi.network


import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class NetworkBoundRepository<ResultType, RequestType> internal constructor() {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.isLoading(null)
        val loadedFromDb = this.loadFromDb()
        result.addSource(loadedFromDb) { data ->
            result.removeSource(loadedFromDb)
            if (shouldFetch(data)) {
                fetchData(loadedFromDb)
            } else {
                result.addSource<ResultType>(loadedFromDb) { newData ->
                    setValue(Resource.isSuccess(newData))
                }
            }
        }
    }

    private fun fetchData(loadedFromDb: LiveData<ResultType>) {
        val restData = fetchService()
        result.addSource(restData) { response ->
            result.removeSource(restData)
            when (response.isSuccessful) {
                true -> {
                    val data = response.body
                    if (data != null) {
                        saveFetchData(data)
                        result.addSource<ResultType>(loadFromDb()) { newData ->
                            setValue(Resource.isSuccess(newData))
                        }
                    }
                }
                false -> {
                    onFetchFailed(response.message)
                    result.addSource<ResultType>(loadedFromDb) { newData ->
                        setValue(Resource.isError(newData, response.message!!))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    @WorkerThread
    protected abstract fun saveFetchData(items: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun fetchService(): LiveData<ApiResponse<RequestType>>

    @MainThread
    protected abstract fun onFetchFailed(message: String?)

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}