package com.zhenghaikj.shop.adapter;

import android.widget.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Order;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class OrderListAdapter extends BaseQuickAdapter<Order.OrdersBean, BaseViewHolder> {
    String name;
    private OrderListAdapter2 orderListAdapter2;

    public OrderListAdapter(int layoutResId, @Nullable List<Order.OrdersBean> data,String name) {
        super(layoutResId, data);
        this.name=name;
    }

    @Override
    protected void convert(BaseViewHolder helper, Order.OrdersBean item) {
        orderListAdapter2 = new OrderListAdapter2(R.layout.item_order_list,item.getItemInfo());
        helper.setText(R.id.tv_store_name,item.getShopname());
        RecyclerView rv=helper.getView(R.id.rv_order_list);
        rv.setAdapter(orderListAdapter2);
    }
}
