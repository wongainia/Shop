package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.ShopCoupResult;

import java.util.List;

import androidx.annotation.Nullable;

public class ShopCoupAdapter extends BaseQuickAdapter<ShopCoupResult.CouponBean, BaseViewHolder> {
    public ShopCoupAdapter(int layoutResId, @Nullable List<ShopCoupResult.CouponBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCoupResult.CouponBean item) {
     helper.setText(R.id.tv_coupname,item.getCouponName());
     helper.setText(R.id.tv_price,"¥"+item.getPrice());
     helper.setText(R.id.tv_time,"有效期:"+item.getStartTime()+"-"+item.getEndTime());
      helper.setText(R.id.tv_OrderAmount,"(满"+item.getOrderAmount()+"元可用)");

      helper.addOnClickListener(R.id.tv_getcoup);
    }
}
