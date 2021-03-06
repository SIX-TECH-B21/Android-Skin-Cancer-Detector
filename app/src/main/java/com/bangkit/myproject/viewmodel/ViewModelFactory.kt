package com.bangkit.myproject.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.myproject.data.source.MainRepository
import com.bangkit.myproject.di.Injection
import com.bangkit.myproject.ui.detail.DetailHistoryViewModel
import com.bangkit.myproject.ui.diagnosa.DiagnosaViewModel
import com.bangkit.myproject.ui.history.HistoryViewModel
import com.bangkit.myproject.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory(){

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mainRepository) as T
            }
            modelClass.isAssignableFrom(DiagnosaViewModel::class.java) -> {
                DiagnosaViewModel(mainRepository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(mainRepository) as T
            }
            modelClass.isAssignableFrom(DetailHistoryViewModel::class.java) -> {
                DetailHistoryViewModel(mainRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}