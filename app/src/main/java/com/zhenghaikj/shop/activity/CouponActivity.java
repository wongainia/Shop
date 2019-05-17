package com.zhenghaikj.shop.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.MyPagerAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.fragment.CouponFragment;
import com.zhenghaikj.shop.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.BindView;

public class CouponActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tab_coupon_layout)
    TabLayout mTabCouponLayout;
    @BindView(R.id.vp_coupon)
    CustomViewPager mVpCoupon;

    private String[] mTitleDataList = new String[]{
            "可用优惠券（0）", "不可用优惠券（0）"
    };
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_coupon;
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
//        for (int i = 0; i < 2; i++) {
            fragmentList.add(CouponFragment.newInstance("0", ""));
            fragmentList.add(CouponFragment.newInstance("1", ""));
//        }

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(mTitleDataList));
        mTabCouponLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabCouponLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mVpCoupon.setAdapter(myPagerAdapter);
        mTabCouponLayout.setupWithViewPager(mVpCoupon);
        mVpCoupon.setCurrentItem(0);
        mVpCoupon.setOffscreenPageLimit(fragmentList.size());

    }

    @Override
    protected void initView() {
        mTvTitle.setText("我的优惠券");
        mTvTitle.setVisibility(View.VISIBLE);
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
}