package com.example.myapplication.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log


class TestViewModel(private val key: String) : ViewModel() {
    val nameEvent = MutableLiveData<String>()

    init {
        Log.e("===TestViewModel===", key)
    }

    class TestViewModelFactory(val name: String) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TestViewModel(name) as T
        }
    }
}
