package com.zhenghaikj.shop.adapter;

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
        helper.setText(R.id.tv_goods_money,""+item.getMinSalePrice());

        helper.addOnClickListener(R.id.ll_item);




    }
}
