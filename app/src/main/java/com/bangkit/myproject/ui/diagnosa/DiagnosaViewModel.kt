package com.bangkit.myproject.ui.diagnosa

import androidx.lifecycle.ViewModel
import com.bangkit.myproject.data.source.MainRepository

class DiagnosaViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun insertDiagnoseTest(
        name: String,
        age: Int,
        sex: Boolean,
        result: String,
        percentage: String,
        image: String,
    ) = mainRepository.insertDiagnose(name, age, sex, result, percentage, image)
}