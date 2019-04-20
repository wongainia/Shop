package com.zhenghaikj.shop.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchDetailAdapetr extends BaseQuickAdapter<SearchResult.ProductBean, BaseViewHolder> {
    public SearchDetailAdapetr(int layoutResId, @Nullable List<SearchResult.ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResult.ProductBean item) {
        helper.setText(R.id.tv_name,item.getProductName());
        helper.setText(R.id.tv_goods_money,"¥"+item.getSalePrice());
        helper.setText(R.id.tv_shop_name,item.getShopName());
        helper.setText(R.id.tv_number_of_payers,item.getSaleCount()+"人付款");
        GlideUtil.loadImageViewLoding(mContext,item.getImagePath(),helper.getView(R.id.iv_good_picture),R.drawable.image_loading,R.drawable.image_loading);
//        GlideUtil.loadImageViewLodingRadius(mContext,item.getImagePath(),helper.getView(R.id.iv_good_picture),R.drawable.image_loading,R.drawable.image_loading,10);
        helper.addOnClickListener(R.id.ll_good);
        helper.addOnClickListener(R.id.ll_into_the_store);
    }


}
