package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.ShopSize;

import java.util.List;

import androidx.annotation.Nullable;

public class ChooseSizeAdapter extends BaseQuickAdapter<ShopSize, BaseViewHolder> {
    public ChooseSizeAdapter(int layoutResId, @Nullable List<ShopSize> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopSize item) {

        helper.setText(R.id.tv_size,item.getValue());
        helper.addOnClickListener(R.id.rl_choose);
    }

}
