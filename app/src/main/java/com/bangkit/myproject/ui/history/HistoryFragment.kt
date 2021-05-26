package com.bangkit.myproject.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bangkit.myproject.R
import com.bangkit.myproject.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var dashboardViewModel: HistoryViewModel

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
                ViewModelProvider(this).get(HistoryViewModel::class.java)
        binding = FragmentHistoryBinding.inflate(layoutInflater)

        return binding.root
    }
}