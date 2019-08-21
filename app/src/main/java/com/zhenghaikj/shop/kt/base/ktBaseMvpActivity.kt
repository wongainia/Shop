package com.zhenghaikj.shop.kt.base

import android.os.Bundle
import android.widget.Toast

/**
Data:2019/8/14
Time:14:41
author:ying
 **/
abstract class ktBaseMvpActivity<in V:IBaseView,P:IBasePresenter<V>>:ktBaseActivity(),IBaseView{

    var mPresenter:P?=null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mPresenter=createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun initListener() {
        super.initListener()
    }
    /**
     * 释放一些资源
     */
    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }


    /*
    * 加载中
    * */
    override fun showLoading() {
    }

    /*
    * 取消加载
    * */
    override fun dismissLoading() {
    }

    /*无网络*/
    override fun showNoNetwork() {
    }
    /**
     * 显示内容视图
     */
    override fun showContent() {
    }

    /**
     * 显示错误视图
     */
    override fun showError() {
    }

    /**
     * 显示错误提示
     */
    override fun showErrorMsg(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }

    /**
     * 请求网络数据
     */
    abstract fun startNetwork()




    /**
     * 创建Presenter
     */
    abstract fun createPresenter(): P

}