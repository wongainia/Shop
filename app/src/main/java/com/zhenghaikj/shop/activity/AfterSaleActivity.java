package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.AfterSaleAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AfterSaleActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_after_sale)
    RecyclerView mRvAfterSale;
    @BindView(R.id.srl_after_sale)
    SwipeRefreshLayout mSrlAfterSale;

    private ArrayList<Product> afterSaleList=new ArrayList<>();
    private AfterSaleAdapter afterSaleAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_after_sale;
    }
    /**
     * 初始化沉浸式
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        for (int i=0;i<10;i++){
            afterSaleList.add(new Product());
        }
        afterSaleAdapter = new AfterSaleAdapter(R.layout.item_after_sale,afterSaleList);
        mRvAfterSale.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAfterSale.setAdapter(afterSaleAdapter);
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("退换/售后");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
        }
    }
}
