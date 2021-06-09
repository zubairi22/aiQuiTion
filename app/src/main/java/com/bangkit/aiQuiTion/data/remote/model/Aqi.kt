package com.bangkit.aiQuiTion.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Aqi (
    val CO: Double,
    val NO2: Double,
    val O3: Double,
    val PM10: Double,
    val SO2: Double,
    val city: String,
    val AQI: Double,
    val date: String
):Parcelable