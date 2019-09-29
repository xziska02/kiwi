package com.peter.ziska.kiwi.database.entity

import androidx.room.Embedded

data class FLightData(
    @Embedded val flight: Flight,
    @Embedded val data: Data
) {
    override fun toString(): String {
        return "FLightData(flight=$flight, data=$data)"
    }
}