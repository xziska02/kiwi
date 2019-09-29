package com.peter.ziska.kiwi.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peter.ziska.kiwi.database.entity.Data
import com.peter.ziska.kiwi.database.entity.FLightData
import com.peter.ziska.kiwi.database.entity.Flight

@Dao
abstract class FlightDao {

    @Insert
    abstract fun insertFlight(flight: Flight): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertFlightDatum(flightDatum: Data)

    @Query("SELECT * FROM flight_datas ORDER BY id DESC LIMIT :limit")
    abstract fun getData(limit: Int): LiveData<List<Data>>

    @Query("SELECT * FROM flight_datas ORDER BY id DESC")
    abstract fun getData(): LiveData<List<Data>>

    @Query("SELECT * FROM flights JOIN flight_datas ON flights.flightId = flight_datas.flightIdentifier")
    abstract fun getFlightData(): LiveData<List<FLightData>>

    @Query("DELETE FROM flights")
    abstract fun deleteFlight()
}