package com.zhenghaikj.shop.kt.base

/**
Data:2019/8/14
Time:14:21
author:ying
 **/
interface IBaseView {
    /**
     * 加载中
     */
    fun showLoading()

    /**
     * 无网络
     */
    fun showNoNetwork()

    /**
     * 显示错误视图
     */
    fun showError()

    /**
     * 显示内容视图
     */
    fun showContent()

    /**
     * 取消加载中
     */
    fun dismissLoading()

    /**
     * 显示错误提示
     */
    fun showErrorMsg(msg: String)

}