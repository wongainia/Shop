package com.zhenghaikj.shop.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.NewSearchDetailActivty;
import com.zhenghaikj.shop.entity.CategoryMall;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapterMall extends BaseMultiItemQuickAdapter<CategoryMall.CategoryBean, BaseViewHolder> {

    private TextView tv;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CategoryAdapterMall(List<CategoryMall.CategoryBean> data) {
        super(data);
        addItemType(0, R.layout.classify_item);
        addItemType(1, R.layout.second_category_item);
        addItemType(2, R.layout.category_goods_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryMall.CategoryBean item) {
        switch(helper.getItemViewType()){
            case 0:
                tv = helper.getView(R.id.tv);
                tv.setText(item.getName());
                if (item.isSelected()){
                    tv.setTextColor(Color.parseColor("#E82C00"));
                    tv.setBackgroundResource(R.drawable.layout_line_left);
                }else{
                    tv.setTextColor(Color.parseColor("#333333"));
                    tv.setBackgroundResource(R.drawable.layout_line);
                }
                break;
            case 1:
                tv = helper.getView(R.id.tv);
                RecyclerView rv=helper.getView(R.id.rv_third);
                tv.setText(item.getName());
                rv.setLayoutManager(new GridLayoutManager(mContext,3));
                for (int i = 0; i <item.getSubCategories().size() ; i++) {
                    item.getSubCategories().get(i).setItemType(2);
                }
                CategoryAdapterMall adapter=new CategoryAdapterMall(item.getSubCategories());
                rv.setAdapter(adapter);
                adapter.setOnItemClickListener((adapter1, view, position) -> {
                  /*  Intent intent=new Intent(mContext, SearchDetailActivity.class);
                    intent.putExtra("tag",item.getSubCategories().get(position));
                    mContext.startActivity(intent);*/
                    Intent intent=new Intent(mContext, NewSearchDetailActivty.class);
                    intent.putExtra("tag",item.getSubCategories().get(position));
                    mContext.startActivity(intent);

                });
                break;
            case 2:
                tv = helper.getView(R.id.name);
                ImageView icon = helper.getView(R.id.icon);
                tv.setText(item.getName());
                GlideUtil.loadImageViewLoding(mContext,item.getImage(),icon,R.drawable.potu,R.drawable.potu);
                break;
            default:
                break;
        }
    }

}
