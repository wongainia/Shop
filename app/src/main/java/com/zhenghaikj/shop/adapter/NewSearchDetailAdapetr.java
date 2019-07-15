package com.zhenghaikj.shop.adapter;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.utils.GlideUtil;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;
import com.zhenghaikj.shop.widget.RoundBackGroundColorSpan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewSearchDetailAdapetr extends BaseQuickAdapter<SearchResult.ProductBean, BaseViewHolder> {

    public NewSearchDetailAdapetr(int layoutResId, @Nullable List<SearchResult.ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResult.ProductBean item) {
        if ("官方自营店".equals(item.getShopName())){
            SpannableString spannableString = new SpannableString("官方"+" "+item.getProductName());
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.WHITE);
            spannableString.setSpan(colorSpan, 0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            RoundBackGroundColorSpan span = new RoundBackGroundColorSpan(Color.parseColor("#ff0000"),Color.parseColor("#FFFFFF"), 10);
            spannableString.setSpan(span, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(35), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.tv_name,spannableString);
        }else {
            helper.setText(R.id.tv_name,item.getProductName());
        }

        helper.setText(R.id.tv_price,item.getSalePrice()+"");
        helper.setText(R.id.tv_salesnum,item.getSaleCount()+"人付款");
        helper.setText(R.id.tv_shop_name,item.getShopName());
        helper.setText(R.id.tv_place_of_delivery,item.getProductAddress());
        helper.addOnClickListener(R.id.ll_shop_name);
        String timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        Glide.with(mContext).load(item.getImagePath()+"?"+timestamp)
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 5)))
                .into((ImageView) helper.getView(R.id.img_shop));
        helper.addOnClickListener(R.id.rl_shop);
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
                helper.setGone(R.id.tv_comment,false);
//                helper.setGone(R.id.tv_comment_two,false);
//                helper.setGone(R.id.tv_comment_three,false);
            }
        }else {
            helper.setGone(R.id.tv_comment,false);
//            helper.setGone(R.id.tv_comment_two,false);
//            helper.setGone(R.id.tv_comment_three,false);
        }

        if (item.getCashDepositsServer().isIsSevenDayNoReasonReturn()){
            helper.setText(R.id.tv_service,"七天无理由退换");
        }else {
            helper.setGone(R.id.tv_service,false);
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
//        if (item.getProductAttributeInfos().size()>=4){
//            for (int i = 0; i <4; i++) {
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
//        rv_attributes.setLayoutManager(new GridLayoutManager(mContext,2));
//        rv_attributes.setAdapter(attributesAdapter);

    }

}
