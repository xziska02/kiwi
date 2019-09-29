package com.peter.ziska.kiwi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peter.ziska.kiwi.database.entity.Data
import com.peter.ziska.kiwi.database.entity.Flight

@Database(entities = [Flight::class, Data::class], version = 2, exportSchema = false)
abstract class FlightDatabase : RoomDatabase() {

    abstract fun getDao(): FlightDao

}