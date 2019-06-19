package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.UserCouponListResult;

import java.util.List;

public class CouponAdapter extends BaseQuickAdapter<UserCouponListResult.CouponBean, BaseViewHolder> {
    public CouponAdapter(int layoutResId, @Nullable List<UserCouponListResult.CouponBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCouponListResult.CouponBean item) {

        helper.setText(R.id.tv_price,"¥"+item.getPrice());
        helper.setText(R.id.tv_date,"使用期限至:"+item.getEndTime());
        helper.setText(R.id.tv_shop,item.getShopName());

        if (item.getUseStatus()==1){
            helper.setBackgroundRes(R.id.cv,R.drawable.coupon_bg_shape_gray);
            helper.setText(R.id.tv_content,"已使用");
            helper.setGone(R.id.iv_go,false);
        }else if (TimeUtils.getNowMills()>TimeUtils.string2Millis(item.getEndTime())){
            helper.setBackgroundRes(R.id.cv,R.drawable.coupon_bg_shape_gray);
            helper.setText(R.id.tv_content,"已过期");
            helper.setGone(R.id.iv_go,false);
        }else{
            if (item.getPrice()<50){
                helper.setBackgroundRes(R.id.cv,R.drawable.coupon_bg_shape_blue);
            }else if (item.getPrice()>=100){
                helper.setBackgroundRes(R.id.cv,R.drawable.coupon_bg_shape_yellow);
            }else{
                helper.setBackgroundRes(R.id.cv,R.drawable.coupon_bg_shape_red);
            }
//            helper.setBackgroundRes(R.id.cv,R.drawable.coupon_bg_shape_yellow);
            helper.setText(R.id.tv_content,item.getCouponName());
            helper.setGone(R.id.iv_go,true);
        }
    }
}
