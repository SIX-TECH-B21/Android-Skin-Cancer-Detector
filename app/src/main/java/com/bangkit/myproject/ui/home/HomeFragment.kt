package com.bangkit.myproject.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.myproject.databinding.FragmentHomeBinding
import com.bangkit.myproject.diseaselist.DiseaseListActivity
import com.bangkit.myproject.ui.adapter.ArticlesAdapter
import com.bangkit.myproject.ui.detail.DetailArtikelActivity
import com.bangkit.myproject.ui.diagnosa.BiodataDiagnoseActivity
import com.bangkit.myproject.viewmodel.ViewModelFactory
import com.bangkit.myproject.vo.Status
import com.google.android.gms.location.*

class HomeFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
            val articlesAdapter = ArticlesAdapter()

            articlesAdapter.onItemClick = {
                val intent = Intent(activity, DetailArtikelActivity::class.java)
                intent.putExtra(DetailArtikelActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            viewModel.getArticles().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> binding.progress.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progress.visibility = View.GONE
                            articlesAdapter.setArticles(it.data)
                            articlesAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding.progress.visibility = View.GONE

                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.rvArticles) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = articlesAdapter
            }
            viewModel.getBanners().observe(viewLifecycleOwner, {
                binding.imgSlider.setImageList(it)
            })
        }
        checkPermission()
        diseaseListShow()
    }

    private fun diseaseListShow() {
        binding.btnDiseaseInfo.setOnClickListener {
            val intent = Intent(requireActivity(), DiseaseListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkPermission() {
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
                checkLocation()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocation()
            } else {
                Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkLocation() {
        val locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showAlertLocation()
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        getCurrentLocation()
    }


    private fun showAlertLocation() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setMessage("Pengaturan lokasi anda belum aktif nih, Harap diaktifkan dahulu ya kak!")
        dialog.setPositiveButton("Aktifkan GPS") { _, _ ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        dialog.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
            binding.progress.visibility = View.GONE
        }
        dialog.setCancelable(false)
        dialog.show()
    }


    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val locationRequest = LocationRequest.create().apply {
            binding.progress.visibility = View.VISIBLE
            interval = 50000
            fastestInterval = 50000
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

    companion object {
        const val REQUEST_PERMISSION = 101
    }
}