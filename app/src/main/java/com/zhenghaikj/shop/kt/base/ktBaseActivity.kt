package com.zhenghaikj.shop.kt.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhenghaikj.shop.kt.util.ActivityManager

/**
Data:2019/8/14
Time:14:28
author:ying
 **/
abstract class ktBaseActivity :AppCompatActivity(){
    lateinit var mContext:Context
    lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext=this.applicationContext
        mActivity=this
        //向activity栈中添加当前activity
        ActivityManager.instance.addActivity(this)
        initView(savedInstanceState)
        initData()
        initListener()
    }
    /**
     * 释放一些资源
     */
    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.instance.removeActivity()
    }
    /**
     *  加载布局
     */
    abstract fun getLayoutId(): Int
    /**
     *  initView
     */
    open fun initView(savedInstanceState: Bundle?) {
    }
    /**
     * 初始化数据
     */
    abstract fun initData()


    open fun initListener() {
    }

}