package com.zhenghaikj.shop.adapter;

import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Product;

import java.util.List;

public class LimitedTimeAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {
    public LimitedTimeAdapter(int layoutResId, @Nullable List<Product> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        String string = "￥158.00";
        SpannableString sp = new SpannableString(string);

        sp.setSpan(new StrikethroughSpan(), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.tv_money_old,sp);
    }
}
