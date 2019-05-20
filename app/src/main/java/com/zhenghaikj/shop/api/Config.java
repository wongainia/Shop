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
    public static final String URL = "http://mall.xigyu.com/";//测试服

    public static final int CHOOSE_ADDRESS_REQUEST=10001; //选择地址请求码
    public static final int CHOOSE_ADDRESS_RESULT=10002; //选择地址返回

    public static final int RECEIPT_REQUEST=20001;//发单请求码
    public static final int RECEIPT_RESULT=20002;//发单结果码





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
