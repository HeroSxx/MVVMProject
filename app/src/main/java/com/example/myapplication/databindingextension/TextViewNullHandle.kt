package com.example.myapplication.databindingextension

import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import com.example.myapplication.R

object TextViewNullHandle {
    @BindingAdapter("hideIfNullValue")
    @JvmStatic
    fun hideIfNullValue(view: TextView, value: String?) {
        view.visibility = if (value.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
//        if (value.isNullOrEmpty()) {
//            view.setText(R.string.user_name)
//        }
    }

}