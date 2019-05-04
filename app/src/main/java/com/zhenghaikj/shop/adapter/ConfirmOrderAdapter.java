package com.zhenghaikj.shop.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.StoreBean;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ConfirmOrderAdapter extends BaseQuickAdapter<StoreBean, BaseViewHolder> {
    private List<StoreBean> list;
    public ConfirmOrderAdapter(int layoutResId, @Nullable List<StoreBean> data) {
        super(layoutResId, data);
        list=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreBean item) {

        helper.setText(R.id.tv_goods_number,"共"+item.getList().size()+"件商品");

        double Money = 0;
        for (int i = 0; i < item.getList().size(); i++) {
            double count = Double.parseDouble(item.getList().get(i).getCount());
            double price = Double.parseDouble(item.getList().get(i).getPrice());
            Money += count * price;
        }
        helper.setText(R.id.tv_subtotal,"¥"+String.format("%.2f", Money));


        helper.setText(R.id.tv_store_name,item.getShopName());
        ConfirmOrderListAdapter confirmOrderListAdapter=new ConfirmOrderListAdapter(R.layout.item_confirm_order_list,item.getList());
        RecyclerView recyclerView = helper.getView(R.id.rv_confirm_order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(confirmOrderListAdapter);



    }
}
