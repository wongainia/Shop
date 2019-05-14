package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.RegionResult;

import java.util.List;

public class ProvinceAdapter2 extends BaseQuickAdapter<RegionResult,BaseViewHolder> {
    public ProvinceAdapter2(int layoutResId, List<RegionResult> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, RegionResult item) {
        // 加载网络图片
        helper.setText(R.id.tv_name,item.getName());
    }
}
