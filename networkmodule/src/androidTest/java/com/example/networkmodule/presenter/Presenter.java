package com.example.networkmodule.presenter;

/**
 * class_name: Presenter
 * package_name: com.i5d5.wuding.WD.Presenter
 * author: lijun
 * time: 2017/3/29 12:38
 */

public interface Presenter<V extends MvpView> {
    /**
     * 绑定视图
     *
     * @param mvpView presenter与view交互接口
     */
    void attachView(V mvpView);

    /**
     * 关闭页面解绑
     */
    void detachView();
}
