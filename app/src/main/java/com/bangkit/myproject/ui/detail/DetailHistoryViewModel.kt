package com.bangkit.myproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bangkit.myproject.data.source.MainRepository
import com.bangkit.myproject.data.source.local.entity.DiagnoseEntity

class DetailHistoryViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val getId = MutableLiveData<Int>()

    fun setSelectedHistoryDiagnose(id: Int) {
        this.getId.value = id
    }

    var historyDiagnose : LiveData<DiagnoseEntity> = Transformations.switchMap(getId) { data ->
        mainRepository.getDiagnoseById(data)
    }
}