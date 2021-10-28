package com.example.appcenthw.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Retrofit objesi oluşturucusu
class RetrofitClient {
    companion object{
        fun getClient(base_url: String) : Retrofit{
            return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}