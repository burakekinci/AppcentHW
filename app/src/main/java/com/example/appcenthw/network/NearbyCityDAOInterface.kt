package com.example.appcenthw.network

import com.example.appcenthw.models.NearbyCity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


//yakındaki şehirler için doğru sorgu urli hazırla
interface NearbyCityDAOInterface {
    @GET("api/location/search")
    fun getNearbyCities(@Query("lattlong") lattLong: String) : Call<List<NearbyCity>>
}