package com.example.appcenthw.network

import com.example.appcenthw.models.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


//https://www.metaweather.com/api/location/2344116
//hava durumu için doğru sorgu url'sini ayarla
interface WeatherDAOInterface {
    @GET("api/location/{woeid}")
    fun getWeatherData(@Path("woeid") woeid: Int) : Call<List<Weather>>
}