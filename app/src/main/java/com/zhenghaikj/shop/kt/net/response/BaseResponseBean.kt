package com.zhenghaikj.shop.kt.net.response

/**
Data:2019/8/14
Time:15:07
author:ying
 **/
class BaseResponseBean<T> (
        val code :Int,
        val msg:String,
        val data:T
)


