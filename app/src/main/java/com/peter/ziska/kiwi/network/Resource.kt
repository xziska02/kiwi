package com.peter.ziska.kiwi.network

class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> isSuccess(data: T): Resource<T> =
            Resource(Status.SUCCESS, data, null)

        fun <T> isError(data: T, msg: String): Resource<T> =
            Resource(Status.ERROR, data, msg)

        fun <T> isLoading(data: T?): Resource<T> =
            Resource(Status.LOADING, data, null)
    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}