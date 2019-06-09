package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.GiftOrder;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

public class GiftOrderListAdapter extends BaseQuickAdapter<GiftOrder.ModelsBean, BaseViewHolder> {

    public GiftOrderListAdapter(int layoutResId, @Nullable List<GiftOrder.ModelsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftOrder.ModelsBean item) {
        helper.setText(R.id.tv_order_number,item.getId())
         .setText(R.id.tv_goods_number,"共"+item.getItems().get(0).getQuantity()+"件礼品")
         .setText(R.id.tv_goods_price,"合计："+item.getTotalIntegral()+"西瓜币")
         .setText(R.id.tv_product_name,item.getItems().get(0).getGiftName())
         .setText(R.id.tv_coin,item.getItems().get(0).getSaleIntegral()+"西瓜币")
         .setText(R.id.tv_num,"x"+item.getItems().get(0).getQuantity());
        helper.setText(R.id.tv_trading_status,item.getShowOrderStatus());
        helper.addOnClickListener(R.id.tv_confirm_receipt);//确认收货
        GlideUtil.loadImageViewLoding(mContext,item.getItems().get(0).getDefaultImage(),helper.getView(R.id.iv_img),R.drawable.image_loading,R.drawable.image_loading);

        if (item.getOrderStatus()==3){//待收货
            helper.setGone(R.id.tv_confirm_receipt,true);
        }else{
            helper.setGone(R.id.tv_confirm_receipt,false);
        }
    }
}
