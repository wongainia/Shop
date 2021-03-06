package com.zhenghaikj.shop.kt.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhenghaikj.shop.R
import com.zhenghaikj.shop.kt.bean.Product

/**
Data:2019/8/20
Time:14:33
author:ying
 **/
class HistroyAdapter(layoutResId: Int, data: List<Product>): BaseQuickAdapter<Product, BaseViewHolder>(layoutResId,data) {
    override fun convert(helper: BaseViewHolder?, item: Product?) {

        val stringBuilder = StringBuilder(item?.BrowseTime)
        val time = "" + stringBuilder.replace(10, 11, " ")
        helper?.setText(R.id.tv_time,time)
              ?.setText(R.id.tv_goods_name,item?.ProductName)
              ?.setText(R.id.tv_good_money,"¥"+item?.ProductPrice)

        helper?.getView<ImageView>(R.id.iv_goods_picture)?.let {
            Glide.with(mContext).load(item?.ImagePath).into(it)
        }

    }
}