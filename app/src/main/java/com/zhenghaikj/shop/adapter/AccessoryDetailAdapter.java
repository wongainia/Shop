package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.GAccessory;

import java.util.List;

public class AccessoryDetailAdapter extends BaseQuickAdapter<GAccessory,BaseViewHolder> {
    public AccessoryDetailAdapter(int layoutResId, List<GAccessory> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, GAccessory item) {
        helper.setText(R.id.tv_accessories_name,item.getFAccessoryName());
        helper.setText(R.id.tv_accessories_number,"¥"+item.getDiscountPrice()+"/"+item.getQuantity()+"个");
    }
}
