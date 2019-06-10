package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.List;

import androidx.annotation.Nullable;

public class SearchShopDetailAdapter extends BaseQuickAdapter<SearchResult.ProductBean,BaseViewHolder> {
    public SearchShopDetailAdapter(int layoutResId, @Nullable List<SearchResult.ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResult.ProductBean item) {
     helper.setText(R.id.tv_name,item.getProductName());
     helper.setText(R.id.tv_price,item.getSalePrice()+"");
        Glide.with(mContext).load(item.getImagePath())
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 5)))
                .into((ImageView) helper.getView(R.id.img_shop));
        helper.addOnClickListener(R.id.rl_shop);
    }
}
