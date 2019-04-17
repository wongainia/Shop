package com.zhenghaikj.shop.api;

import com.zhenghaikj.shop.utils.MyUtils;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2018/4/18.
 */
public class Config {
    public static boolean IS_DEBUG = true;
    public static final String URL = "http://47.96.126.145:8830/api/";//测试服
    static HttpLoggingInterceptor loggingInterceptor;

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        if (null == loggingInterceptor) {
            loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    MyUtils.e("okhttp", message );
                }
            });
        }
        if (IS_DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return loggingInterceptor;
    }

    static Interceptor interceptor;

    public static Interceptor getInterceptor() {
        if (null == interceptor) {
            interceptor = chain -> {
                Request.Builder builder=chain.request().newBuilder();
                return   chain.proceed(builder
                        .build());
            };
        }
        return interceptor;
    }

    static OkHttpClient client;

    public static OkHttpClient getClient() {
        if (null == client) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(getInterceptor())
                    .addInterceptor(getLoggingInterceptor())
                    .build();
        }
        return client;
    }
}
