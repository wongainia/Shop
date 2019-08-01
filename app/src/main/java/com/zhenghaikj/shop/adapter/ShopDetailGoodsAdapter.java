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
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.List;

import androidx.annotation.Nullable;

public class ShopDetailGoodsAdapter extends BaseQuickAdapter<StoreCommodityResult.ProductListBean,BaseViewHolder> {

    public ShopDetailGoodsAdapter(int layoutResId, @Nullable List<StoreCommodityResult.ProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreCommodityResult.ProductListBean item) {

        Glide.with(mContext).load(item.getRelativePath())
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 5)))
                .into((ImageView) helper.getView(R.id.iv_goods));

        helper.setText(R.id.tv_goods_name,item.getProductName());
        SpannableString spannableString = new SpannableString(item.getMinSalePrice());
        if (item.getMinSalePrice().contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), item.getMinSalePrice().indexOf("."), item.getMinSalePrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(new RelativeSizeSpan(0.5f), item.getMinSalePrice().indexOf("."), item.getMinSalePrice().length(), USIVE_EXCLUSIVE);
        }
        helper.setText(R.id.tv_goods_money,spannableString);

        helper.addOnClickListener(R.id.ll_item);




    }
}
