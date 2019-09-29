package com.peter.ziska.kiwi

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.peter.ziska.kiwi.di.DaggerApplicationComponent
import com.peter.ziska.kiwi.worker.FlightWorker
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import java.util.concurrent.TimeUnit


class FlightApplication : DaggerApplication() {

    private val injector =
        DaggerApplicationComponent.builder().application(this).build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        injector

    override fun onCreate() {
        val myWorkBuilder =
            PeriodicWorkRequest.Builder(FlightWorker::class.java, 24, TimeUnit.HOURS)

        val myWork = myWorkBuilder.build()
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("jobTag", ExistingPeriodicWorkPolicy.KEEP, myWork)
        super.onCreate()
    }
}