package com.example.appcenthw.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


//yakındaki şehirler için model yapısı
data class NearbyCity(
    @SerializedName("distance") @Expose var distance : Int,
    @SerializedName("title") @Expose var title : String,
    @SerializedName("location_type") @Expose var locationType : String,
    @SerializedName("woeid") @Expose var woeid : Int,
    @SerializedName("latt_long") @Expose var lattLong : String

) : Serializable
