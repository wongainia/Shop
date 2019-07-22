package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.GetSerachListResult;

import java.util.List;

public class GetSerachListAdapter extends BaseQuickAdapter<GetSerachListResult.DataListBean, BaseViewHolder> {
    public GetSerachListAdapter(int layoutResId, @Nullable List<GetSerachListResult.DataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetSerachListResult.DataListBean item) {
        Glide.with(mContext)
                .asBitmap()
                .load("http://mall.xigyu.com/" + item.getImagePath()+"/1.png")
                .into((ImageView) helper.getView(R.id.iv_goods));

        helper.setText(R.id.tv_goods_money,item.getNeedIntegral()+"");

        helper.addOnClickListener(R.id.ll_item);
    }
}
