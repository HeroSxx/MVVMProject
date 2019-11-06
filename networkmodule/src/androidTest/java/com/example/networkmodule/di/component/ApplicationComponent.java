package com.example.networkmodule.di.component;

import android.content.Context;

import com.basemodule.di.module.ApiModule;
import com.basemodule.di.module.ApplicationModule;
import com.basemodule.di.qualifier.ApplicationContext;
import com.basemodule.util.SPUtils;
import com.basemodule.util.ToastUtils;

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
