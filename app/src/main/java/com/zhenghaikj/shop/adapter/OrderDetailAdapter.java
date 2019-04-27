package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

import androidx.annotation.Nullable;

public class OrderDetailAdapter extends BaseQuickAdapter<OrderDetail.OrderItemBean, BaseViewHolder> {
    public OrderDetailAdapter(int layoutResId, @Nullable List<OrderDetail.OrderItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetail.OrderItemBean item) {
        helper.setText(R.id.tv_goods_name,item.getProductName())
                .setText(R.id.tv_goods_type,item.getColor()+item.getSize()+item.getVersion())
                .setText(R.id.tv_good_money,item.getPrice())
                .setText(R.id.tv_goods_number,"×"+item.getCount());
        ImageView icon = helper.getView(R.id.iv_goods_picture);
        GlideUtil.loadImageViewLoding(mContext,item.getProductImage(),icon,R.drawable.image_loading,R.drawable.image_loading);
    }
}
