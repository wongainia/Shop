package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.CoinRecord;

import java.util.List;

public class CoinRecordListAdapter extends BaseQuickAdapter<CoinRecord, BaseViewHolder> {

    public CoinRecordListAdapter(int layoutResId, @Nullable List<CoinRecord> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinRecord item) {
        helper.setText(R.id.tv_memo, item.getMemo())
                .setText(R.id.tv_time, item.getNowtime());
        if ("1".equals(item.getState())) {
            helper.setText(R.id.tv_price, "+"+item.getConCount());
        }else{
            helper.setText(R.id.tv_price, "-"+item.getConCount());
        }
    }
}
