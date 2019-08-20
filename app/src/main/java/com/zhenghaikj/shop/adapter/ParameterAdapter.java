package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.DetailResult;

import java.util.List;

public class ParameterAdapter extends BaseQuickAdapter<DetailResult.ProductAttributeInfolistBean, BaseViewHolder> {
    public ParameterAdapter(int layoutResId, @Nullable List<DetailResult.ProductAttributeInfolistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailResult.ProductAttributeInfolistBean item) {
        helper.setText(R.id.tv_name,item.getKey())
                .setText(R.id.tv_customersecurity,item.getValue());
    }
}
