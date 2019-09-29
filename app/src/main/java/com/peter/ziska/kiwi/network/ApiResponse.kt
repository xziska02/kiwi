package com.peter.ziska.kiwi.network

import retrofit2.Response


@Suppress("MemberVisibilityCanBePrivate")
open class ApiResponse<T> {
    val code: Int
    val body: T?
    val message: String?

    val isFailure: Boolean
    val isSuccessful: Boolean
        get() = code in 200..300

    constructor(error: Throwable) {
        code = 500
        body = null
        isFailure = true
        message = error.message
    }

    constructor(response: Response<T>) {
        code = response.code()

        if (response.isSuccessful) {
            body = response.body()
            message = null
            isFailure = false
        } else {
            var errorMessage = response.message()
            response.errorBody()?.let {
                errorMessage = it.string()
            }
            isFailure = true
            body = null
            message = errorMessage
        }
    }
}