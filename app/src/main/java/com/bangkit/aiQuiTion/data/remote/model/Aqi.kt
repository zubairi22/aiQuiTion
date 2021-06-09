package com.bangkit.aiQuiTion.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Aqi (
    val _id: String,
    val CO: Double,
    val NO2: Double,
    val OZONE: Double,
    val PM10: Double,
    val PM25: Double,
    val SO2: Double,
    val city: String,
    val AQI: Int,
    val aqiInfo: AqiInfo?
):Parcelable