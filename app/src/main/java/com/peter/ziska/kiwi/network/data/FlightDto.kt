package com.peter.ziska.kiwi.network.data

import com.google.gson.annotations.SerializedName

class FlightDto(
    @SerializedName("data")
    val data: List<DataDto>,
    @SerializedName("currency")
    var currency: String = "EUR",
    @SerializedName("currency_rate")
    var currencyRate: Double = 0.0
)