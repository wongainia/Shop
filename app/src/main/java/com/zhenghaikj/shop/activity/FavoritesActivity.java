package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.MyPagerAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.fragment.CommodityFragment;
import com.zhenghaikj.shop.fragment.StoreFragment;
import com.zhenghaikj.shop.widget.CustomViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.view_pager_news)
    CustomViewPager mViewPagerNews;
    @BindView(R.id.tab_favorites_layout)
    TabLayout mTabFavoritesLayout;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.tv_management)
    TextView mTvManagement;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private CommodityFragment commodityFragment;
    private StoreFragment storeFragment;

//    private CommonNavigator commonNavigator;
//    private List<Fragment> mNewsList;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_favorites;
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
        title.add("宝贝");
        title.add("店铺");
        commodityFragment = new CommodityFragment();
        storeFragment = new StoreFragment();
        fragmentList.add(commodityFragment);
        fragmentList.add(storeFragment);



        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, title);
        myPagerAdapter.getCount();
        mTabFavoritesLayout.setTabMode(TabLayout.MODE_FIXED);

        mViewPagerNews.setAdapter(myPagerAdapter);
        mTabFavoritesLayout.setupWithViewPager(mViewPagerNews);
        mViewPagerNews.setCurrentItem(0);
    }

    @Override
    protected void initView() {
        mViewPagerNews.setScroll(false);
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
