package com.peter.ziska.kiwi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peter.ziska.kiwi.ui.main.FlightRepository
import com.peter.ziska.kiwi.ui.main.FlightViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: FlightRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlightViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlightViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}