package com.example.appcenthw.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcenthw.R
import com.example.appcenthw.adapters.NearbyCitiesAdapter
import com.example.appcenthw.databinding.FragmentSehirlerBinding
import com.example.appcenthw.models.NearbyCity
import com.example.appcenthw.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "OUTPUT"

class SehirlerFragment : Fragment() {

    private var _binding : FragmentSehirlerBinding? = null
    private val binding get() = _binding!!
    private lateinit var lattlong: String

    var nearbyCityList : ArrayList<NearbyCity> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSehirlerBinding.inflate(layoutInflater)
        lattlong = arguments?.getString("data").toString()
        getNearbyCities()
        // Use the Kotlin extension in the fragment-ktx artifact

        Log.d(TAG,"gelen data: ${lattlong}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null

    }

    private fun getNearbyCities(){
        ApiUtils.getNearbyCityDAOInterface().getNearbyCities(lattlong).enqueue(
            object  : Callback<List<NearbyCity>>{
                override fun onResponse(
                    call: Call<List<NearbyCity>>,
                    response: Response<List<NearbyCity>>
                ) {
                    val tempList = response.body()

                    tempList?.let{
                        nearbyCityList = it as ArrayList<NearbyCity>
                    }


                    val cityAdapter = NearbyCitiesAdapter(nearbyCityList)
                    binding.sehirRV.adapter = cityAdapter
                    binding.sehirRV.layoutManager = LinearLayoutManager(context)
                    binding.sehirRV.setHasFixedSize(true)
                }

                override fun onFailure(call: Call<List<NearbyCity>>, t: Throwable) {
                    Log.d(TAG,"sehir listesi alinamadi")
                }
            }
        )
    }

}