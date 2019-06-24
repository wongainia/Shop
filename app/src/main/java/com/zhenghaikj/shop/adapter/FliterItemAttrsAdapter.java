package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.FilterResult;

import java.util.List;

public class FliterItemAttrsAdapter extends BaseQuickAdapter<FilterResult.AttrsBean.AttrValuesBean,BaseViewHolder> {
    public FliterItemAttrsAdapter(int layoutResId, @Nullable List<FilterResult.AttrsBean.AttrValuesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterResult.AttrsBean.AttrValuesBean item) {
        helper.setText(R.id.item_cb,item.getName());
        helper.addOnClickListener(R.id.item_cb);

    }
}
