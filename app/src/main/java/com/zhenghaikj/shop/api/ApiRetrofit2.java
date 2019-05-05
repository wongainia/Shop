package com.zhenghaikj.shop.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit2 {

    private static ApiService2 SERVICE;
    public static ApiService2 getDefault(){
//        if (SERVICE == null){
            SERVICE = new Retrofit.Builder()
            .baseUrl(Config2.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(Config2.getClient())
            .build()
            .create(ApiService2.class);
//        }
        return SERVICE;
    }
}
