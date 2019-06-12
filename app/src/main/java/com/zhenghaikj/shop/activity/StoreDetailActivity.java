package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.fragment.StoreDetailGoodsFragment;
import com.zhenghaikj.shop.fragment.StoreDetailHomeFragment;
import com.zhenghaikj.shop.fragment.StoreDetailSortFragment;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;
import com.zhenghaikj.shop.mvp.model.StoreDetailModel;
import com.zhenghaikj.shop.mvp.presenter.StoreDetailPresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreDetailActivity extends BaseActivity<StoreDetailPresenter, StoreDetailModel> implements StoreDetailContract.View, View.OnClickListener {

    @BindView(R.id.tab_receiving_layout)
    SlidingTabLayout mTabReceivingLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ll_toolbar)
    LinearLayout mLlToolbar;
    @BindView(R.id.img_shop)
    ImageView mImgShop;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_attention)
    TextView mTvAttention;
    @BindView(R.id.img_sort)
    ImageView mImgsort;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;

    @BindView(R.id.rl_shop)
    RelativeLayout rl_shop;
    @BindView(R.id.iv_attention)
    ImageView mIvAttention;
    @BindView(R.id.tv_share)
    TextView mTvShare;
    @BindView(R.id.iv_attention1)
    ImageView mIvAttention1;
    @BindView(R.id.ll_attention)
    LinearLayout mLlAttention;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.rv_coupon)
    RecyclerView mRvCoupon;
    @BindView(R.id.banner)
    Banner mBanner;

    private String Userkey;
    private String VShopId;
    private SPUtils spUtils = SPUtils.getInstance("token");
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    StoreDetailResult storeDetailResult = new StoreDetailResult();

    private int height;
    private final String[] mTitles = {
            "首页", "商品", "分类"
    };
    private MyPagerAdapter mAdapter;
    private String userName;

    //用来记录内层固定布局到屏幕顶部的距离


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
        return R.layout.activity_storedetail;
    }

    @Override
    protected void initData() {
        Userkey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");
        VShopId = getIntent().getStringExtra("VShopId");
        mPresenter.GetVShop(VShopId, Userkey);


    }

    @Override
    protected void initView() {

        mFragments.add(StoreDetailHomeFragment.newInstance("首页"));
        mFragments.add(StoreDetailGoodsFragment.newInstance("所有商品"));
        mFragments.add(StoreDetailSortFragment.newInstance("查看分类"));
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(mFragments.size());
        mTabReceivingLayout.setViewPager(mViewpager);


    }

    @Override
    protected void setListener() {
        mTvAttention.setOnClickListener(this);
        mImgsort.setOnClickListener(this);
        mLlAttention.setOnClickListener(this);


        appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                Log.d("======>appbar", String.valueOf(i));

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void GetVShop(StoreDetailResult result) {

        if ("True".equals(result.getSuccess())) {
            storeDetailResult = result;

            Glide.with(mActivity).load(result.getVShop().getLogo())
                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                    .into(mImgShop);
            mTvName.setText(result.getVShop().getName());
            if (result.getVShop().isFavorite()) {
                mTvAttention.setText("已关注");
                mIvAttention.setVisibility(View.GONE);
                mIvAttention1.setVisibility(View.VISIBLE);

            } else {
                mTvAttention.setText("关注");
                mIvAttention.setVisibility(View.VISIBLE);
                mIvAttention1.setVisibility(View.GONE);
            }

            if (!result.getSlideImgs().isEmpty()) {
                List<String> images = new ArrayList<>();
                for (int i = 0; i < result.getSlideImgs().size(); i++) {
                    images.add(result.getSlideImgs().get(i).getImageUrl());
                }
                mBanner.setImageLoader(new GlideImageLoader());
                mBanner.setImages(images);
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                mBanner.setIndicatorGravity(BannerConfig.CENTER);
                mBanner.setDelayTime(5000);
                mBanner.start();
            }
        }

    }

    @Override
    public void PostAddFavoriteShop(PostattentionResult result) {
        if (userName == null || "".equals(userName)) {
            startActivity(new Intent(mActivity, LoginActivity.class));
        } else {
            if (result.getMsg().equals("关注成功")) {
                mTvAttention.setText("已关注");
                mIvAttention.setVisibility(View.GONE);
                mIvAttention1.setVisibility(View.VISIBLE);
            } else {
                mTvAttention.setText("关注");
                mIvAttention.setVisibility(View.VISIBLE);
                mIvAttention1.setVisibility(View.GONE);
            }
        }


    }

    @Override
    public void GetVShopCategory(GetStoreSortResult result) {

    }

    @Override
    public void GetProductList(StoreCommodityResult result) {

    }

    @Override
    public void GetShopCouponList(ShopCoupResult Result) {

    }

    @Override
    public void PostAcceptCoupon(GetShopCoupResult Result) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_attention:
            case R.id.tv_attention:
                mPresenter.PostAddFavoriteShop(String.valueOf(storeDetailResult.getVShop().getShopId()), Userkey);
                break;
            case R.id.img_sort:
                mViewpager.setCurrentItem(2);
                break;
        }

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        height = rl_shop.getMeasuredHeight();
        Log.d("======>height", String.valueOf(rl_shop.getMeasuredHeight()));
    }
}
