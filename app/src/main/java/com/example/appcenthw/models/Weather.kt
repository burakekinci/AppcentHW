package com.example.appcenthw.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//bir şehrin hava durumu için model
data class Weather(
    @SerializedName("weather_state_name") @Expose var weatherStateName : String,
    @SerializedName("weather_state_abbr") @Expose var weatherStateAbbr : String,
    @SerializedName("the_temp") @Expose var temp : Double,
    @SerializedName("applicable_date") @Expose var date : String
): Serializable
