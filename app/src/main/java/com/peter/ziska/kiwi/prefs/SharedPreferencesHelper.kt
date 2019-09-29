package com.peter.ziska.kiwi.prefs

interface SharedPreferencesHelper {
    fun updateFlag(flag: Boolean)
    fun shouldUpdateFlights(): Boolean
}