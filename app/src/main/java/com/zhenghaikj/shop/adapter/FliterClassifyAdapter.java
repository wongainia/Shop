package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.FilterResult;

import java.util.List;

public class FliterClassifyAdapter extends BaseQuickAdapter<FilterResult.CategoryBean.SubCategoryBeanX.SubCategoryBean,BaseViewHolder> {
    public FliterClassifyAdapter(int layoutResId, @Nullable List<FilterResult.CategoryBean.SubCategoryBeanX.SubCategoryBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, FilterResult.CategoryBean.SubCategoryBeanX.SubCategoryBean item) {
        helper.setText(R.id.item_cb,item.getName());
        helper.addOnClickListener(R.id.item_cb);
    }
}
