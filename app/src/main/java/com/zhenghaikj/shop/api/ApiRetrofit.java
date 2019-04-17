package com.zhenghaikj.shop.api;

import com.zhenghaikj.shop.utils.CEComplexComparator;
import com.zhenghaikj.shop.utils.MD5Util;
import com.zhenghaikj.shop.utils.MyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {

    private static ApiService SERVICE;
    public static ApiService getDefault(){
//        if (SERVICE == null){
            SERVICE = new Retrofit.Builder()
            .baseUrl(Config.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(Config.getClient())
            .build()
            .create(ApiService.class);
//        }
        return SERVICE;
    }
    public static  String SignTopRequest(Map<String, String> sortedParams)
    {
        ArrayList<String> list=new ArrayList<>();
        for (String key:sortedParams.keySet()){
            list.add(key);
        }
        CEComplexComparator com=new CEComplexComparator();
        Collections.sort(list, com);
        String text="";
        for(String i:list){
            text+=i+sortedParams.get(i);
        }
        text+="has2f5zbd4";
        return  MD5Util.MD5Encode(text,"utf-8");
    }
}
