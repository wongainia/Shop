package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.CollectionProduct;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

public class CommodityAdapter extends BaseQuickAdapter<CollectionProduct.DataBean, BaseViewHolder> {
    public CommodityAdapter(int layoutResId, @Nullable List<CollectionProduct.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionProduct.DataBean item) {
        helper.setText(R.id.tv_goods_name,item.getProductName())
                .setText(R.id.tv_goods_money,item.getSalePrice());
        ImageView icon=helper.getView(R.id.iv_goods_picture);
        GlideUtil.loadImageViewLoding(mContext,item.getImage(),icon,R.drawable.image_loading,R.drawable.image_loading);
        helper.addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.tv_find_similar)
                .addOnClickListener(R.id.ll_commodity);
        helper.addOnLongClickListener(R.id.ll_commodity);
    }
}
