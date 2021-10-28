package com.example.appcenthw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenthw.databinding.HavaCardDesignBinding
import com.example.appcenthw.models.Weather
import com.squareup.picasso.Picasso
import java.util.zip.Inflater


private const val imageUrlCore = "https://www.metaweather.com/static/img/weather/"
private const val imageExtension = ".svg"


class WeatherAdapter(private var weatherList: ArrayList<Weather>)
    : RecyclerView.Adapter<WeatherAdapter.WeatherCardDesign>(){

        class WeatherCardDesign(val weatherCardBinding : HavaCardDesignBinding)
            : RecyclerView.ViewHolder(weatherCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherCardDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val weatherCardBinding = HavaCardDesignBinding.inflate(layoutInflater,parent,false)
        return WeatherCardDesign(weatherCardBinding)
    }

    override fun onBindViewHolder(holder: WeatherCardDesign, position: Int) {
        val weather = weatherList[position]

        //her bir recyclerView itemi için gelen verileri uygula
        holder.weatherCardBinding.apply {
            gunTextView.text = weather.date
            havaDurumTextView.text = weather.weatherStateName
            gunSicaklikTextView.text = weather.temp.toString()
            val imgName = weather.weatherStateAbbr
            Picasso.get().load(imageUrlCore+imgName+ imageExtension).into(gunImageView)
        }
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}