package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
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

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class AfterSaleFragment extends BaseLazyFragment {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "AfterSaleFragment";
    @BindView(R.id.rv_sale)
    RecyclerView mRvSale;

    private ArrayList<Product> saleList=new ArrayList<>();
    private SaleAdapter saleAdapter;
    private String mParam1;
    private Intent intent;

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }
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
    public void getData() {
        switch (mParam1) {
//            case "全部":
//                mPresenter.GetOrders("0", Integer.toString(pagaNo), "10", userKey);
//                break;
//            case "待付款":
//                mPresenter.GetOrders("1", Integer.toString(pagaNo), "10", userKey);
//                break;
//            case "待发货":
//                mPresenter.GetOrders("2", Integer.toString(pagaNo), "10", userKey);
//                break;
//            case "待收货":
//                mPresenter.GetOrders("3", Integer.toString(pagaNo), "10", userKey);
//                break;
//            case "待评价":
//                mPresenter.GetOrders("5", Integer.toString(pagaNo), "10", userKey);
//                break;
        }
    }
    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

}
