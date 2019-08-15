package com.zhenghaikj.shop.kt.net.api

import com.zhenghaikj.shop.kt.bean.HistoryBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
Data:2019/8/14
Time:14:54
author:ying
 **/
interface ApiService {
    /**
     * 获取我的足迹（商品浏览记录）列表
     * @return
     */

    @GET("api/product/GetHistoryVisite")
    fun GetHistoryVisite(
            @Query("rows")     rows:String,
            @Query("page")     page:String ,
            @Query("userkey")  userkey:String ,
            @Query("app_key")  app_key:String ,
            @Query("timestamp")timestamp:String ,
            @Query("sign")     sign:String
        ):Observable<HistoryBean>


}