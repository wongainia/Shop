package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.CollectionShop;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

public class StoreAdapter extends BaseQuickAdapter<CollectionShop.DataBean, BaseViewHolder> {
    public StoreAdapter(int layoutResId, @Nullable List<CollectionShop.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionShop.DataBean item) {
        helper.addOnClickListener(R.id.ll_store);
        helper.setText(R.id.tv_store_name,item.getName());
        ImageView icon=helper.getView(R.id.iv_store_picture);
        GlideUtil.loadImageViewLoding(mContext,item.getLogo(),icon,R.drawable.image_loading,R.drawable.image_loading);
    }
}
