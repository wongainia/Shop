package com.zhenghaikj.shop.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.fragment.MineFragment;
import com.zhenghaikj.shop.fragment.StoreDetailGoodsFragment;
import com.zhenghaikj.shop.fragment.StoreDetailHomeFragment;
import com.zhenghaikj.shop.fragment.StoreDetailSortFragment;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;
import com.zhenghaikj.shop.mvp.model.StoreDetailModel;
import com.zhenghaikj.shop.mvp.presenter.StoreDetailPresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;
import com.zhenghaikj.shop.widget.StickyNavLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

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
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar1)
    Toolbar mToolbar1;
    @BindView(R.id.ll_share)
    LinearLayout mLlShare;
    @BindView(R.id.cdl)
    CoordinatorLayout mCdl;
    private String VShopId;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    StoreDetailResult storeDetailResult = new StoreDetailResult();

    private int height;
    private final String[] mTitles = {
            "首页", "所有商品", "查看分类"
    };
    private MyPagerAdapter mAdapter;
    private String shopid;
    private MineFragment.CustomShareListener mShareListener;
    private ShareAction mShareAction;

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
        VShopId = getIntent().getStringExtra("VShopId");
        mPresenter.GetVShop(VShopId, userKey);

        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new MineFragment.CustomShareListener(mActivity);

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
        mIvClose.setOnClickListener(this);
        mIconBack.setOnClickListener(this);
        mIconSearch.setOnClickListener(this);
        mLlShare.setOnClickListener(this);


        appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("======>appbar", String.valueOf(verticalOffset));
                if (verticalOffset == 0) {
                    mTvTitle.setVisibility(View.GONE);
//                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
//                    mTvTitle.setVisibility(View.VISIBLE);
                } else {
                    mTvTitle.setVisibility(View.VISIBLE);
                }
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
            shopid =result.getVShop().getId();
            Glide.with(mActivity).load(result.getVShop().getLogo())
                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                    .into(mImgShop);
            mTvName.setText(result.getVShop().getName());
            mTvTitle.setText(result.getVShop().getName());
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

                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
//                        intent.putExtra("id", (Serializable) images);
                        startActivity(intent);


                    }
                });
            }

            /*增加自定义按钮的分享面板*/
            mShareAction = new ShareAction((Activity) mActivity).setDisplayList(
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                    SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                    .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                    .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                    .setShareboardclickCallback(new ShareBoardlistener() {
                        @Override
                        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                            if (snsPlatform.mShowWord.equals("复制文本")) {
                                Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                            } else if (snsPlatform.mShowWord.equals("复制链接")) {
                                Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();

                            } else {
                                RxPermissions rxPermissions = new RxPermissions((Activity) mActivity);
                                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                        .subscribe(new Consumer<Boolean>() {
                                            @Override
                                            public void accept(Boolean aBoolean) throws Exception {
                                                if (aBoolean) {
                                                    // 获取全部权限成功

                                                    UMWeb web = new UMWeb(storeDetailResult.getVShop().getShareUrl());
                                                    web.setTitle("西瓜鱼");
                                                    web.setDescription(storeDetailResult.getVShop().getName());
                                                    web.setThumb(new UMImage(mActivity, R.drawable.shop));
                                                    new ShareAction((Activity) mActivity).withMedia(web)
                                                            .setPlatform(share_media)
                                                            .setCallback(mShareListener)
                                                            .share();
                                                } else {
                                                    // 获取全部权限失败
                                                    ToastUtils.showShort("权限获取失败");
                                                }
                                            }
                                        });

                            }
                        }
                    });
        }

    }

    @Override
    public void PostAddFavoriteShop(PostattentionResult result) {
        if (!isLogin) {
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
            case R.id.ll_share:
                mShareAction.open();
                break;
            case R.id.ll_attention:
            case R.id.tv_attention:
                mPresenter.PostAddFavoriteShop(String.valueOf(storeDetailResult.getVShop().getShopId()), userKey);
                break;
            case R.id.img_sort:
                mViewpager.setCurrentItem(2);
                break;
            case R.id.icon_search:
                Intent intent=new Intent(mActivity,SearchShopPreDetailActivity.class);
                intent.putExtra("shopid", shopid);
                startActivity(intent);
                startActivity(intent);
                break;
            case R.id.icon_back:
            case R.id.iv_close:
                finish();
                break;
        }

    }


    private class MyPagerAdapter extends FragmentPagerAdapter implements StickyNavLayout.OnLayoutScrollListener {

        private boolean isShowTop;

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

        @Override
        public void isTopShow(boolean isTopShow) {
            isShowTop = isTopShow;
            //获取当前显示的Fragment
            mViewpager.getCurrentItem();
        }
    }


}
