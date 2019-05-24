package com.zhenghaikj.shop.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vondear.rxui.view.roundprogressbar.RxRoundProgressBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.LimitBuyListResult;
import com.zhenghaikj.shop.utils.GlideUtil;
import com.zhenghaikj.shop.widget.SaleProgressView;

import java.util.List;

import androidx.annotation.Nullable;

public class LimitedTimeAdapter extends BaseQuickAdapter<LimitBuyListResult.ListBean, BaseViewHolder> {
    public LimitedTimeAdapter(int layoutResId, @Nullable List<LimitBuyListResult.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LimitBuyListResult.ListBean item) {
        String string = "￥"+item.getMarketPrice();
        SpannableString sp = new SpannableString(string);
//
        sp.setSpan(new StrikethroughSpan(), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.tv_money_old,sp);
        helper.setText(R.id.tv_money_now,item.getMinPrice()+"");
        helper.setText(R.id.tv_goods_name,item.getProductName());
        GlideUtil.loadImageViewLodingRadius(mContext,item.getProductImg(),helper.getView(R.id.iv_goods_picture),R.drawable.image_loading,R.drawable.image_loading,10);
        helper.addOnClickListener(R.id.tv_grab_immediately);
        RxRoundProgressBar pb=helper.getView(R.id.rx_round_pd4);
        pb.setProgress(30);
        pb.setMax(100);
        SaleProgressView spv=helper.getView(R.id.spv);
        spv.setTotalAndCurrentCount(100,item.getSaleCount());
    }
}
