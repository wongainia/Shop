package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.StoreDetailGoodsAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.fragment.StoreDetailGoodsFragment;
import com.zhenghaikj.shop.fragment.StoreDetailHomeFragment;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;
import com.zhenghaikj.shop.mvp.model.StoreDetailModel;
import com.zhenghaikj.shop.mvp.presenter.StoreDetailPresenter;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
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
    private String Userkey;
    private String VShopId;
    private SPUtils spUtils = SPUtils.getInstance("token");
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private final String[] mTitles = {
            "首页", "商品"
    };
    private MyPagerAdapter mAdapter;

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
        VShopId=getIntent().getStringExtra("VShopId");
        mPresenter.GetVShop(VShopId,Userkey);


    }

    @Override
    protected void initView() {

       mFragments.add(StoreDetailHomeFragment.newInstance("首页"));
       mFragments.add(StoreDetailGoodsFragment.newInstance("商品"));
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(mFragments.size());
        mTabReceivingLayout.setViewPager(mViewpager);
    }

    @Override
    protected void setListener() {
        mTvAttention.setOnClickListener(this);
        mImgsort.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void GetVShop(StoreDetailResult result) {

        if (result.getSuccess().equals("True")){

            Glide.with(mActivity).load(result.getVShop().getLogo())
                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                    .into(mImgShop);
            mTvName.setText(result.getVShop().getName());

        }

    }

    @Override
    public void PostAddFavoriteShop(PostattentionResult result) {

    }

    @Override
    public void GetVShopCategory(GetStoreSortResult result) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_attention:
                mPresenter.PostAddFavoriteShop(VShopId,Userkey);
                break;
            case R.id.img_sort:
            mPresenter.GetVShopCategory(VShopId);
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
}
