package com.example.networkmodule.di.module;

import android.content.Context;


import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.example.networkmodule.constant.Constant;
import com.example.networkmodule.di.qualifier.ApplicationContext;
import com.example.networkmodule.gsonfactory.GsonConverterFactory;
import com.example.networkmodule.util.LogUtils;
import com.example.networkmodule.util.SystemUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * class_name: ApiModule
 * package_name: com.i5d5.basemodule.DI.Module
 * author: lijun
 * time: 2017/3/29 10:23
 */
@Module
public class ApiModule {
    public ApiModule() {
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(@ApplicationContext final Context context) {
//日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
//                if (message.contains("Set-Cookie")) {
//                    LogUtils.d("===okhttp===", message);
//                } else {
                LogUtils.d("===okhttp===", message);
//                }
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //统一设置header token
//        Interceptor headInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                SPUtils spUtils = new SPUtils(context);
//                String token = spUtils.getUsToken();
//                Request newRequest = chain.request().newBuilder()
//                        .addHeader("token", token)
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        };
        //第三方cookie持久化
//        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(),
//                new SharedPrefsCookiePersistor(context));
//        缓存大小
//        int cacheSize = 30 * 1024 * 1024; // 30 MiB
//        String cacheDir = Environment.getExternalStorageDirectory() + File.separator + "cache";
//        File file = new File(cacheDir);
//        Cache cache = new Cache(file, cacheSize);

        return new OkHttpClient.Builder()
                //header拦截器 可根据
//                .addInterceptor(headInterceptor)
//                 缓存设置
//                .addInterceptor(new CacheInterceptor(context))
//                .addNetworkInterceptor(new CacheNetworkInterceptor())
//                .cache(cache)
//                .cookieJar(cookieJar)
                .addInterceptor(httpLoggingInterceptor)
//
                //设置user-Agent传设备信息等
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .removeHeader("User-Agent")//移除旧的
                                .addHeader("User-Agent", SystemUtil.getUserAgent(context))//添加真正的头部
                                .build();
                        return chain.proceed(request);
                    }
                })
                .retryOnConnectionFailure(true)
                .connectTimeout(45, TimeUnit.SECONDS)
//                .sslSocketFactory(sslSocketFactory)
                .build();


    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }


}

