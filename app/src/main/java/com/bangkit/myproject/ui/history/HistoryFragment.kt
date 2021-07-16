package com.bangkit.myproject.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.myproject.databinding.FragmentHistoryBinding
import com.bangkit.myproject.ui.adapter.DiagnosaAdapter
import com.bangkit.myproject.viewmodel.ViewModelFactory

class HistoryFragment : Fragment() {


    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[HistoryViewModel::class.java]

            val historyAdapter = DiagnosaAdapter()


            viewModel.getHistoryDiagnose().observe(viewLifecycleOwner, {
                if (it.isEmpty()) {
                    binding.rvHistoryDiagnose.visibility = View.GONE
                    binding.animationEmpty.visibility = View.VISIBLE
                    binding.viewEmpty.visibility = View.VISIBLE
                } else {
                    historyAdapter.setHistoryDiagnose(it)
                    historyAdapter.notifyDataSetChanged()
                    binding.rvHistoryDiagnose.visibility = View.VISIBLE
                    binding.animationEmpty.visibility = View.GONE
                    binding.viewEmpty.visibility = View.GONE
                }
            })

            with(binding.rvHistoryDiagnose) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = historyAdapter
            }
        }
    }
}

