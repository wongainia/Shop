package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.GetStoreSortResult;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class StoreSubSortAdapter extends BaseQuickAdapter<GetStoreSortResult.ShopCategoriesBean.SubCategoriesBean, BaseViewHolder> {



    public StoreSubSortAdapter(int layoutResId, @Nullable List<GetStoreSortResult.ShopCategoriesBean.SubCategoriesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetStoreSortResult.ShopCategoriesBean.SubCategoriesBean item) {

        helper.setText(R.id.tv_name,item.getName());
        helper.addOnClickListener(R.id.ll_subsort);

    }
}
