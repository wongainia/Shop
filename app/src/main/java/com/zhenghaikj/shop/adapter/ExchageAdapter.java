package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.ShopResult;

import java.util.List;

import androidx.annotation.Nullable;

public class ExchageAdapter extends BaseQuickAdapter<ShopResult.GiftListNewBean, BaseViewHolder> {
    public ExchageAdapter(int layoutResId, @Nullable List<ShopResult.GiftListNewBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopResult.GiftListNewBean item) {
        ImageView goods=helper.getView(R.id.iv_goods);
        Glide.with(mContext)
                .asBitmap()
                .load("http://mall.xigyu.com/" + item.getImagePath()+"/1.png")
                .into(goods);
//        GlideUtil.loadImageViewLoding(mContext,item.getImagePath(),goods,R.drawable.image_loading,R.drawable.image_loading);
        helper.setText(R.id.tv_goods_money,"¥"+item.getGiftValue());
        helper.setText(R.id.tv_goods_name,item.getGiftName());
        helper.setVisible(R.id.tv_payment,false);
    }
}
