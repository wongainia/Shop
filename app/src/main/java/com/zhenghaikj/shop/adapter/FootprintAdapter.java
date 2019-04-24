package com.zhenghaikj.shop.adapter;

import android.graphics.Bitmap;
import android.util.Base64;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.HistoryVisite;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;

public class FootprintAdapter extends BaseQuickAdapter<HistoryVisite.ProductBean, BaseViewHolder> {

    private Bitmap bmp;

    public FootprintAdapter(int layoutResId, @Nullable List<HistoryVisite.ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryVisite.ProductBean item) {
        helper.setText(R.id.tv_time,item.getBrowseTime())
                .setText(R.id.tv_goods_name,item.getProductName())
                .setText(R.id.tv_goods_money,item.getProductPrice());
        byte[] decode;
        decode = Base64.decode(item.getImagePath(), Base64.DEFAULT);
        bmp = null;
        try {
            bmp = Glide.with(mContext).asBitmap().load(decode).submit().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Glide.with(mContext).asBitmap().load(decode).into(mIvAvatar);
        helper.setImageBitmap(R.id.iv_goods_picture, bmp);
    }
}
