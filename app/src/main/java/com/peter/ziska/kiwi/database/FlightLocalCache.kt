package com.peter.ziska.kiwi.database

import androidx.lifecycle.LiveData
import com.peter.ziska.kiwi.database.entity.Data
import com.peter.ziska.kiwi.database.entity.Flight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FlightLocalCache(
    private val dao: FlightDao,
    private val scope: CoroutineScope
) {
    fun insertAll(flight: Flight, flightData: List<Data>) {
        scope.launch {
            dao.deleteFlight()
            val insertedFlightId = dao.insertFlight(flight)
            flightData.forEach {
                it.flightIdentifier = insertedFlightId
                dao.insertFlightDatum(it)
            }
        }
    }

    fun getData(limit: Int): LiveData<List<Data>> {
        return dao.getData(limit)
    }

    fun getFlightData() = dao.getFlightData()
}