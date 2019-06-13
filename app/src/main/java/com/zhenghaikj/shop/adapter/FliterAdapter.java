package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FliterAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private RecyclerView recyclerView;
    private FliterItemAdapter fliterItemAdapter;
    private List<String> list=new ArrayList<>();
    public FliterAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        list.add("苹果");
        list.add("三星");
        list.add("华为");
        list.add("小米");
        list.add("vivo");
        list.add("oppo");
        helper.setText(R.id.tv_type,item);
        recyclerView=helper.getView(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        fliterItemAdapter=new FliterItemAdapter(R.layout.item_fliter_choose,list);
        recyclerView.setAdapter(fliterItemAdapter);


    }
}
