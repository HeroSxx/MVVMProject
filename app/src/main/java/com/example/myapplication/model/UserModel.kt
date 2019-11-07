package com.example.myapplication.model

import android.databinding.ObservableField
import android.databinding.ObservableInt

data class UserModel(
    var name: ObservableField<String>,
    var sex: String?,
    var age: ObservableInt,
    var chinese:Boolean
)