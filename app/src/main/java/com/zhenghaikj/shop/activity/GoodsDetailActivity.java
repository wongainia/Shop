package com.zhenghaikj.shop.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.Util.GlideImageLoader;
import com.zhenghaikj.shop.adapter.ShopRecommendationAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.widget.CircleImageView;
import com.zhenghaikj.shop.widget.IdeaScrollView;
import com.zhenghaikj.shop.widget.RoundImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.one)
    LinearLayout mOne;
    @BindView(R.id.tv_good_money)
    TextView mTvGoodMoney;
    @BindView(R.id.tv_good_name)
    TextView mTvGoodName;
    @BindView(R.id.ll_share)
    LinearLayout mLlShare;
    @BindView(R.id.tv_express_delivery)
    TextView mTvExpressDelivery;
    @BindView(R.id.tv_sales_volume)
    TextView mTvSalesVolume;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_service)
    TextView mTvService;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.tv_specification)
    TextView mTvSpecification;
    @BindView(R.id.ll_specification)
    LinearLayout mLlSpecification;
    @BindView(R.id.tv_parameter)
    TextView mTvParameter;
    @BindView(R.id.ll_parameter)
    LinearLayout mLlParameter;
    @BindView(R.id.tv_baby_evaluation)
    TextView mTvBabyEvaluation;
    @BindView(R.id.ll_evaluation)
    LinearLayout mLlEvaluation;
    @BindView(R.id.iv_picture)
    CircleImageView mIvPicture;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.iv_store_picture)
    RoundImageView mIvStorePicture;
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.tv_all_goods)
    TextView mTvAllGoods;
    @BindView(R.id.tv_go_shopping)
    TextView mTvGoShopping;
    @BindView(R.id.tv_baby_description_score)
    TextView mTvBabyDescriptionScore;
    @BindView(R.id.tv_baby_description)
    TextView mTvBabyDescription;
    @BindView(R.id.tv_seller_service_score)
    TextView mTvSellerServiceScore;
    @BindView(R.id.tv_seller_service)
    TextView mTvSellerService;
    @BindView(R.id.tv_logistics_services_score)
    TextView mTvLogisticsServicesScore;
    @BindView(R.id.tv_logistics_services)
    TextView mTvLogisticsServices;
    @BindView(R.id.ideaScrollView)
    IdeaScrollView mIdeaScrollView;
    @BindView(R.id.header)
    LinearLayout mHeader;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    //    @BindView(R.id.layer)
//    View mLayer;
    @BindView(R.id.headerParent)
    LinearLayout mHeaderParent;
    @BindView(R.id.two)
    LinearLayout mTwo;
    @BindView(R.id.three)
    LinearLayout mThree;
    @BindView(R.id.rv_recommend)
    RecyclerView mRvRecommend;
    @BindView(R.id.four)
    LinearLayout mFour;
    @BindView(R.id.ll_ask_everyone)
    LinearLayout mLlAskEveryone;
    @BindView(R.id.tv_question)
    TextView mTvQuestion;
    @BindView(R.id.tv_answer)
    TextView mTvAnswer;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_shop_recommendation)
    LinearLayout mLlShopRecommendation;
    @BindView(R.id.rv_shop_recommendation)
    RecyclerView mRvShopRecommendation;
    @BindView(R.id.iv_cart)
    ImageView mIvCart;
    @BindView(R.id.banner_goods)
    Banner mBannerGoods;



//    @BindView(R.id.number_indicater)
//    NumberIndicater mNumberIndicater;

    private ArrayList<Product> shopRecommendationList = new ArrayList<>();
    private boolean isStop = false;//线程是否停止
    private static int PAGER_TIOME = 5000;//间隔时间

    private boolean isNeedScrollTo = true;
    private float currentPercentage = 0;
    private RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(currentPercentage) : getRadioAlphaColor(currentPercentage));
                if (radioButton.isChecked() && isNeedScrollTo) {
                    mIdeaScrollView.setPosition(i);
                }
            }
        }
    };

    private PagerAdapter mAdapter;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_goods_detail;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.transparent);
        mImmersionBar.fitsSystemWindows(false);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    @Override
    protected void initData() {
         ArrayList<Integer> images = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            images.add(R.drawable.home);
        }
        mBannerGoods.setImageLoader(new GlideImageLoader());
        mBannerGoods.setImages(images);
        mBannerGoods.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBannerGoods.setIndicatorGravity(BannerConfig.CENTER);
        mBannerGoods.start();


        for (int i = 0; i < 6; i++) {
            shopRecommendationList.add(new Product());
        }
        ShopRecommendationAdapter shopRecommendationAdapter = new ShopRecommendationAdapter(R.layout.item_shop_recommendation, shopRecommendationList);
        mRvShopRecommendation.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mRvShopRecommendation.setAdapter(shopRecommendationAdapter);

        ShopRecommendationAdapter shopRecommendationAdapter1 = new ShopRecommendationAdapter(R.layout.item_recommend, shopRecommendationList);
        mRvRecommend.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRvRecommend.setAdapter(shopRecommendationAdapter1);
    }


    @Override
    protected void initView() {
        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        mIdeaScrollView.setViewPager(mBannerGoods, getMeasureHeight(mHeaderParent) - rectangle.top);
//        icon.setImageAlpha(0);
        mRadioGroup.setAlpha(0);
        mRadioGroup.check(mRadioGroup.getChildAt(0).getId());

        ArrayList<Integer> araryDistance = new ArrayList<>();

        araryDistance.add(0);
        araryDistance.add(getMeasureHeight(mOne) - getMeasureHeight(mHeaderParent));
        araryDistance.add(getMeasureHeight(mOne) + getMeasureHeight(mTwo) - getMeasureHeight(mHeaderParent));
        araryDistance.add(getMeasureHeight(mOne) + getMeasureHeight(mTwo) + getMeasureHeight(mThree) - getMeasureHeight(mHeaderParent));

        mIdeaScrollView.setArrayDistance(araryDistance);

        mIdeaScrollView.setOnScrollChangedColorListener(new IdeaScrollView.OnScrollChangedColorListener() {
            @Override
            public void onChanged(float percentage) {

                int color = getAlphaColor(percentage > 0.9f ? 1.0f : percentage);
                mHeader.setBackgroundDrawable(new ColorDrawable(color));
//                mRadioGroup.setBackgroundDrawable(new ColorDrawable(color));
//                icon.setImageAlpha((int) ((percentage>0.9f?1.0f:percentage)*255));
                mRadioGroup.setAlpha((percentage > 0.9f ? 1.0f : percentage) * 255);

                setRadioButtonTextColor(percentage);

            }

            @Override
            public void onChangedFirstColor(float percentage) {

            }

            @Override
            public void onChangedSecondColor(float percentage) {

            }
        });

        mIdeaScrollView.setOnSelectedIndicateChangedListener(new IdeaScrollView.OnSelectedIndicateChangedListener() {
            @Override
            public void onSelectedChanged(int position) {
                isNeedScrollTo = false;
                mRadioGroup.check(mRadioGroup.getChildAt(position).getId());
                isNeedScrollTo = true;
            }
        });

        mRadioGroup.setOnCheckedChangeListener(radioGroupListener);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void setRadioButtonTextColor(float percentage) {
        if (Math.abs(percentage - currentPercentage) >= 0.1f) {
            for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(percentage) : getRadioAlphaColor(percentage));
            }
            this.currentPercentage = percentage;
        }
    }

    public int getMeasureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    public int getAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0xff, 0x00, 0x00);
    }

    public int getLayerAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x09, 0xc1, 0xf4);
    }

    public int getRadioCheckedAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x44, 0x44, 0x44);
    }

    public int getRadioAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0xFF, 0xFF, 0xFF);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
