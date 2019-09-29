package com.peter.ziska.kiwi.network.data


import com.google.gson.annotations.SerializedName

data class DataDto(
    @SerializedName("id")
    var id: String = "id",
    @SerializedName("aTime")
    var aTime: Int,
    @SerializedName("dTime")
    var dTime: Int,
    @SerializedName("cityFrom")
    var cityFrom: String,
    @SerializedName("cityTo")
    var cityTo: String,
    @SerializedName("distance")
    var distance: Double,
    @SerializedName("fly_duration")
    var flyDuration: String,
    @SerializedName("flyFrom")
    var flyFrom: String,
    @SerializedName("flyTo")
    var flyTo: String,
    @SerializedName("nightsInDest")
    var nightsInDest: Int,
    @SerializedName("price")
    var price: Int,
    @SerializedName("return_duration")
    var returnDuration: String,
    @SerializedName("mapIdto")
    var mapIdto: String,
    @SerializedName("deep_link")
    var deepLink: String
)