package com.peter.ziska.kiwi.network

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val observableType =
            CallAdapter.Factory.getParameterUpperBound(
                0,
                returnType as ParameterizedType
            ) as? ParameterizedType
                ?: throw IllegalArgumentException("resource must be parameterized")
        return LiveDataAdapter<Any>(CallAdapter.Factory.getParameterUpperBound(0, observableType))
    }
}