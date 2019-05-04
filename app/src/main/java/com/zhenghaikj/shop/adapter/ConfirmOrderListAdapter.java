package com.zhenghaikj.shop.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.CommodityBean;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.StoreBean;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.List;

import androidx.annotation.Nullable;

public class ConfirmOrderListAdapter extends BaseQuickAdapter<CommodityBean, BaseViewHolder> {
    public ConfirmOrderListAdapter(int layoutResId, @Nullable List<CommodityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityBean item) {
      helper.setText(R.id.tv_goods_name,item.getName());
        String type="";
        if (item.getSize()!=null||item.getColor()!=null){
            if (item.getColor()!=null){
                type=item.getColor();
                helper.setText(R.id.tv_goods_type,type);
            }
            if (item.getSize()!=null){
                type=type+" "+item.getSize();
                helper.setText(R.id.tv_goods_type,type);
            }
        }

        Glide.with(mContext).load(item.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 5)))
                .into((ImageView) helper.getView(R.id.iv_goods_picture));
        helper.setText(R.id.tv_goods_name,item.getName());
        helper.setText(R.id.tv_good_money,"¥"+item.getPrice());
        helper.setText(R.id.tv_goods_number,"×"+item.getCount());

    }
}
