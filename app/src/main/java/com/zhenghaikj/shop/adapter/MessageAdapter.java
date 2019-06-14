package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.Product;

import java.util.List;

import androidx.annotation.Nullable;

public class MessageAdapter extends BaseQuickAdapter<Announcement.RowsBean, BaseViewHolder> {
    public MessageAdapter(int layoutResId, @Nullable List<Announcement.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Announcement.RowsBean item) {
        helper.setText(R.id.tv_userName,item.getTitle())
                .setText(R.id.tv_time,item.getAddDate());
        helper.addOnClickListener(R.id.ll_message);
    }
}
