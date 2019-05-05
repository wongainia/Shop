package com.zhenghaikj.shop.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.StoreBean;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CarAdapter extends BaseQuickAdapter<StoreBean, BaseViewHolder> {

    private RecyclerView recyclerView;
    private CarAdapter2 adapter2;
    private CheckBox checkBox;

    public CarAdapter(int layoutResId, @Nullable List<StoreBean> data) {
        super(layoutResId, data);
    }
    private allCheck mCallBack;

    public void setCallBack(allCheck callBack) {
        mCallBack = callBack;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreBean item) {
        adapter2 = new CarAdapter2(R.layout.item_shop_type,item.getList());
        helper.setText(R.id.tv_shop_name,item.getShopName());
        recyclerView = helper.getView(R.id.rv_shop_type);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter2);
        checkBox = helper.getView(R.id.cb_choose_all);
        checkBox.setChecked(item.isIscheck());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCallBack!=null){
                    mCallBack.OnCheckListener(isChecked,item.hashCode());
                }
            }
        });

    }

    public interface allCheck{
        //回调函数 将店铺的checkbox的点击变化事件进行回调
        public void OnCheckListener(boolean isSelected,int position);
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        public void OnItemCheckListener(boolean isSelected,int parentposition,int chaildposition);

        public void OnItemAddReduceListener(int value,int parentposition,int chaildposition);

        public void OnItemClickDetailListner(View view, int parentposition, int chaildposition);

        public void OnCheckCoupListner(int parentposition);
    }

}
