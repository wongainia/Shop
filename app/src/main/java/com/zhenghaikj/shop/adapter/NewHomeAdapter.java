package com.zhenghaikj.shop.adapter;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;
import com.zhenghaikj.shop.widget.RoundBackGroundColorSpan;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Data:2019/8/5
 * Time:10:55
 * author:ying
 **/
public class NewHomeAdapter extends BaseQuickAdapter<HomeResult.ProductBean, BaseViewHolder> {

    public NewHomeAdapter(int layoutResId, @Nullable List<HomeResult.ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeResult.ProductBean item) {

        if ("官方自营店".equals(item.getShopName())){
            SpannableString spannableString = new SpannableString("官方"+" "+item.getName());
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.WHITE);
            spannableString.setSpan(colorSpan, 0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            RoundBackGroundColorSpan span = new RoundBackGroundColorSpan(Color.parseColor("#ff0000"),Color.parseColor("#FFFFFF"), 10);
            spannableString.setSpan(span, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(35), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.tv_goods_name,spannableString);
        }else {
            helper.setText(R.id.tv_goods_name,item.getName());
        }




        SpannableString spannableString = new SpannableString(item.getSalePrice());
        if (item.getSalePrice().contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), item.getSalePrice().indexOf("."), item.getSalePrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        helper.setText(R.id.tv_goods_money,spannableString);
        //holder.tv_goods_money.setText(spannableString);
        String string = "¥"+item.getMarketPrice();
        SpannableString sp = new SpannableString(string);
        sp.setSpan(new StrikethroughSpan(), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        helper.setText(R.id.tv_payment,sp);
        helper.setText(R.id.tv_shop_name,item.getShopName());
        if (item.getProductAttributeInfos()!=null){
            if (item.getProductAttributeInfos().size()>=3){

                helper.setText(R.id.tv_comment,item.getProductAttributeInfos().get(0)+" | "+item.getProductAttributeInfos().get(1)+" | "+item.getProductAttributeInfos().get(2));
                //holder.tv_comment.setText(list.get(position).getProductAttributeInfos().get(0)+" | "+list.get(position).getProductAttributeInfos().get(1)+" | "+list.get(position).getProductAttributeInfos().get(2));
            }else if (item.getProductAttributeInfos().size()==1){

                helper.setText(R.id.tv_comment,item.getProductAttributeInfos().get(0));

              //  holder.tv_comment.setText(list.get(position).getProductAttributeInfos().get(0));
            }else if (item.getProductAttributeInfos().size()==2){

                helper.setText(R.id.tv_comment,item.getProductAttributeInfos().get(0)+" | "+item.getProductAttributeInfos().get(1));
              //  holder.tv_comment.setText(list.get(position).getProductAttributeInfos().get(0)+" | "+list.get(position).getProductAttributeInfos().get(1));
            }else if (item.getProductAttributeInfos().size()==0||item.getProductAttributeInfos()==null){
           helper.setVisible(R.id.tv_comment,false);
               // holder.tv_comment.setVisibility(View.INVISIBLE);
            }
        }else {
            helper.setVisible(R.id.tv_comment,false);
          //  holder.tv_comment.setVisibility(View.INVISIBLE);
        }


        if (item.getCashDepositsServer().isIsSevenDayNoReasonReturn()){
           helper.setText(R.id.tv_service,"七天无理由退换");
            //holder.tv_service.setText("七天无理由退换");
        }else {
            helper.setGone(R.id.tv_service,false);
           // holder.tv_service.setVisibility(View.GONE);
        }


        if (item.getCashDepositsServer().isIsTimelyShip()){
            helper.setText(R.id.tv_service_two,"急速发货");
            //holder.tv_service_two.setText("急速发货");
        }else {
            helper.setGone(R.id.tv_service_two,false);
           // holder.tv_service_two.setVisibility(View.GONE);
        }

        if (item.getCashDepositsServer().isIsCustomerSecurity()){
            helper.setText(R.id.tv_service_three,"消费者保证");
          //  holder.tv_service_three.setText("消费者保证");
        }else {
            helper.setGone(R.id.tv_service_three,false);
          //  holder.tv_service_three.setVisibility(View.GONE);
        }


        String timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // GlideUtil.loadImageViewLodingRadius(context,bean.getImageUrl()+"?"+timestamp,holder.iv_goods,R.drawable.image_loading,R.drawable.image_loading,10);

        //  Log.d("=====>",bean.getImageUrl()+"?"+timestamp);

        Glide.with(mContext).load(item.getImageUrl()+"?"+timestamp)
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 5)))
                .into((ImageView) helper.getView(R.id.iv_goods));


        helper.addOnClickListener(R.id.ll_gotoshop);
        helper.addOnClickListener(R.id.ll_goods);

    }
}
