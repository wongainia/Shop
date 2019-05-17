package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Category;

import java.util.List;

public class CategoryAdapter extends BaseQuickAdapter<Category,BaseViewHolder> {
    public CategoryAdapter(int layoutResId, List<Category> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getFCategoryName());
    }
}
