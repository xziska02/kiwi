package com.peter.ziska.kiwi.network.data


import com.google.gson.annotations.SerializedName

data class SearchParams(
    @SerializedName("flyFrom_type")
    val flyFromType: String,
    @SerializedName("to_type")
    val toType: String
)