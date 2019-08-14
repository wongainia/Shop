package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.MyPagerAdapter;
import com.zhenghaikj.shop.adapter.RecordingAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.fragment.IntegralUseFragment;
import com.zhenghaikj.shop.fragment.RecordFragment;
import com.zhenghaikj.shop.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*转账、提现记录*/
public class RecordingActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.ll_toolbar)
    LinearLayout mLlToolbar;
    @BindView(R.id.tab_order_layout)
    TabLayout mTabOrderLayout;
    @BindView(R.id.vp_order)
    CustomViewPager mVpOrder;
    private List<Product> list = new ArrayList<>();
    private RecordingAdapter adapter;
    private String[] mTitleDataList = new String[]{
            "收支", "提现", "充值"
    };
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private Intent intent;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_recording;
    }

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
//        for (int i = 0; i < 10; i++) {
//            list.add(new Product());
//        }
//
//        adapter = new RecordingAdapter(R.layout.item_recording, list);
//        adapter.setEmptyView(getEmptyView());
        for (int i = 0; i < mTitleDataList.length; i++) {
            fragmentList.add(RecordFragment.newInstance(mTitleDataList[i], ""));
        }
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(mTitleDataList));
        mTabOrderLayout.setTabMode(TabLayout.MODE_FIXED);
        mVpOrder.setAdapter(myPagerAdapter);
        mTabOrderLayout.setupWithViewPager(mVpOrder);
        mVpOrder.setCurrentItem(0);
        mVpOrder.setOffscreenPageLimit(fragmentList.size());
    }

    @Override
    protected void initView() {
        mTvTitle.setText("支出、提现记录");
        mTvTitle.setVisibility(View.VISIBLE);

        String intent=getIntent().getStringExtra("intent");
        switch (intent){
            case "收支":
                mVpOrder.setCurrentItem(0);
                break;
            case "提现":
                mVpOrder.setCurrentItem(1);
                break;
            case "充值":
                mVpOrder.setCurrentItem(2);
                break;

        }
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
}
