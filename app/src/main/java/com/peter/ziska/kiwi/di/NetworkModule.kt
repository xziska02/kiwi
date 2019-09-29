package com.peter.ziska.kiwi.di

import com.peter.ziska.kiwi.network.FlightService
import com.peter.ziska.kiwi.network.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideFlightApi(retrofit: Retrofit): FlightService {
        return retrofit.create(FlightService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideHttpInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        return logger
    }


    @Provides
    @Singleton
    internal fun provideClient(logger: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(logger).build()

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(
        gson: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gson)
            .client(client)
            .baseUrl("https://api.skypicker.com/")
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }
}