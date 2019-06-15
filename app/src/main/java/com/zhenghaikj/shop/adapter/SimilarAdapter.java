package com.zhenghaikj.shop.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.SimilarProduct;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

public class SimilarAdapter extends BaseQuickAdapter<SimilarProduct, BaseViewHolder> {

    private Bitmap bmp;

    public SimilarAdapter(int layoutResId, @Nullable List<SimilarProduct> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SimilarProduct item) {
        helper.setText(R.id.tv_goods_name,item.getProductName())
                .setText(R.id.tv_good_money,"¥"+item.getMinSalePrice());
        ImageView icon = helper.getView(R.id.iv_goods_picture);
        GlideUtil.loadImageViewLoding(mContext,"http://mall.xigyu.com/"+item.getImagePath(),icon,R.drawable.image_loading,R.drawable.image_loading);
    }
}
