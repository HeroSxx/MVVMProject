package com.example.networkmodule.di.module;

import android.app.Application;
import android.content.Context;


import com.example.networkmodule.di.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * class_name: ApplicationModule
 * package_name: com.i5d5.basemodule.DI.Module
 * author: lijun
 * time: 2017/3/28 16:45
 */

@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideApplicationContext() {
        return this.application;
    }


}