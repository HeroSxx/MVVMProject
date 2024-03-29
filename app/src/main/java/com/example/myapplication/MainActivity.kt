package com.example.myapplication

import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.extension.increment
import com.example.myapplication.extension.showToast
import com.example.myapplication.model.CityModel
import com.example.myapplication.model.UserModel
import com.example.myapplication.viewModel.TestViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var userModel: UserModel
    private lateinit var cityModel: CityModel

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var testViewModel: TestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initViewModel()
    }

    private fun initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userModel = UserModel(ObservableField("阿里"), null, ObservableInt(23), false)
        activityMainBinding.user = userModel
        cityModel = CityModel(ObservableField("青岛"), ObservableInt(370702))
        activityMainBinding.city = cityModel
        activityMainBinding.lifecycleOwner = this
    }

    /**textView的点击方法*/

    public fun changeCity(view: View) {
        var newCity: CityModel = CityModel(ObservableField("潍坊"), ObservableInt(370704))

        if (testViewModel.cityModel.value?.name?.get().toString()=="青岛") {
            newCity = CityModel(ObservableField("潍坊"), ObservableInt(370704))
        } else {
            newCity = CityModel(ObservableField("青岛"), ObservableInt(370702))
        }
        testViewModel.cityModel.value = newCity
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
        testViewModel.cityModel.observe(this, Observer {
            activityMainBinding.city = it
        })
    }
}
