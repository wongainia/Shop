package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.LimitBuyListResult;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

import androidx.annotation.Nullable;

public class LimitedTimeAdapter extends BaseQuickAdapter<LimitBuyListResult.ListBean, BaseViewHolder> {
    public LimitedTimeAdapter(int layoutResId, @Nullable List<LimitBuyListResult.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LimitBuyListResult.ListBean item) {
//        String string = "￥"+item.getMinPrice();
//        SpannableString sp = new SpannableString(string);
//
//        sp.setSpan(new StrikethroughSpan(), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        helper.setText(R.id.tv_money_old,sp);
        helper.setText(R.id.tv_money_now,item.getMinPrice()+"");
        helper.setText(R.id.tv_goods_name,item.getProductName());
        GlideUtil.loadImageViewLodingRadius(mContext,item.getProductImg(),helper.getView(R.id.iv_goods_picture),R.drawable.image_loading,R.drawable.image_loading,10);
        helper.addOnClickListener(R.id.tv_grab_immediately);
    }
}
