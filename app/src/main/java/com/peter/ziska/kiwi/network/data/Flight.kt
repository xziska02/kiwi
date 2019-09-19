package com.peter.ziska.kiwi.network.data


import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("currency_rate")
    val currencyRate: Double,
    @SerializedName("search_params")
    val searchParams: SearchParams,
    @SerializedName("time")
    val time: Int
)