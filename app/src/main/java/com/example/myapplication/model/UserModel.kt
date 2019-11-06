package com.example.myapplication.model

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

data class UserModel(
    var name: ObservableField<String>,
    var sex: String?,
    var age: ObservableInt
)