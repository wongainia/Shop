package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

public class AfterSaleAdapter extends BaseQuickAdapter<Refund.DataBean, BaseViewHolder> {

    public AfterSaleAdapter(int layoutResId, @Nullable List<Refund.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Refund.DataBean item) {
        helper.setText(R.id.tv_store_name,item.getShopName())
                .setText(R.id.tv_goods_name,item.getProductName())
                .setText(R.id.tv_goods_number,"×"+item.getSellerAuditStatusValue())
                .setText(R.id.tv_refund,item.getRefundMode());
        ImageView iv=helper.getView(R.id.iv_goods_picture);
        GlideUtil.loadImageViewLoding(mContext,item.getImg(),iv,R.drawable.image_loading,R.drawable.image_loading);
    }
}
