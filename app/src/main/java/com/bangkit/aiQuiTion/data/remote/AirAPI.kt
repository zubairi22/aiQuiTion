package com.bangkit.aiQuiTion.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface AirAPI {
    companion object{
        const val BASE_URL = "http://34.101.199.60:5000"
    }


    @GET("/")
    fun getData () : Call<AirResponse>

}