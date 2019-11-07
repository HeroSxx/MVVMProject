package com.example.myapplication.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
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
