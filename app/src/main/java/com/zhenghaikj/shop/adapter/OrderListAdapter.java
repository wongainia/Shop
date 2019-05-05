package com.zhenghaikj.shop.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.OrderDetailActivity;
import com.zhenghaikj.shop.entity.Order;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

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
        helper.setText(R.id.tv_store_name,item.getShopname())
                .setText(R.id.tv_goods_number,"共"+item.getProductCount()+"件商品")
                .setText(R.id.tv_goods_price,"合计："+item.getOrderTotalAmount());
        helper.setText(R.id.tv_trading_status,item.getStatus());
        helper.addOnClickListener(R.id.tv_trading_status);
        helper.addOnClickListener(R.id.tv_delete_order);
        helper.addOnClickListener(R.id.tv_confirm_receipt);
        RecyclerView rv=helper.getView(R.id.rv_order_list);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(orderListAdapter2);
//        if (item.getOrderStatus()==0){
//            helper.setText(R.id.tv_trading_status,item.getStatus());
//        }

        if (item.getOrderStatus()==5){

//            helper.setVisible(R.id.tv_trading_status,true);
            helper.setVisible(R.id.ll_pending_payment,false);
            helper.setVisible(R.id.ll_pending_receipt,false);
            helper.setVisible(R.id.ll_all_orders,true);
            helper.setVisible(R.id.ll_to_be_delivered,false);
        }

        if (item.getOrderStatus()==1){
            helper.setVisible(R.id.ll_pending_payment,true);
            helper.setVisible(R.id.ll_pending_receipt,false);
            helper.setVisible(R.id.ll_all_orders,false);
            helper.setVisible(R.id.ll_to_be_delivered,false);
        }

        if (item.getOrderStatus()==2){
            helper.setVisible(R.id.ll_pending_payment,false);
            helper.setVisible(R.id.ll_pending_receipt,false);
            helper.setVisible(R.id.ll_all_orders,false);
            helper.setVisible(R.id.ll_to_be_delivered,true);
        }

        if (item.getOrderStatus()==3){
            helper.setVisible(R.id.ll_pending_payment,false);
            helper.setVisible(R.id.ll_pending_receipt,true);
            helper.setVisible(R.id.ll_all_orders,false);
            helper.setVisible(R.id.ll_to_be_delivered,false);
        }


        orderListAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_goods:
                        Intent intent=new Intent(mContext,OrderDetailActivity.class);
                        intent.putExtra("orderId",item.getId());
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
