package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.FilterResult;

import java.util.List;

public class FliterBrandAdapter extends BaseQuickAdapter<FilterResult.BrandBean,BaseViewHolder> {
    public FliterBrandAdapter(int layoutResId, @Nullable List<FilterResult.BrandBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterResult.BrandBean item) {
        helper.setText(R.id.item_cb,item.getName());
        helper.addOnClickListener(R.id.item_cb);

    }
}
