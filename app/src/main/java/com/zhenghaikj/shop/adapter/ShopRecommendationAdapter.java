package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.entity.Product;

import java.util.List;

public class ShopRecommendationAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {
    public ShopRecommendationAdapter(int layoutResId, @Nullable List<Product> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {

    }
}
