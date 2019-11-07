package com.example.networkmodule.presenter;


import com.example.networkmodule.rxjava.ErrorModel;

/**
 * class_name: MvpView
 * author: lijun
 * time: 2017/3/29 12:39
 */

public interface MvpView {
    /**
     * 接口错误回调
     */
    void showErrorInfo(ErrorModel errorModel);

    /**
     * 当前页面接口服务器抛异常回调
     */
    void onServerBusy();
}
