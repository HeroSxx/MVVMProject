package com.example.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.extension.increment
import com.example.myapplication.extension.showToast
import com.example.myapplication.model.UserModel
import com.example.myapplication.viewModel.TestViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var userModel: UserModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var testViewModel: TestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initViewModel()
    }

    private fun initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userModel = UserModel(ObservableField("阿里"), null, ObservableInt(23))
        activityMainBinding.user = userModel
    }

    /**textView的点击方法*/
    public fun addAge(view: View) {
        var oldAge = activityMainBinding?.user?.age?.get()
        activityMainBinding?.user?.age?.increment()
        testViewModel.nameEvent.postValue(oldAge.toString())
        Log.e("====addAge====  ", "$oldAge")
//        activityMainBinding?.user?.age?.set(oldAge!!.plus(1))
    }


    private fun initViewModel() {
        testViewModel = ViewModelProviders.of(this, TestViewModel.TestViewModelFactory("初始值"))
            .get(TestViewModel::class.java)
        testViewModel.nameEvent.observe(this, Observer {
            it?.let { it1 -> showToast(it1) }
            Log.e("====observe====  ", it)
        })
    }
}
