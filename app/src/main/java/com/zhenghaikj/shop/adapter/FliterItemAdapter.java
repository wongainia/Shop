package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;

import java.util.List;

import androidx.annotation.Nullable;

public class FliterItemAdapter extends BaseQuickAdapter<String,BaseViewHolder>
{
    public FliterItemAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_cb,item);


    }
}
