package com.zhenghaikj.shop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.ShopColor;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.List;

import androidx.annotation.Nullable;

public class ChooseColorAdapter extends BaseQuickAdapter<ShopColor, BaseViewHolder> {
    private Context mContext;
    public ChooseColorAdapter(int layoutResId, @Nullable List<ShopColor> data,Context context) {
        super(layoutResId, data);
        this.mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopColor item) {
    helper.setText(R.id.tv_color,item.getValue());
    helper.addOnClickListener(R.id.rl_choose);
    if ("".equals(item.getImg())){
        helper.getView(R.id.img_color).setVisibility(View.GONE);
    }else {
        Glide.with(mContext).load(item.getImg())
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 2)))
                .into((ImageView) helper.getView(R.id.img_color));
    }
        }
    }
