package com.example.networkmodule.di.component;

import android.content.Context;


import com.example.networkmodule.di.module.ApiModule;
import com.example.networkmodule.di.module.ApplicationModule;
import com.example.networkmodule.di.qualifier.ApplicationContext;
import com.example.networkmodule.util.SPUtils;
import com.example.networkmodule.util.ToastUtils;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * class_name: ApplicationComponent
 * package_name: com.i5d5.basemodule.DI.Component
 * author: lijun
 * 全局component，主要存放一些使用APP生命周期的网络请求和工具类
 * time: 2017/3/28 16:56
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    @ApplicationContext
    Context context();

    SPUtils sputils();

    Retrofit retrofit();

    ToastUtils toastUtils();
}
