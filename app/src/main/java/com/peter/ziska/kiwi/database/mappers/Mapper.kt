package com.peter.ziska.kiwi.database.mappers

import com.peter.ziska.kiwi.database.entity.Data
import com.peter.ziska.kiwi.database.entity.Flight
import com.peter.ziska.kiwi.network.data.DataDto
import com.peter.ziska.kiwi.network.data.FlightDto

fun FlightDto.map() = Flight(
    currency,
    currencyRate
)

fun DataDto.map() = Data(
    webDataId = id,
    aTime = aTime,
    dTime = dTime,
    cityFrom = cityFrom,
    cityTo = cityTo,
    distance = distance,
    flyDuration = flyDuration,
    flyFrom = flyFrom,
    flyTo = flyTo,
    nightsInDest = nightsInDest,
    price = price,
    returnDuration = returnDuration,
    mapIdto = mapIdto,
    deepLink = deepLink
)