package com.bangkit.aiQuiTion.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface AirAPI {
    companion object{
        const val BASE_URL = "https://api.ambeedata.com/"
        const val API_KEY = "Vgp79sbaVClPcEmXUW9Z1S7BxG3KLoGzRzLwoa00"
    }

    @Headers(
        "Content-type: application/json",
        "Accept: application/json"
    )
    @GET("latest/by-city")
    fun getData (
        @Query("city") city : String,
        @Header("x-api-key") apiKey: String
    ) : Call<AirResponse>

}