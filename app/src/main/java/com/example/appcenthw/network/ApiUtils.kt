package com.example.appcenthw.network

import retrofit2.create


//hava durumu ve yakındaki şehirler için uygun url'i ayarla
class ApiUtils {
    companion object{
        private const val BASE_URL = "https://www.metaweather.com/"

        fun getNearbyCityDAOInterface() : NearbyCityDAOInterface{
            return RetrofitClient.getClient(BASE_URL).create(NearbyCityDAOInterface::class.java)
        }

        fun getWeatherDAOInterface() : WeatherDAOInterface{
            return RetrofitClient.getClient(BASE_URL).create(WeatherDAOInterface::class.java)
        }
    }
}