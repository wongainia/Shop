package com.zhenghaikj.shop.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.List;

import androidx.annotation.Nullable;

public class StoreDetailGoodsAdapter extends BaseQuickAdapter<StoreDetailResult.ProductsBean,BaseViewHolder> {

    public StoreDetailGoodsAdapter(int layoutResId, @Nullable List<StoreDetailResult.ProductsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreDetailResult.ProductsBean item) {

        Glide.with(mContext).load(item.getImageUrl())
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 5)))
                .into((ImageView) helper.getView(R.id.iv_goods));

        helper.setText(R.id.tv_goods_name,item.getName());
        SpannableString spannableString = new SpannableString(String.valueOf(item.getSalePrice()));
        if (String.valueOf(item.getSalePrice()).contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), String.valueOf(item.getSalePrice()).indexOf("."), String.valueOf(item.getSalePrice()).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        helper.setText(R.id.tv_goods_money,spannableString);

        helper.addOnClickListener(R.id.ll_item);




    }
}
