package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.AfterSalesTypeActivity;
import com.zhenghaikj.shop.adapter.SaleAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Product;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AfterSaleFragment extends BaseLazyFragment {
    @BindView(R.id.rv_sale)
    RecyclerView mRvSale;
    Unbinder unbinder;
    private ArrayList<Product> saleList=new ArrayList<>();

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

        SaleAdapter saleAdapter=new SaleAdapter(R.layout.item_sale,saleList);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
