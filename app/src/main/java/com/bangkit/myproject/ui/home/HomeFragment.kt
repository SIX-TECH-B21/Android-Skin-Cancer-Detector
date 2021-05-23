package com.bangkit.myproject.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.myproject.R
import com.bangkit.myproject.databinding.FragmentHomeBinding
import com.bangkit.myproject.ui.diagnosa.BiodataDiagnoseActivity
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.gms.location.*

class HomeFragment : Fragment() {


    companion object {
         const val REQUEST_PERMISSION = 101
    }


    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(layoutInflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnDiagnosa.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSION
                )
            } else {
                getCurrentLocation()
            }
        }

        setupSlider()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        binding.progress.visibility = View.VISIBLE
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 3000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        LocationServices.getFusedLocationProviderClient(requireActivity())
            .requestLocationUpdates(locationRequest, object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    LocationServices.getFusedLocationProviderClient(requireActivity())
                        .removeLocationUpdates(this)
                    if (p0.locations.size > 0) {
                        val locIndex = p0.locations.size - 1
                        val intent = Intent(requireActivity(), BiodataDiagnoseActivity::class.java)
                        intent.putExtra(
                            BiodataDiagnoseActivity.EXTRA_LAT,
                            p0.locations[locIndex].latitude
                        )
                        intent.putExtra(
                            BiodataDiagnoseActivity.EXTRA_LONG,
                            p0.locations[locIndex].longitude
                        )
                        startActivity(intent)
                    }
                    binding.progress.visibility = View.GONE
                }
            }, Looper.getMainLooper())

    }

    private fun setupSlider() {
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.placeholder))
        imageList.add(SlideModel(R.drawable.placeholder))
        imageList.add(SlideModel(R.drawable.placeholder))

        binding.imgSlider.setImageList(imageList)
    }
}