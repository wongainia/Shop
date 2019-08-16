package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.entity.FilterResult;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class FliterAdapter extends BaseQuickAdapter<FilterResult,BaseViewHolder> {
    private RecyclerView recyclerView;
    private FliterItemAdapter fliterItemAdapter;
    public FliterAdapter(int layoutResId, @Nullable List<FilterResult> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterResult item) {




    }
}
