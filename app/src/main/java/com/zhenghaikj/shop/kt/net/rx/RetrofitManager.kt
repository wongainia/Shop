package com.zhenghaikj.shop.kt.net.rx

import android.util.Log
import com.zhenghaikj.shop.kt.net.api.ApiService
import com.zhenghaikj.shop.kt.net.api.UrlConstant
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
Data:2019/8/14
Time:15:09
author:ying
 **/
object RetrofitManager {
    val apiService:ApiService by lazy{
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit():Retrofit{
        //获取Retrofit实例
        return Retrofit.Builder()
                .baseUrl(UrlConstant.BASE_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    private fun getOkHttpClient(): OkHttpClient {
        //添加一个log拦截器,打印所有的log
        val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String? -> Log.e("okhttp", message) })
        //可以设置请求过滤的水平,body,basic,headers
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //设置 请求的缓存的大小跟位置
        //val cacheFile = File(MyApp.context.cacheDir, "cache")
        //val cache = Cache(cacheFile, 1024 * 1024 * 50) //50Mb 缓存的大小

        return OkHttpClient.Builder()
//            .addInterceptor(addQueryParameterInterceptor())  //参数添加
//            .addInterceptor(addHeaderInterceptor()) // token过滤
                .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
//            .addNetworkInterceptor(addCacheInterceptor())// 添加缓存
//            .addInterceptor(addCacheInterceptor())// 添加缓存
                //.cache(cache)  //添加缓存
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .build()
    }

    /**
     * 设置公共参数
     */


    /**
     * 设置头
     */







}