package com.zhenghaikj.shop.kt.ui

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.SPUtils
import com.zhenghaikj.shop.R
import com.zhenghaikj.shop.kt.base.ktBaseMvpActivity
import com.zhenghaikj.shop.kt.bean.HistoryBean
import com.zhenghaikj.shop.kt.bean.Product
import com.zhenghaikj.shop.kt.mvp.contract.GetHistoryVisiteContract
import com.zhenghaikj.shop.kt.mvp.presenter.GetHistoryVisitePresenter
import com.zhenghaikj.shop.kt.ui.adapter.HistroyAdapter
import kotlinx.android.synthetic.main.kt_activity_histroy.*

/**
Data:2019/8/14
Time:15:45
author:ying
 **/
class HistoryActivityKt :ktBaseMvpActivity<GetHistoryVisiteContract.View,GetHistoryVisitePresenter>(),GetHistoryVisiteContract.View {

    lateinit var spUtils: SPUtils
    lateinit var userKey: String
    lateinit var histroyAdapter: HistroyAdapter
    lateinit var list: List<Product>



    override fun createPresenter(): GetHistoryVisitePresenter {
        return GetHistoryVisitePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.kt_activity_histroy
    }

    override fun initData() {
        spUtils= SPUtils.getInstance("token")
        userKey= spUtils.getString("UserKey")
        startNetwork()
        histroyAdapter= HistroyAdapter(R.layout.item_footprint2,list)
        rv_footprint.layoutManager = GridLayoutManager(this,3)
        rv_footprint.adapter=histroyAdapter
    }
    override fun startNetwork() {
        mPresenter?.GetHistoryVisite("10","1",userKey)
    }
    override fun GetHistoryVisite(historyBean: HistoryBean) {
       when(historyBean.Success){
           "true"->{
               list=historyBean.Product
               histroyAdapter.notifyDataSetChanged()
           }

       }
    }

}