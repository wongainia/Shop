package com.zhenghaikj.shop.adapter;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vondear.rxui.view.roundprogressbar.RxRoundProgressBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.LimitBuyListResult;
import com.zhenghaikj.shop.utils.GlideUtil;
import com.zhenghaikj.shop.widget.RoundBackGroundColorSpan;
import com.zhenghaikj.shop.widget.SaleProgressView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LimitedTimeAdapter extends BaseQuickAdapter<LimitBuyListResult.ListBean, BaseViewHolder> {


    public LimitedTimeAdapter(int layoutResId, @Nullable List<LimitBuyListResult.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LimitBuyListResult.ListBean item) {
        String string = "¥"+item.getMarketPrice();
        SpannableString sp = new SpannableString(string);
//
        sp.setSpan(new StrikethroughSpan(), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.tv_money_old,sp);
        helper.setText(R.id.tv_money_now,item.getMinPrice()+"");
        if ("官方自营店".equals(item.getShopName())){
            SpannableString spannableString = new SpannableString("官方"+" "+item.getProductName());
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.WHITE);
            spannableString.setSpan(colorSpan, 0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            RoundBackGroundColorSpan span = new RoundBackGroundColorSpan(Color.parseColor("#ff0000"),Color.parseColor("#FFFFFF"), 10);
            spannableString.setSpan(span, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(35), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.tv_goods_name,spannableString);
        }else {
            helper.setText(R.id.tv_goods_name,item.getProductName());
        }
//        helper.setText(R.id.tv_goods_name,item.getProductName());
        GlideUtil.loadImageViewLodingRadius(mContext,item.getProductImg(),helper.getView(R.id.iv_goods_picture),R.drawable.image_loading,R.drawable.image_loading,10);
        helper.addOnClickListener(R.id.tv_grab_immediately);
        RxRoundProgressBar pb=helper.getView(R.id.rx_round_pd4);
        pb.setProgress(30);
        pb.setMax(100);
        SaleProgressView spv=helper.getView(R.id.spv);
        spv.setTotalAndCurrentCount(item.getStock(),item.getSaleCount());

        if (item.getProductAttributeInfos()!=null){
            if (item.getProductAttributeInfos().size()>3){
                helper.setText(R.id.tv_comment,item.getProductAttributeInfos().get(0)+" | "+item.getProductAttributeInfos().get(1)+" | "+item.getProductAttributeInfos().get(2));
//                helper.setText(R.id.tv_comment_two,item.getProductAttributeInfos().get(1));
//                helper.setText(R.id.tv_comment_three,item.getProductAttributeInfos().get(2));

            }else if (item.getProductAttributeInfos().size()==1){
                helper.setText(R.id.tv_comment,item.getProductAttributeInfos().get(0));
//                helper.setGone(R.id.tv_comment_two,false);
//                helper.setGone(R.id.tv_comment_three,false);
            }else if (item.getProductAttributeInfos().size()==2){
                helper.setText(R.id.tv_comment,item.getProductAttributeInfos().get(0)+" | "+item.getProductAttributeInfos().get(1));
//                helper.setText(R.id.tv_comment_two,item.getProductAttributeInfos().get(1));
//                helper.setGone(R.id.tv_comment_three,false);
            }else if (item.getProductAttributeInfos().size()==0||item.getProductAttributeInfos()==null){
//                helper.setGone(R.id.tv_comment,false);
                helper.setVisible(R.id.tv_comment,false);
//                helper.setGone(R.id.tv_comment_two,false);
//                helper.setGone(R.id.tv_comment_three,false);
            }
        }else {
            helper.setVisible(R.id.tv_comment,false);
//            helper.setGone(R.id.tv_comment,false);
//            helper.setGone(R.id.tv_comment_two,false);
//            helper.setGone(R.id.tv_comment_three,false);
        }

        if (item.getCashDepositsServer().isIsSevenDayNoReasonReturn()){
            helper.setText(R.id.tv_service,"七天无理由退换");
        }else {
            helper.setGone(R.id.tv_service,false);
//            helper.setVisible(R.id.tv_service,false);
        }

        if (item.getCashDepositsServer().isIsTimelyShip()){
            helper.setText(R.id.tv_service_two,"急速发货");
        }else {
            helper.setGone(R.id.tv_service_two,false);

        }

        if (item.getCashDepositsServer().isIsCustomerSecurity()){
            helper.setText(R.id.tv_service_three,"消费者保证");
        }else {
            helper.setGone(R.id.tv_service_three,false);
        }

//        List<String> listBeans=new ArrayList<>();
//        if (item.getProductAttributeInfos().size()>3){
//            for (int i = 0; i <3 ; i++) {
//                listBeans.add(item.getProductAttributeInfos().get(i));
//            }
//        }else {
//            for (int i = 0; i <item.getProductAttributeInfos().size() ; i++) {
//                listBeans.add(item.getProductAttributeInfos().get(i));
//            }
//        }
//
//        RecyclerView rv_attributes=helper.getView(R.id.rv_attributes);
//        AttributesAdapter attributesAdapter=new AttributesAdapter(R.layout.item_attributes,listBeans);
//        rv_attributes.setLayoutManager(new GridLayoutManager(mContext,3));
//        rv_attributes.setAdapter(attributesAdapter);
    }
}
