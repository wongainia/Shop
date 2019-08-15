package com.zhenghaikj.shop.kt.mvp.presenter

import com.blankj.utilcode.util.TimeUtils
import com.zhenghaikj.shop.api.ApiRetrofit
import com.zhenghaikj.shop.kt.base.BaseRxPresenter
import com.zhenghaikj.shop.kt.mvp.contract.GetHistoryVisiteContract
import com.zhenghaikj.shop.kt.net.exception.ExceptionHandle
import com.zhenghaikj.shop.kt.net.rx.RetrofitManager
import com.zhenghaikj.shop.kt.net.rx.RxManage
import java.text.SimpleDateFormat
import java.util.HashMap

/**
Data:2019/8/14
Time:15:32
author:ying
 **/
class GetHistoryVisitePresenter:BaseRxPresenter<GetHistoryVisiteContract.View>(),GetHistoryVisiteContract.Presenter{
    private var map: MutableMap<String, String>? = null
    private var sign: String? = null
    private var timestamp: String? = null



    override fun GetHistoryVisite(rows: String, page: String, userkey: String) {
        map = HashMap()
        map!!["rows"] = rows
        map!!["page"] = page
        map!!["userkey"] = userkey
        map!!["app_key"] = "himalltest"
        timestamp = TimeUtils.getNowString(SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
        map!!["timestamp"] = timestamp!!
        sign = ApiRetrofit.SignTopRequest(map)

    addSubScription(
     RetrofitManager.apiService.GetHistoryVisite(rows,page,userkey,"himalltest",timestamp!!,sign!!)
             .compose(RxManage.rxSchedulerObservableHelper())
             .subscribe({
                 run {->
                     mView?.GetHistoryVisite(it)
                     mView?.dismissLoading()
                 }
             }, {
                  t->
                    mView?.dismissLoading()
                    mView.let { ExceptionHandle.handleException(t,it)}
                     }
                     )
   )

    }


}