package com.example.myapplication.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.example.myapplication.model.UserModel


class TestViewModel(private val key: String) : ViewModel() {
    val nameEvent = MutableLiveData<String>()
//    val userLiveData = MutableLiveData<UserModel>()

    init {
        Log.e("===TestViewModel===", key)
    }

    class TestViewModelFactory(val key: String) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TestViewModel(key) as T
        }
    }
}
