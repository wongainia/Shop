package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.entity.CommodityBean;

import java.util.List;

import androidx.annotation.Nullable;

public class CarAdapter2 extends BaseQuickAdapter<CommodityBean, BaseViewHolder> {
    public CarAdapter2(int layoutResId, @Nullable List<CommodityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityBean item) {

    }
}
