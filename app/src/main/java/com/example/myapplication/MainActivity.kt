package com.example.myapplication

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding

import com.example.myapplication.model.UserModel

class MainActivity : AppCompatActivity() {
    private lateinit var userModel: UserModel
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userModel = UserModel(ObservableField("阿里"), null, ObservableInt(23))
        activityMainBinding.user = userModel
    }

    fun addAge(view: View) {
        var oldAge = activityMainBinding?.user?.age?.get()
        activityMainBinding?.user?.age?.increment()
        Log.e("====  ", "$oldAge")
//        activityMainBinding?.user?.age?.set(oldAge!!.plus(1))
    }

    fun ObservableInt.increment() {
        set(get() + 1)
    }
}
