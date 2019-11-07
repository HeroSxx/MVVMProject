package com.example.networkmodule.util;

import android.content.Context;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 上传文件工具类
 * Date:2018/5/31
 * Time:16:35
 * author:lijun
 */

public class MultiFileUpLoadUtils {
    //    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("application/octet-stream");

    /**
     * @param context
     * @param url      上传文件的url
     * @param files    文件集合
     * @param callback 上传成功失败的回调
     */
    public static void sendMultipart(Context context, String url, String params, String token, List<String> files, Callback callback) {
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 40 * 1024 * 1024;
        //设置超时时间及缓存
        int timeOut = 400;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));


        OkHttpClient mOkHttpClient = builder.build();

        MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i));
            if (file.exists()) {
                mbody.addFormDataPart(params, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
            }
        }
        RequestBody requestBody = mbody.build();
        Request request = new Request.Builder()
                .header("token", token)
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
}
