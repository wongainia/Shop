package com.zhenghaikj.shop.adapter;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.List;

import androidx.annotation.Nullable;

public class ReturnGoodsAdapter extends BaseQuickAdapter<Refund.DataBean, BaseViewHolder> {

    private Context mContext;
    public ReturnGoodsAdapter(int layoutResId, @Nullable List<Refund.DataBean> data,Context context) {
        super(layoutResId, data);
        this.mContext=context;

    }
    @Override
    protected void convert(BaseViewHolder helper, Refund.DataBean item) {
       helper.setText(R.id.tv_shop_name,item.getShopName());
       helper.setText(R.id.tv_state,item.getSellerAuditStatus());

        Glide.with(mContext)
                .load(item.getImg())
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 5)))
                .into((ImageView) helper.getView(R.id.img_shop));
        helper.setText(R.id.tv_return_money,"退款金额¥ "+item.getAmount());
        helper.setText(R.id.tv_shopname,item.getProductName());
        helper.addOnClickListener(R.id.ll_shop);

    }
}
