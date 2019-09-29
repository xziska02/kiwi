package com.peter.ziska.kiwi.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.peter.ziska.kiwi.prefs.FlightPreferences

class FlightWorker(
    val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val preferences = FlightPreferences(context)
        preferences.updateFlag(true)
        return Result.success()
    }

}