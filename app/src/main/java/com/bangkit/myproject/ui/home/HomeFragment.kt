package com.bangkit.myproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bangkit.myproject.R
import com.bangkit.myproject.databinding.FragmentHomeBinding
import com.bangkit.myproject.ui.diagnosa.BiodataDiagnosaActivity
import com.denzcoskun.imageslider.models.SlideModel

class HomeFragment : Fragment() {

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
            val intent = Intent(requireActivity(), BiodataDiagnosaActivity::class.java)
            startActivity(intent)
        }

        setupSlider()
    }

    private fun setupSlider() {
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.placeholder))
        imageList.add(SlideModel(R.drawable.placeholder))
        imageList.add(SlideModel(R.drawable.placeholder))

        binding.imgSlider.setImageList(imageList)
    }
}