package com.peter.ziska.kiwi.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun today(): Date {
    val dateFormat = SimpleDateFormat("d/M/yyyy")
    return Date(System.currentTimeMillis())
}

fun Date.getRandomDate(randomBound: Int): String {
    val dateFormat = SimpleDateFormat("d/M/yyyy")
    val randomDays = Random.nextInt(randomBound)
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DATE, randomDays)

    val randomDate = calendar.time
    return dateFormat.format(randomDate)
}

fun getRandomDateFrom(fromDate: String, randomBound: Int): String {
    val dateFormat = SimpleDateFormat("d/M/yyyy")
    val randomDays = Random.nextInt(randomBound)
    val calendar = Calendar.getInstance()
    calendar.time = dateFormat.parse(fromDate)
    calendar.add(Calendar.DATE, randomDays)

    val randomDate = calendar.time
    return dateFormat.format(randomDate)
}