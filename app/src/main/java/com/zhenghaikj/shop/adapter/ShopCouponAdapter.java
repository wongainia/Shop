package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.entity.UserCouponListResult;

import java.util.List;

public class ShopCouponAdapter extends BaseQuickAdapter<ShopCoupResult.CouponBean, BaseViewHolder> {
    public ShopCouponAdapter(int layoutResId, @Nullable List<ShopCoupResult.CouponBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCoupResult.CouponBean item) {

        helper.setText(R.id.tv_price,"¥"+item.getPrice());
        helper.setText(R.id.tv_date,"至:"+item.getEndTime());
        helper.setText(R.id.tv_content,item.getCouponName());
        helper.addOnClickListener(R.id.tv_shop);
    }
}
