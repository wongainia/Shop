package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.DetailResult;

import java.util.List;

public class ServicesAdapter extends BaseQuickAdapter<DetailResult.CashDepositsServerNameBean, BaseViewHolder> {
    public ServicesAdapter(int layoutResId, @Nullable List<DetailResult.CashDepositsServerNameBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailResult.CashDepositsServerNameBean item) {
        helper.setText(R.id.tv_customersecurity,item.getCashServiceName());
    }
}
