package com.peter.ziska.kiwi.di

import com.peter.ziska.kiwi.FlightApplication
import com.peter.ziska.kiwi.ui.main.FlightActivity
import com.peter.ziska.kiwi.ui.main.FlightFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeApplication(): FlightApplication

    @ContributesAndroidInjector
    internal abstract fun contributeFlightActivity(): FlightActivity

    @ContributesAndroidInjector
    internal abstract fun contributeFlightFragment(): FlightFragment
}