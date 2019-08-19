package com.zhenghaikj.shop.kt.ui

import android.util.Log
import com.blankj.utilcode.util.SPUtils
import com.zhenghaikj.shop.R
import com.zhenghaikj.shop.kt.base.ktBaseMvpActivity
import com.zhenghaikj.shop.kt.bean.HistoryBean
import com.zhenghaikj.shop.kt.mvp.contract.GetHistoryVisiteContract
import com.zhenghaikj.shop.kt.mvp.presenter.GetHistoryVisitePresenter
import kotlin.math.log

/**
Data:2019/8/14
Time:15:45
author:ying
 **/
class HistoryActivityKt :ktBaseMvpActivity<GetHistoryVisiteContract.View,GetHistoryVisitePresenter>(),GetHistoryVisiteContract.View {

    val spUtils: SPUtils?= SPUtils.getInstance("token")
    var userKey: String?=null


    override fun createPresenter(): GetHistoryVisitePresenter {
        return GetHistoryVisitePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.kt_activity_histroy
    }

    override fun initData() {
        userKey= spUtils?.getString("UserKey")
        startNetwork()
    }
    override fun startNetwork() {
        mPresenter?.GetHistoryVisite("10","1",userKey!!)
    }
    override fun GetHistoryVisite(historyBean: HistoryBean) {
       when(historyBean.Success){
           "true"-> Log.d("======>", historyBean.Product.size.toString())
       }
    }

}