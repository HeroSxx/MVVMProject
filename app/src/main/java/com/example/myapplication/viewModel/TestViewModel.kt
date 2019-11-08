package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.model.CityModel


class TestViewModel(private val key: String) : ViewModel() {
    val nameEvent = MutableLiveData<String>()
    val cityModel = MutableLiveData<CityModel>()

    init {
        Log.e("===TestViewModel===", key)
    }

    class TestViewModelFactory(private val key: String) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TestViewModel(key) as T
        }
    }
}
