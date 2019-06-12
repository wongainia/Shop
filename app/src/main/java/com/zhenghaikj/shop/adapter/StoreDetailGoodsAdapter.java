package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.StoreDetailGoodsEntity;
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
        helper.setText(R.id.tv_goods_money,"¥"+item.getSalePrice());

        helper.addOnClickListener(R.id.ll_item);




    }
}
