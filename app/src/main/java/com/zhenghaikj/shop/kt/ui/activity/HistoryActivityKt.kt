package com.zhenghaikj.shop.kt.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SPUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.barlibrary.ImmersionBar
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zhenghaikj.shop.R
import com.zhenghaikj.shop.activity.GoodsDetailActivity
import com.zhenghaikj.shop.kt.base.ktBaseMvpActivity
import com.zhenghaikj.shop.kt.bean.HistoryBean
import com.zhenghaikj.shop.kt.bean.Product
import com.zhenghaikj.shop.kt.mvp.contract.GetHistoryVisiteContract
import com.zhenghaikj.shop.kt.mvp.presenter.GetHistoryVisitePresenter
import com.zhenghaikj.shop.kt.ui.adapter.HistroyAdapter
import kotlinx.android.synthetic.main.activity_toolbar.*
import kotlinx.android.synthetic.main.kt_activity_histroy.*

/**
Data:2019/8/14
Time:15:45
author:ying
 **/
class HistoryActivityKt :ktBaseMvpActivity<GetHistoryVisiteContract.View,GetHistoryVisitePresenter>(),GetHistoryVisiteContract.View,View.OnClickListener{
    lateinit var spUtils: SPUtils
    lateinit var userKey: String
    lateinit var histroyAdapter: HistroyAdapter
    lateinit var list: MutableList<Product>
    var pagaNo:Int=1

    override fun createPresenter(): GetHistoryVisitePresenter {
        return GetHistoryVisitePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.kt_activity_histroy
    }


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
         var mImmersionBar = ImmersionBar.with(this)
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(view)
        mImmersionBar.keyboardEnable(true)
        mImmersionBar.init()
    }

    override fun initData() {
        spUtils= SPUtils.getInstance("token")
        userKey= spUtils.getString("UserKey")
        tv_title.visibility=View.VISIBLE
        tv_title.text="足迹"
        list= mutableListOf()
        histroyAdapter= HistroyAdapter(R.layout.item_footprint2,list)
        rv_footprint.layoutManager = GridLayoutManager(mActivity,2)
        rv_footprint.adapter=histroyAdapter
        startNetwork()
    }

    override fun initListener() {
        icon_back.setOnClickListener(this)
        refreshLayout.setOnRefreshListener(object :OnRefreshListener{
            override fun onRefresh(refreshLayout: RefreshLayout) {
                pagaNo=1
                list.clear()
                startNetwork()
            }
        })

        refreshLayout.setOnLoadMoreListener(object :OnLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pagaNo++
                startNetwork()
            }
        })


        histroyAdapter.setOnItemClickListener { adapter, view, position ->
                    intent=Intent(mActivity, GoodsDetailActivity::class.java)
                    intent.putExtra("id",list.get(position).ProductId.toString())
                    startActivity(intent)
        }



    }

    override fun startNetwork() {
        mPresenter?.GetHistoryVisite("10",pagaNo.toString(),userKey)
    }
    override fun GetHistoryVisite(historyBean: HistoryBean) {
       when(historyBean.Success){
           "true"->{
               list.addAll(historyBean.Product.toMutableList())
               histroyAdapter.setNewData(list)
               if (pagaNo==1){ //下拉刷新
                   refreshLayout.setNoMoreData(false)
                   refreshLayout.finishRefresh()
               }else{
                   refreshLayout.finishLoadMore()
               }
           }

       }
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.icon_back-> this.finish()
        }
    }


}