package com.peter.ziska.kiwi.extensions

import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun Int.headerDateFormat(): String {
    val date = Date(this.toLong() * 1000)
    val dateFormat = SimpleDateFormat("dd MMM, EEE")
    return dateFormat.format(date)
}

fun Int.timeFormat(): String {
    val date = Date(this.toLong() * 1000)
    val dateFormat = SimpleDateFormat("hh:mm")
    return dateFormat.format(date)
}

fun Int.timeFormat(days: Int): String {
    Log.e("TAG", " DAYS: ${days}")
    val date = Date(this.toLong() * 1000)
    val dateFormat = SimpleDateFormat("dd MMM, EEE")
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.DATE, days)
    return dateFormat.format(calendar.time)
}

fun String.timeFormat(): String {
    return ""
}

fun String.loadResourceText(context: Context?, layoutId: Int): String {
    if (context != null) {
        return String.format(context.resources.getString(layoutId), this)
    }
    return this
}

fun loadResourceTexts(context: Context?, layoutId: Int, text1: Any, text2: Any): String {
    if (context != null) {
        return String.format(context.resources.getString(layoutId), text1, text2)
    }
    return "$text1 - $text2"
}
