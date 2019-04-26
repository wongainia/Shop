package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.ShopColor;

import java.util.List;

import androidx.annotation.Nullable;

public class ChooseColorAdapter extends BaseQuickAdapter<ShopColor, BaseViewHolder> {
    public ChooseColorAdapter(int layoutResId, @Nullable List<ShopColor> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopColor item) {
    helper.setText(R.id.tv_color,item.getValue());
    helper.addOnClickListener(R.id.rl_choose);

    }
}
