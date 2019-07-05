package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.MyPagerAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.GiftAds;
import com.zhenghaikj.shop.entity.Shop;
import com.zhenghaikj.shop.entity.ShopResult;
import com.zhenghaikj.shop.mvp.contract.ShopContract;
import com.zhenghaikj.shop.mvp.model.ShopModel;
import com.zhenghaikj.shop.mvp.presenter.ShopPresenter;
import com.zhenghaikj.shop.widget.CustomViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class ShopFragment2 extends BaseLazyFragment<ShopPresenter, ShopModel> implements ShopContract.View, View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tab_goods_layout)
    TabLayout mTabGoodsLayout;
    @BindView(R.id.vp_goods)
    CustomViewPager mVpGoods;
    public static final int MOVABLE_COUNT = 5;
    @BindView(R.id.appbarlayout)
    AppBarLayout mAppbarlayout;
    @BindView(R.id.cdl)
    CoordinatorLayout mCdl;

    private String[] mTitleDataList = new String[]{
            "全部", "衣服", "裤子", "鞋子", "帽子", "热水器", "水龙头", "饰品", "零食"
    };
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public static ShopFragment2 newInstance(String param1, String param2) {
        ShopFragment2 fragment = new ShopFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shop2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("更新登录信息".equals(name)) {
            getLoginMsg();
            mPresenter.GetList("18", "10", "1", userKey);
        }
    }

    @Override
    protected void initData() {
        for (int i = 0; i < mTitleDataList.length; i++) {
            fragmentList.add(CategoryFragment.newInstance(mTitleDataList[i], ""));
        }
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), fragmentList, Arrays.asList(mTitleDataList));
//        mTabGoodsLayout.setTabMode(mTitleDataList.length <= MOVABLE_COUNT ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);
        mTabGoodsLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mVpGoods.setAdapter(myPagerAdapter);
        mTabGoodsLayout.setupWithViewPager(mVpGoods);
        mVpGoods.setCurrentItem(0);
        mVpGoods.setOffscreenPageLimit(0);

    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {
        mAppbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("======>appbar", String.valueOf(verticalOffset));
//                if (verticalOffset == 0) {
//                    mTvTitle.setVisibility(View.GONE);
////                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
////                    mTvTitle.setVisibility(View.VISIBLE);
//                } else {
//                    mTvTitle.setVisibility(View.VISIBLE);
//                }
            }
        });
    }

    //初始化数据
    protected void init() {
//        mDatas = new ArrayList<String>();
//        for (int i = 48; i < 57; i++) {
//            mDatas.add("¥" + (char) i);
//        }
    }

    @Override
    public void index(Shop result) {

    }

    @Override
    public void IndexJson(ShopResult result) {

    }

    @Override
    public void GetSlideAds(List<GiftAds> result) {

    }

    @Override
    public void GetList(Announcement result) {


    }

    @Override
    public void onResume() {
        super.onResume();
//        mTvMessage.startAutoScroll();
    }

    //停止滚动
    @Override
    public void onPause() {
        super.onPause();
//        mTvMessage.stopAutoScroll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


}
