package com.peter.ziska.kiwi.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "flight_datas", foreignKeys = [ForeignKey(
        entity = Flight::class,
        parentColumns = arrayOf("flightId"),
        childColumns = arrayOf("flightIdentifier"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Data(
    var webDataId: String,
    var aTime: Int,
    var dTime: Int,
    var cityFrom: String,
    var cityTo: String,
    var distance: Double,
    var flyDuration: String,
    var flyFrom: String,
    var flyTo: String,
    var nightsInDest: Int,
    var price: Int,
    var returnDuration: String?,
    var mapIdto: String,
    var deepLink: String,
    var flightIdentifier: Long = 0
) {
    @field:PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return "Data(webDataId='$webDataId', aTime=$aTime, dTime=$dTime, cityFrom='$cityFrom', cityTo='$cityTo', distance=$distance, flyDuration='$flyDuration', flyFrom='$flyFrom', flyTo='$flyTo', nightsInDest=$nightsInDest, price=$price, returnDuration=$returnDuration, mapIdto='$mapIdto', deepLink='$deepLink', flightIdentifier=$flightIdentifier, id=$id)"
    }


}