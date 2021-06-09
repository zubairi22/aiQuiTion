package com.bangkit.aiQuiTion.data.remote

import com.bangkit.aiQuiTion.data.remote.model.Aqi

data class AirResponse (
    val message: String,
    val stations: ArrayList<Aqi>
)