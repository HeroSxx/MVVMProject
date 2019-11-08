package com.example.networkmodule.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.androidnetworking.AndroidNetworking;

import com.example.networkmodule.di.component.ApplicationComponent;
import com.example.networkmodule.di.component.DaggerApplicationComponent;
import com.example.networkmodule.di.module.ApiModule;
import com.example.networkmodule.di.module.ApplicationModule;


/**
 * class_name: BaseApplication
 * package_name: com.basemodule
 * author: lijun
 * time: 2018/7/23 16:38
 */

public class BaseApplication extends Application {
    private ApplicationComponent applicationComponent;
    private static BaseApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        AndroidNetworking.initialize(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    public static BaseApplication getInstance() {
        return application;
    }


    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            this.applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .apiModule(new ApiModule())
                    .build();
        }
        return applicationComponent;

    }
}
