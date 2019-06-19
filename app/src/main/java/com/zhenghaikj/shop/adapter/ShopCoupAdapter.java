package com.zhenghaikj.shop.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.ShopCoupResult;

import java.util.List;

public class ShopCoupAdapter extends BaseQuickAdapter<ShopCoupResult.CouponBean, BaseViewHolder> {
    public ShopCoupAdapter(int layoutResId, @Nullable List<ShopCoupResult.CouponBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, ShopCoupResult.CouponBean item) {
     helper.setText(R.id.tv_coupname,item.getCouponName());
     helper.setText(R.id.tv_price,"¥"+item.getPrice());
     helper.setText(R.id.tv_time,"有效期:"+item.getStartTime()+"-"+item.getEndTime());
      helper.setText(R.id.tv_OrderAmount,"(满"+item.getOrderAmount()+"元可用)");

      helper.addOnClickListener(R.id.tv_getcoup);
      if (item.getReceive()==1){
          ((CardView)helper.getView(R.id.cardView)).setCardBackgroundColor(Color.parseColor("#E82C00"));//EE7942
          helper.setText(R.id.tv_getcoup,"立即领取");
      }else if (item.getReceive()==3){
          ((CardView)helper.getView(R.id.cardView)).setCardBackgroundColor(Color.parseColor("#b2b2b2"));//808080
          helper.setText(R.id.tv_getcoup,"已领取");
      }
    }
}
