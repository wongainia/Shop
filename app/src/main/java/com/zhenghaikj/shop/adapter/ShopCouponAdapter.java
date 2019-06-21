package com.zhenghaikj.shop.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.ShopCoupResult;

import java.util.List;

public class ShopCouponAdapter extends BaseQuickAdapter<ShopCoupResult.CouponBean, BaseViewHolder> {
    public ShopCouponAdapter(int layoutResId, @Nullable List<ShopCoupResult.CouponBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, ShopCoupResult.CouponBean item) {

        helper.setText(R.id.tv_price,"¥"+item.getPrice());
        helper.setText(R.id.tv_date,"至:"+item.getEndTime());
        helper.setText(R.id.tv_content,item.getCouponName());
        helper.addOnClickListener(R.id.tv_shop);
        if (item.getReceive()==1){
            helper.setBackgroundRes(R.id.cv,R.drawable.mine_bg_shape);
            helper.setBackgroundRes(R.id.tv_shop,R.drawable.red_round_shape);
            helper.setText(R.id.tv_shop,"领取");
        }else if (item.getReceive()==3){
            helper.setBackgroundRes(R.id.cv,R.drawable.coupon_bg_shape_gray);
            helper.setBackgroundRes(R.id.tv_shop,R.drawable.gray_round_shape);
            helper.setText(R.id.tv_shop,"已领取");
        }
    }
}
