package com.peter.ziska.kiwi.prefs

import android.content.Context

class FlightPreferences(context: Context?) : SharedPreferencesHelper {

    private val sharedPreferences =
        context?.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun updateFlag(flag: Boolean) {
        sharedPreferences?.edit()?.putBoolean(SHOULD_FETCH_DATA, flag)?.apply()
    }

    override
    fun shouldUpdateFlights(): Boolean =
        sharedPreferences?.getBoolean(SHOULD_FETCH_DATA, false) ?: false

    companion object {
        private const val SHARED_PREFS_NAME = "flights"
        private const val SHOULD_FETCH_DATA = "should_fetch_date"
    }
}