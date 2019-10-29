package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Bill;

import java.util.List;

public class RecordingAdapter extends BaseQuickAdapter<Bill.DataBean, BaseViewHolder> {
    public RecordingAdapter(int layoutResId, @Nullable List<Bill.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bill.DataBean item) {
        helper.setText(R.id.tv_payment_method,item.getPayTypeName());
        StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        helper.setText(R.id.tv_time,time);
        if ("2".equals(item.getState())){
            helper.setText(R.id.tv_money,"-"+item.getPayMoney()+"");
        } else if ("1".equals(item.getState())) {
            helper.setText(R.id.tv_money,"+"+item.getPayMoney()+"");
        }else if ("5".equals(item.getState())){
            helper.setText(R.id.tv_money,"+"+item.getPayMoney()+"");
        }else if ("3".equals(item.getState())){
            helper.setText(R.id.tv_money,"-"+item.getPayMoney()+"");
        }else if ("6".equals(item.getState())){
            helper.setText(R.id.tv_money,"-"+item.getPayMoney()+"");
        }else if ("7".equals(item.getState())){
            helper.setText(R.id.tv_money,"-"+item.getPayMoney()+"");
        }
        else {
            return;
        }


    }
}
