package com.bangkit.aiQuiTion.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.aiQuiTion.data.remote.AirAPI
import com.bangkit.aiQuiTion.data.remote.AirResponse
import com.bangkit.aiQuiTion.data.remote.RetrofitClient
import com.bangkit.aiQuiTion.data.remote.model.Aqi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel() : ViewModel() {

    private val  listData = MutableLiveData<ArrayList<Aqi>>()

    fun setDataList(city : String) {
        RetrofitClient.apiInstance
            .getData(city, AirAPI.API_KEY)
            .enqueue(object : Callback<AirResponse> {
                override fun onResponse(
                    call: Call<AirResponse>,
                    response: Response<AirResponse>
                ) {
                    if (response.isSuccessful) {
                        listData.postValue(response.body()?.stations)
                    } else {
                        Log.e("Failure", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<AirResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getDataList(): LiveData<ArrayList<Aqi>> {
        return listData
    }
}