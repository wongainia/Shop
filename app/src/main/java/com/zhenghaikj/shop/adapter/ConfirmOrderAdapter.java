package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Product;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ConfirmOrderAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {
    private List<Product> list=new ArrayList<>();
    public ConfirmOrderAdapter(int layoutResId, @Nullable List<Product> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        for (int i = 0; i <2 ; i++) {
            list.add(new Product());
        }
        ConfirmOrderListAdapter adapter=new ConfirmOrderListAdapter(R.layout.item_confirm_order_list,list);
        RecyclerView rv=helper.getView(R.id.rv_confirm_order_list);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(adapter);
    }
}
