package com.zhenghaikj.shop.kt.mvp.contract

import com.zhenghaikj.shop.kt.base.IBasePresenter
import com.zhenghaikj.shop.kt.base.IBaseView
import com.zhenghaikj.shop.kt.bean.HistoryBean

/**
Data:2019/8/14
Time:15:28
author:ying
 **/
interface GetHistoryVisiteContract {
    interface View:IBaseView{
        /*
        * 显示应用接口
        **/
        fun  GetHistoryVisite(historyBean: HistoryBean)
    }

      interface Presenter:IBasePresenter<View>{
          fun GetHistoryVisite(rows:String,
                               page:String ,
                               userkey:String)
      }
      /*
      *
      * 获取应用接口
      * */



}