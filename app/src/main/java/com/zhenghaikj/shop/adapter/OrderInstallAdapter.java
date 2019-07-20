package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.List;

public class OrderInstallAdapter extends BaseQuickAdapter<OrderDetail.OrderItemBean, BaseViewHolder> {

    public OrderInstallAdapter(int layoutResId, @Nullable List<OrderDetail.OrderItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetail.OrderItemBean item) {
       Glide.with(mContext)
               .load(item.getProductImage())
               .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 5)))
               .into((ImageView) helper.getView(R.id.img_shop));
        helper.setText(R.id.tv_shop,item.getProductName());
        helper.setText(R.id.tv_count,"数量:"+item.getCount());
        if (!item.isInstall()){
           helper.setVisible(R.id.img_check,false);
        }else {
            helper.getView(R.id.img_check).setSelected(true);
        }
        helper.addOnClickListener(R.id.img_check);

    }
}
