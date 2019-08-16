package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.fragment.GoodDailyFragment;
import com.zhenghaikj.shop.adapter.GoodsdailyShopAdapter;
import com.zhenghaikj.shop.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodDailyShopActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private List<Fragment> fragments=new ArrayList<>();
    private List<String> titlesList=new ArrayList<>();
    private String[] title=new String[]{"精选","发现"};
    @Override
    protected int setLayoutId() {
        return R.layout.activity_good_daily_shop;
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
        for (int i=0;i<title.length;i++){
            titlesList.add(title[i]);
            fragments.add(new GoodDailyFragment());
        }
        for (int i=0;i<titlesList.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titlesList.get(i)));
        }

        GoodsdailyShopAdapter adapter=new GoodsdailyShopAdapter(getSupportFragmentManager(),fragments,titlesList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void initView() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    protected void setListener() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("每日好店");
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
}
