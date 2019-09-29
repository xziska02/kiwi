package com.peter.ziska.kiwi.ui.main

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.annotation.GlideModule
import com.peter.ziska.kiwi.R
import com.peter.ziska.kiwi.database.entity.FLightData
import com.peter.ziska.kiwi.extensions.inflate

@GlideModule
class FlightAdapter(
    private val context: Context?
) : RecyclerView.Adapter<FlightViewHolder>() {

    private val flightData: MutableList<FLightData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        return FlightViewHolder(parent.inflate(R.layout.flight_item))
    }

    override fun getItemCount(): Int = flightData.size

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val item = flightData[position]
        holder.bind(item, this.context)
    }

    fun update(flightData: List<FLightData>) {
        this.flightData.clear()
        notifyDataSetChanged()
        this.flightData.addAll(flightData)
        notifyDataSetChanged()
    }
}
