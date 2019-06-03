package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.ShopVersion;

import java.util.List;

import androidx.annotation.Nullable;

public class ChooseVersionAdapter extends BaseQuickAdapter<ShopVersion, BaseViewHolder> {
    public ChooseVersionAdapter(int layoutResId, @Nullable List<ShopVersion> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopVersion item) {
        helper.setText(R.id.tv_version,item.getValue());
        helper.addOnClickListener(R.id.rl_choose);
    }
}
