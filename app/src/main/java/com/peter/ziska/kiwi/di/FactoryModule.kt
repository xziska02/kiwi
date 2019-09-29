package com.peter.ziska.kiwi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peter.ziska.kiwi.ViewModelFactory
import com.peter.ziska.kiwi.ui.main.FlightViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FlightViewModel::class)
    abstract fun bindflightViewModel(flightViewModel: FlightViewModel): ViewModel
}