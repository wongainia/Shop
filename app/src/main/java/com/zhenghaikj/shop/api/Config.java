package com.zhenghaikj.shop.api;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.utils.MyUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    public static Interceptor getInterceptor(final String sign) {
        if (null == interceptor) {
            interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                    formBody.add("app_key","himalltest");//传递键值对参数
                    formBody.add("timestamp", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));//传递键值对参数
                    formBody.add("sign",sign);//传递键值对参数
                    Request.Builder builder=chain.request().newBuilder();
                    return   chain.proceed(builder
                            .post(formBody.build())
                            .build());
                }
            };
        }
        return interceptor;
    }

    static OkHttpClient client;

    public static OkHttpClient getClient(String sign) {
        if (null == client) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(getInterceptor(sign))
                    .addInterceptor(getLoggingInterceptor())
                    .build();
        }
        return client;
    }
}
