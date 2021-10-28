package com.example.appcenthw.ui


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.appcenthw.R
import com.example.appcenthw.databinding.FragmentAnasayfaBinding

private const val TAG = "OUTPUT"

@AnasayfaFragment.AndroidEntryPoint
class AnasayfaFragment : Fragment(), LocationListener {
    annotation class AndroidEntryPoint

    private  var _binding : FragmentAnasayfaBinding? = null
    private val binding get() = _binding!!

    //gps bilgileri için
    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLatitude: TextView
    private lateinit var tvGpsLongtiude: TextView
    private lateinit var lattLong : String
    private val locationPermissionCode = 2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAnasayfaBinding.inflate(layoutInflater)

        //degişkenlerin ataması
        tvGpsLatitude = binding.latitudeTextView
        tvGpsLongtiude = binding.longitudeTextView

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //gps butonuna basıldığında konumu al
        binding.gpsButton.setOnClickListener {
            getLocation()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null

    }




    //gps de anlık lokasyon bilgilerini almak için gerekli fonksiyonlar
    private fun getLocation() {
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }
    @SuppressLint("SetTextI18n")
    override fun onLocationChanged(location: Location) {

        val roundedLatitude = String.format("%.3f",location.latitude)
        val roundedLongitude = String.format("%.3f",location.longitude)
        tvGpsLatitude.text = "Latitude : $roundedLatitude"
        tvGpsLongtiude.text = "Longitude: $roundedLongitude"

        lattLong = "$roundedLatitude,$roundedLongitude"


        //koordinat bilgisini sehirlerFragmentına gönder ve şehirleri listlemek için fragmentı başlat
        val bundle = bundleOf("data" to lattLong)
        view?.findNavController()?.navigate(R.id.action_anasayfaFragment_to_sehirlerFragment, bundle)
        Log.d(TAG, "location : ${lattLong}")



    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }



}