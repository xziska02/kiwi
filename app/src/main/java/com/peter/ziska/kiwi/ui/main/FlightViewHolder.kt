package com.peter.ziska.kiwi.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.peter.ziska.kiwi.R
import com.peter.ziska.kiwi.database.entity.Data
import com.peter.ziska.kiwi.database.entity.FLightData
import com.peter.ziska.kiwi.extensions.headerDateFormat
import com.peter.ziska.kiwi.extensions.loadResourceText
import com.peter.ziska.kiwi.extensions.loadResourceTexts
import com.peter.ziska.kiwi.extensions.timeFormat
import kotlinx.android.synthetic.main.flight_item.view.*
import kotlinx.android.synthetic.main.start_item_cardview.view.*

class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(flightData: FLightData, context: Context?) {
        val flight = flightData.data
        bindStartUi(flightData, context)
        setImage(flight, context)
        itemView.direction.text = loadResourceTexts(
            context,
            R.string.direction_text,
            flightData.data.cityFrom,
            flightData.data.cityTo
        )
        itemView.bookNowBtn.text = loadResourceTexts(
            context,
            R.string.book_now,
            flightData.data.price,
            flightData.flight.currency
        )
        itemView.bookNowBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(flight.deepLink))
            context?.startActivity(intent)
        }
    }

    private fun setImage(flight: Data, context: Context?) {
        val imageUrl = "$IMAGE_URL${flight.mapIdto}.jpg"
        if (context != null) {
            Glide.with(context)
                .load(imageUrl)
                .into(itemView.flightBannerImage)
        }
    }

    private fun bindStartUi(
        flightData: FLightData,
        context: Context?
    ) {
        itemView.startCityToHeaderTextView.text =
            flightData.data.cityTo.loadResourceText(context, R.string.header_to_city_name)
        itemView.startFromDate.text = flightData.data.dTime.headerDateFormat()
        itemView.startToDate.text = flightData.data.aTime.headerDateFormat()
        itemView.startCityFromName.text = flightData.data.cityFrom
        itemView.startFromTime.text = flightData.data.dTime.timeFormat()
        itemView.startDuration.text = flightData.data.flyDuration
        itemView.startCityToName.text = flightData.data.cityTo
        itemView.startTimeTo.text = flightData.data.aTime.timeFormat()
    }

    companion object {
        private const val IMAGE_URL = "https://images.kiwi.com/photos/600x330/"
    }
}