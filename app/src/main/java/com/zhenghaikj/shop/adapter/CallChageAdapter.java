package com.zhenghaikj.shop.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.CallChage;
import com.zhenghaikj.shop.entity.Product;

import java.util.List;

public class CallChageAdapter extends BaseQuickAdapter<CallChage, BaseViewHolder> {
    public CallChageAdapter(int layoutResId, @Nullable List<CallChage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CallChage item) {
        helper.setText(R.id.tv_money,item.getMoney())
                .setText(R.id.tv_price,item.getPrice());
    }
}
