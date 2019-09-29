package com.peter.ziska.kiwi.di

import android.app.Application
import com.peter.ziska.kiwi.FlightApplication
import com.peter.ziska.kiwi.ui.main.FlightActivity
import com.peter.ziska.kiwi.ui.main.FlightFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityModule::class,
        DatabaseModule::class,
        FactoryModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<FlightApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun injectApplication(githubApp: FlightApplication)

    fun inject(activity: FlightActivity)

    fun inject(activity: FlightFragment)
}
