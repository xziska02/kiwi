package com.peter.ziska.kiwi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.peter.ziska.kiwi.database.entity.FLightData
import com.peter.ziska.kiwi.extensions.getRandomDate
import com.peter.ziska.kiwi.extensions.getRandomDateFrom
import com.peter.ziska.kiwi.extensions.today
import com.peter.ziska.kiwi.network.Resource
import javax.inject.Inject

class FlightViewModel @Inject constructor(private val repo: FlightRepository) : ViewModel() {

    private val dateFrom = today().getRandomDate(WEEK)
    private val dateTo = getRandomDateFrom(dateFrom, MONTH)

    val flights = MediatorLiveData<Resource<List<FLightData>>>()
    private val result: LiveData<Resource<List<FLightData>>> = repo.fetchFlight(
        COUNTRY,
        dateFrom,
        dateTo,
        RANDOM_FLIGHTS_FETCHED_COUNT
    )

    init {
        flights.addSource(result) { result ->
            result?.let { flights.value = result }
        }
    }

    fun refreshData() {
        flights.removeSource(result)
        flights.addSource(
            repo.retryRequest(
                COUNTRY,
                dateFrom,
                dateTo,
                RANDOM_FLIGHTS_FETCHED_COUNT,
                flights.value?.data
            )
        ) { result ->
            result?.let { flights.value = result }
        }
    }

    companion object {
        private const val WEEK = 7
        private const val MONTH = 31
        private const val RANDOM_FLIGHTS_FETCHED_COUNT = 5
        private const val COUNTRY = "CZ"
    }
}
