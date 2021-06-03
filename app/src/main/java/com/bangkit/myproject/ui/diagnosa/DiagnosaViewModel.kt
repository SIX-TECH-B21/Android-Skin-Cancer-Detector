package com.bangkit.myproject.ui.diagnosa

import androidx.lifecycle.ViewModel
import com.bangkit.myproject.data.source.MainRepository
import com.bangkit.myproject.data.source.local.entity.DiagnoseEntity

class DiagnosaViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun insertDiagnoseTest(
        name: String,
        age: Int,
        sex: Boolean,
        result: String,
        percentage: String,
        image: String,
    ) = mainRepository.insertDiagnoseTest(name, age, sex, result, percentage, image)
}