package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.AfterSalesTypeActivity;
import com.zhenghaikj.shop.adapter.SaleAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Product;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class AfterSaleFragment extends BaseLazyFragment {
    @BindView(R.id.rv_sale)
    RecyclerView mRvSale;

    private ArrayList<Product> saleList=new ArrayList<>();
    private SaleAdapter saleAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_after_sale;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        for (int i=0;i<2;i++){
            saleList.add(new Product());
        }

        saleAdapter = new SaleAdapter(R.layout.item_sale,saleList);
        saleAdapter.setEmptyView(getEmptyView());
        mRvSale.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSale.setAdapter(saleAdapter);
        saleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_apply_for_after_sale:
                        startActivity(new Intent(mActivity, AfterSalesTypeActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

}
