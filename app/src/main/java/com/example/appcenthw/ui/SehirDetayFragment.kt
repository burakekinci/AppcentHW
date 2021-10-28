package com.example.appcenthw.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcenthw.adapters.WeatherAdapter
import com.example.appcenthw.databinding.FragmentSehirDetayBinding
import com.example.appcenthw.models.Weather
import com.example.appcenthw.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "OUTPUT"
class SehirDetayFragment() : Fragment() {


    private var _binding : FragmentSehirDetayBinding? = null
    private val binding get() = _binding!!
    private var woeidValue = 0

    var weatherList : ArrayList<Weather> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSehirDetayBinding.inflate(layoutInflater)

        //gelen "woeid" keyli değişkeni al ve woeidValue'ya ata
        woeidValue = arguments?.getInt("woeid")?.toInt()!!
        Log.d(TAG,"gelen id: ${woeidValue}")

        //havadurumu bilgilerini alan fonksiyonu çalıştır
        getWeather()
        Log.d(TAG, weatherList.toString())
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null

    }


    //havadurumu bilgilerini alan fonksiyon
    private fun getWeather(){
        //getWeatherData fonksiyonunu şehir id'si ile çalıştır ve gelen sorgu sonucuna göre devam et
        ApiUtils.getWeatherDAOInterface().getWeatherData(woeidValue).enqueue(
            object : Callback<List<Weather>>{
                //sorgu sonucu başarılıysa
                override fun onResponse(
                    call: Call<List<Weather>>,
                    response: Response<List<Weather>>
                ) {
                    val tempList = response.body()
                    Log.d(TAG,"tempList : ${tempList.toString()}")
                    tempList?.let{
                        weatherList = it as ArrayList<Weather>
                    }

                    val weatherAdapter = WeatherAdapter(weatherList)
                    binding.havaRV.adapter = weatherAdapter
                    binding.havaRV.layoutManager = LinearLayoutManager(context)
                    binding.havaRV.setHasFixedSize(true)
                }

                //sorgu sonucu başarısızsa
                override fun onFailure(call: Call<List<Weather>>, t: Throwable) {
                    Log.d(TAG,"hava listesi alinamadi")
                }

            }
        )
    }


}