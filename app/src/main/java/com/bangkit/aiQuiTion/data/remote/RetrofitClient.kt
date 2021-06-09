package com.bangkit.aiQuiTion.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(AirAPI.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    val apiInstance = retrofit.create(AirAPI::class.java)

}