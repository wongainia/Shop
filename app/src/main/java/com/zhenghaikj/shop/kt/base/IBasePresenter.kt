package com.zhenghaikj.shop.kt.base

/**
Data:2019/8/14
Time:14:21
author:ying
 **/
interface IBasePresenter<in V:IBaseView> {

    fun attachView(view:V)

    fun detachView()
}