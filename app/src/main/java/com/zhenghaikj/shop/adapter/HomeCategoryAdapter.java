package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.entity.Product;

import java.util.List;

import androidx.annotation.Nullable;

public class HomeCategoryAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {
    public HomeCategoryAdapter(int layoutResId, @Nullable List<Product> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {

    }
}
