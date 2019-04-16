package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Product;

import java.util.List;

public class SearchDetailAdapetr extends BaseQuickAdapter<Product, BaseViewHolder> {
    public SearchDetailAdapetr(int layoutResId, @Nullable List<Product> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        helper.addOnClickListener(R.id.ll_good);
    }


}
