package com.peter.ziska.kiwi.di

import android.app.Application
import androidx.room.Room
import com.peter.ziska.kiwi.database.FlightDao
import com.peter.ziska.kiwi.database.FlightDatabase
import com.peter.ziska.kiwi.database.FlightLocalCache
import com.peter.ziska.kiwi.prefs.FlightPreferences
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): FlightDatabase =
        Room.databaseBuilder(application, FlightDatabase::class.java, "flights2.db").build()

    @Provides
    @Singleton
    fun providesPreferences(application: Application): FlightPreferences =
        FlightPreferences(application)

    @Provides
    @Singleton
    fun providesDao(database: FlightDatabase): FlightDao =
        database.getDao()

    @Provides
    @Singleton
    fun provideCache(dao: FlightDao): FlightLocalCache {
        return FlightLocalCache(dao, CoroutineScope(Dispatchers.IO))
    }
}