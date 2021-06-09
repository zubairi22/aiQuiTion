package com.bangkit.aiQuiTion.data.remote

import com.bangkit.aiQuiTion.data.remote.model.Aqi

data class AirResponse (
    val pollutant: ArrayList<Aqi>
)