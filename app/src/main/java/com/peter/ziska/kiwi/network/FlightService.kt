package com.peter.ziska.kiwi.network

import com.peter.ziska.kiwi.network.data.Flight
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface FlightService {

    @GET("flights?partner=picky")
    fun getFlight(
        @Query("fly_from") flyFrom: String,       //Example: CZ
        @Query("date_from") dateFrom: String,
        @Query("date_to") dateTo: String,
        @Query("sort") sort: String = "price", //price duration quality date
        @Query("vehicle_type") vehicleType: String = "aircraft"
    ): Call<Flight>

    companion object {
        private const val BASE_URL = "https://api.skypicker.com/"

        @JvmSynthetic
        fun create(): FlightService {

            //TODO Add dependency injection
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder().addInterceptor(logger).build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).baseUrl(BASE_URL)
                .build()
                .create(FlightService::class.java)
        }
    }
}