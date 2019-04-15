package com.zhenghaikj.shop.adapter;

import android.support.annotation.Nullable;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.entity.Product;

import java.util.List;

public class AfterSaleAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {
    public AfterSaleAdapter(int layoutResId, @Nullable List<Product> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {

    }
}
