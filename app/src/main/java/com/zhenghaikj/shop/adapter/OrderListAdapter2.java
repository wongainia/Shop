package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

import androidx.annotation.Nullable;

public class OrderListAdapter2 extends BaseQuickAdapter<Order.itemInfoBean, BaseViewHolder> {

    private String color;
    private String size;
    private String version;

    public OrderListAdapter2(int layoutResId, @Nullable List<Order.itemInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order.itemInfoBean item) {
//        if (item.getColor().equals(null)){
//            color = "";
//        }else {
//            color=item.getColor();
//        }
//        if (item.getSize().equals(null)){
//            size = "";
//        }else {
//            size=";"+item.getSize();
//        }
//        if (item.getVersion().equals(null)){
//            version = "";
//        }else {
//            version=";"+item.getVersion();
//        }
        helper.setText(R.id.tv_goods_name,item.getProductName())
                .setText(R.id.tv_goods_type,item.getColor()+"  "+item.getSize()+"  "+item.getVersion())
                .setText(R.id.tv_good_money,item.getPrice())
                .setText(R.id.tv_goods_number,"×"+item.getCount());
        ImageView icon = helper.getView(R.id.iv_goods_picture);
        GlideUtil.loadImageViewLoding(mContext,item.getImage(),icon,R.drawable.image_loading,R.drawable.image_loading);
        helper.addOnClickListener(R.id.ll_goods);
    }
}
