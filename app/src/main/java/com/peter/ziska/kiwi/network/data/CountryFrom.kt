package com.peter.ziska.kiwi.network.data


import com.google.gson.annotations.SerializedName

data class CountryFrom(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String
)