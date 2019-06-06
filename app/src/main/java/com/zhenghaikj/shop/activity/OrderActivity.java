package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.MyPagerAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.fragment.OrderFragment;
import com.zhenghaikj.shop.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class  OrderActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tab_order_layout)
    TabLayout mTabOrderLayout;
    @BindView(R.id.vp_order)
    CustomViewPager mVpOrder;

    private String[] mTitleDataList = new String[]{
            "全部","待付款","待发货", "待收货", "待评价"
    };
    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    @Override
    protected int setLayoutId() {
        return R.layout.activity_order;
    }

    /**
     * 初始化沉浸式
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        for (int i = 0; i <5; i++) {
            fragmentList.add(OrderFragment.newInstance(mTitleDataList[i], ""));
        }

//        PendingPaymentFragment pendingPaymentFragment=new PendingPaymentFragment();
//        ShipFragment shipFragment=new ShipFragment();
//        ReceiptFragment receiptFragment=new ReceiptFragment();
//        EvaluationFragment evaluationFragment=new EvaluationFragment();
//        fragmentList.add(orderFragment);
//        fragmentList.add(pendingPaymentFragment);
//        fragmentList.add(shipFragment);
//        fragmentList.add(receiptFragment);
//        fragmentList.add(evaluationFragment);

        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),fragmentList, Arrays.asList(mTitleDataList));
        mTabOrderLayout.setTabMode(TabLayout.MODE_FIXED);
        mVpOrder.setAdapter(myPagerAdapter);
        mTabOrderLayout.setupWithViewPager(mVpOrder);
        mVpOrder.setCurrentItem(0);
        mVpOrder.setOffscreenPageLimit(fragmentList.size());

    }

    @Override
    protected void initView() {
        mTvTitle.setText("我的订单");
        mTvTitle.setVisibility(View.VISIBLE);

        String intent=getIntent().getStringExtra("intent");
        switch (intent){
            case "全部":
                mVpOrder.setCurrentItem(0);
                break;
            case "待付款":
                mVpOrder.setCurrentItem(1);
                break;
            case "待发货":
                mVpOrder.setCurrentItem(2);
                break;
            case "待收货":
                mVpOrder.setCurrentItem(3);
                break;
            case "待评价":
                mVpOrder.setCurrentItem(4);
                break;

        }
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
