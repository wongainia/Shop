package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Distribution;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

public class DistributionAdapter extends BaseQuickAdapter<Distribution, BaseViewHolder> {
    public DistributionAdapter(int layoutResId, @Nullable List<Distribution> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Distribution item) {
        helper.setText(R.id.tv_goods_name,item.getProductName())
        .setText(R.id.tv_goods_money,item.getSellPrice()+"");
        ImageView icon=helper.getView(R.id.iv_goods);
        GlideUtil.loadImageViewLoding(mContext,item.getShareImageUrl(),icon,R.drawable.image_loading,R.drawable.image_loading);
        helper.addOnClickListener(R.id.tv_look_similar);
    }
}
