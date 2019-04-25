package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Order;

import java.util.List;

import androidx.annotation.Nullable;

public class OrderListAdapter2 extends BaseQuickAdapter<Order.itemInfoBean, BaseViewHolder> {
    public OrderListAdapter2(int layoutResId, @Nullable List<Order.itemInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order.itemInfoBean item) {
        helper.setText(R.id.tv_goods_name,item.getProductName())
                .setText(R.id.tv_goods_type,item.getColorAlias())
                .setText(R.id.tv_good_money,item.getPrice())
                .setText(R.id.tv_goods_number,item.getCount());
    }
}
