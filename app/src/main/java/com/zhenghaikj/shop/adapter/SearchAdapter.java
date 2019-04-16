package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Search;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<Search, BaseViewHolder> {
    public SearchAdapter(int layoutResId, @Nullable List<Search> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Search item) {
        helper.setText(R.id.tv_title,item.getTitle());
    }
}
