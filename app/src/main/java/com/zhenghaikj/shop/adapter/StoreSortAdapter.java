package com.zhenghaikj.shop.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.GetStoreSortResult;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StoreSortAdapter extends BaseQuickAdapter<GetStoreSortResult.ShopCategoriesBean, BaseViewHolder> {
    private StoreSubSortAdapter storeSubSortAdapter;
    private RecyclerView recyclerView;

    private OnItemSortClickListner onItemSortClickListner;

    public OnItemSortClickListner getOnItemSortClickListner() {
        return onItemSortClickListner;
    }

    public void setOnItemSortClickListner(OnItemSortClickListner onItemSortClickListner) {
        this.onItemSortClickListner = onItemSortClickListner;
    }



    public StoreSortAdapter(int layoutResId, @Nullable List<GetStoreSortResult.ShopCategoriesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetStoreSortResult.ShopCategoriesBean item) {
      helper.setText(R.id.sort_name,item.getName());
        recyclerView=helper.getView(R.id.recyclerview);
        storeSubSortAdapter=new StoreSubSortAdapter(R.layout.item_store_subsort,item.getSubCategories());
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(storeSubSortAdapter);
        storeSubSortAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_subsort:
                        onItemSortClickListner.OnItemSortClick(
                                ((GetStoreSortResult.ShopCategoriesBean.SubCategoriesBean)adapter.getItem(position)).getId(),
                           ((GetStoreSortResult.ShopCategoriesBean.SubCategoriesBean)adapter.getItem(position)).getName()
                        );
                        break;
                }
            }
        });
        helper.addOnClickListener(R.id.recyclerview);
    }
    public interface OnItemSortClickListner{
        void OnItemSortClick(int id,String name);
    }


}
