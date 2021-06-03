package com.bangkit.myproject.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.myproject.data.source.MainRepository
import com.bangkit.myproject.data.source.local.entity.DiagnoseEntity

class HistoryViewModel(private val mainRepository: MainRepository) : ViewModel() {

   fun getHistoryDiagnose(): LiveData<List<DiagnoseEntity>> {
       return mainRepository.getDiagnose()
   }
}