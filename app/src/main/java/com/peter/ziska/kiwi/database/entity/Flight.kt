package com.peter.ziska.kiwi.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
class Flight(
    var currency: String = "EUR",
    var currencyRate: Double = 0.0
) {
    @field:PrimaryKey(autoGenerate = true)
    var flightId: Long = 0

    override fun toString(): String {
        return "Flight(currency='$currency', currencyRate=$currencyRate, flightId=$flightId)"
    }
}
