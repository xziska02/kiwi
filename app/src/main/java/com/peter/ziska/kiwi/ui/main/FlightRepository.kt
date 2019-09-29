package com.peter.ziska.kiwi.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import com.peter.ziska.kiwi.database.FlightLocalCache
import com.peter.ziska.kiwi.database.entity.Data
import com.peter.ziska.kiwi.database.entity.FLightData
import com.peter.ziska.kiwi.database.mappers.map
import com.peter.ziska.kiwi.network.ApiResponse
import com.peter.ziska.kiwi.network.FlightService
import com.peter.ziska.kiwi.network.NetworkBoundRepository
import com.peter.ziska.kiwi.network.Resource
import com.peter.ziska.kiwi.network.data.FlightDto
import javax.inject.Inject
import kotlin.random.Random


class FlightRepository @Inject constructor(
    private val flightLocalCache: FlightLocalCache,
    private val flightService: FlightService
) {

    fun fetchFlight(
        flyFrom: String,
        dateFrom: String,
        dateTo: String,
        fetchedDataCount: Int,
        shouldRefreshData: Boolean = false,
        lastStoredFlights: List<FLightData>? = mutableListOf()
    ): LiveData<Resource<List<FLightData>>> {
        return object : NetworkBoundRepository<List<FLightData>, FlightDto>() {
            override fun shouldFetch(data: List<FLightData>?): Boolean {
                return data == null || data.isEmpty() || shouldRefreshData
            }

            override fun saveFetchData(items: FlightDto) {
                saveData(items, fetchedDataCount, lastStoredFlights)
            }

            override fun loadFromDb(): LiveData<List<FLightData>> {
                return flightLocalCache.getFlightData()
            }

            override fun fetchService(): LiveData<ApiResponse<FlightDto>> {
                return flightService.getFlight(flyFrom, dateFrom, dateTo)
            }

            override fun onFetchFailed(message: String?) {
                Log.e("FAILURE", message)
            }

        }.asLiveData()
    }

    private fun saveData(
        items: FlightDto,
        fetchedDataCount: Int,
        lastStoredFlights: List<FLightData>?
    ) {
        val flight = items.map()
        val flightData = mutableListOf<Data>()
        var i = 0
        while (i < fetchedDataCount) {
            val randomIndex = Random.nextInt(items.data.size)
            val datum = items.data[randomIndex].map()
            if (!flightData.contains(datum) &&
                !flightData.hasSameDestination(datum.cityTo) &&
                !(lastStoredFlights != null && lastStoredFlights.hasSameDestinationCity(datum.cityTo))
            ) {
                flightData.add(datum)
                i += 1
            }
        }
        flightLocalCache.insertAll(flight, flightData)
    }

    fun retryRequest(
        flyFrom: String,
        dateFrom: String,
        dateTo: String,
        fetchedDataCount: Int,
        oldFlights: List<FLightData>?
    ) = fetchFlight(
        flyFrom, dateFrom, dateTo, fetchedDataCount, shouldRefreshData = true,
        lastStoredFlights = oldFlights
    )
}

fun List<FLightData>.hasSameDestinationCity(destination: String): Boolean {
    this.forEach {
        if (it.data.cityTo == destination) {
            return true
        }
    }
    return false
}

fun List<Data>.hasSameDestination(destination: String): Boolean {
    this.forEach { datum ->
        if (datum.cityTo == destination) {
            return true
        }
    }
    return false
}