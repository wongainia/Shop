package com.zhenghaikj.shop.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.ChooseColorAdapter;
import com.zhenghaikj.shop.adapter.ChooseSizeAdapter;
import com.zhenghaikj.shop.adapter.ShopRecommendationAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.DetailResult;
import com.zhenghaikj.shop.entity.GetGoodSKu;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.ShopColor;
import com.zhenghaikj.shop.entity.ShopSize;
import com.zhenghaikj.shop.mvp.contract.DetailContract;
import com.zhenghaikj.shop.mvp.model.DetailModel;
import com.zhenghaikj.shop.mvp.presenter.DetailPresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.utils.GlideUtil;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.widget.AdderView;
import com.zhenghaikj.shop.widget.AutoLineFeedLayoutManager;
import com.zhenghaikj.shop.widget.CircleImageView;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;
import com.zhenghaikj.shop.widget.IdeaScrollView;
import com.zhenghaikj.shop.widget.RoundImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class GoodsDetailActivity extends BaseActivity<DetailPresenter, DetailModel> implements View.OnClickListener, DetailContract.View {


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
    @BindView(R.id.tv_collection)
    TextView mTvcollection;

    @BindView(R.id.ll_collect)
    LinearLayout mLlcollect;
    @BindView(R.id.img_collect)
    ImageView mImgcollect;
    @BindView(R.id.tv_addcart)
    TextView mTvaddcart;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    private AdderView adderView;
    private int getinventory; //库存
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private String Userkey;
    private SPUtils spUtils = SPUtils.getInstance("token");
    private ChooseColorAdapter chooseColorAdapter;
    private ChooseSizeAdapter chooseSizeAdapter;
    private DetailResult result = new DetailResult();

    private List<GetGoodSKu.SkuArrayBean> skuArray = new ArrayList<>();
//    @BindView(R.id.number_indicater)
//    NumberIndicater mNumberIndicater;

    private ArrayList<Product> shopRecommendationList = new ArrayList<>();
    private boolean isStop = false;//线程是否停止
    private static int PAGER_TIOME = 5000;//间隔时间

    private boolean isNeedScrollTo = true;
    private float currentPercentage = 0;
    private PagerAdapter mAdapter;
    private String id;

    private String SkuId = "";
    private String skuId_color = "0";
    private String skuId_size = "0";
    private String color_name = "";
    private String size_name = "";
    private String count = "1"; //数量

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


    @Override
    protected int setLayoutId() {
        return R.layout.activity_goods_detail;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {

        /*添加到购物车*/
     /*   findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   `
                mPresenter.PostAddProductToCart("699_0_0_0","1",Userkey);
            }
        });*/


        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.transparent);
        mImmersionBar.fitsSystemWindows(false);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    @Override
    protected void initData() {
        Userkey = spUtils.getString("UserKey");
        for (int i = 0; i < 6; i++) {
            shopRecommendationList.add(new Product());
        }
        ShopRecommendationAdapter shopRecommendationAdapter = new ShopRecommendationAdapter(R.layout.item_shop_recommendation, shopRecommendationList);
        mRvShopRecommendation.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mRvShopRecommendation.setAdapter(shopRecommendationAdapter);

        ShopRecommendationAdapter shopRecommendationAdapter1 = new ShopRecommendationAdapter(R.layout.item_recommend, shopRecommendationList);
        mRvRecommend.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRvRecommend.setAdapter(shopRecommendationAdapter1);

        id = getIntent().getStringExtra("id");

        // Userkey="YVdzb1BrelMyRXA0YU4xNExrUnJJWUxCdjZkN2ZxbEU4am1SM0dTd2ZiazlWWS80T1VQdnJ3SVdYNlc0WkZSKw==";
        if (!"".equals(id) && id != null) {
            mPresenter.GetProductDetail(id, Userkey);
            mPresenter.GetSKUInfo(id);


        }
    }


    @Override
    protected void initView() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_chooseproperty, null);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        adderView = popupWindow_view.findViewById(R.id.adderview);


        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        mIdeaScrollView.setViewPager(mBannerGoods, getMeasureHeight(mHeaderParent) - rectangle.top);
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
        // mTvcollection.setOnClickListener(this);
        mLlcollect.setOnClickListener(this);
        mTvaddcart.setOnClickListener(this);

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
            case R.id.ll_collect:
                Log.d("=====>", String.valueOf(id));

                if (mImgcollect.isSelected()) {
                    mImgcollect.setSelected(false);
                    mPresenter.PostAddFavoriteProduct(id, Userkey);
                    mTvcollection.setText("未收藏");
                } else {

                    mImgcollect.setSelected(true);
                    mPresenter.PostAddFavoriteProduct(id, Userkey);
                    mTvcollection.setText("已收藏");
                }

                break;

            case R.id.tv_addcart:
                showPopupWindow();

                break;


        }
    }


    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow() {
        //   img_bankcancle = popupWindow_view.findViewById(R.id.img_bankcancle);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {


            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);

                ((TextView) popupWindow_view.findViewById(R.id.tv_rmb)).setText("");
                ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:");
                ((TextView) popupWindow_view.findViewById(R.id.tv_choose)).setText("已选：");
                SkuId = "";
                skuId_color = "0";
                skuId_size = "0";
                color_name = "";
                size_name = "";
                adderView.setValue(1);
            }
        });


        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);

        /*glide图片*/
            if (!result.getProduct().getImagePath().isEmpty()){
                Glide.with(mActivity)
                        .load(result.getProduct().getImagePath().get(0))
                        .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                        .into((ImageView) popupWindow_view.findViewById(R.id.img_shop));
            }



        /*用于显示没有颜色没尺寸时候的价格和库存*/
        if (result.getColor().isEmpty() && result.getSize().isEmpty()) {
            ((TextView) popupWindow_view.findViewById(R.id.tv_rmb)).setText(skuArray.get(0).getPrice());
            ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + skuArray.get(0).getStock() + "件");
            getinventory = skuArray.get(0).getStock();

            /*计算数量*/
            adderView.setMaxValue(getinventory);//默认没标签选择
            adderView.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
                @Override
                public void onValueChange(int value) {
                    count = String.valueOf(value);
                }
            });
        }


        if (!result.getColor().isEmpty()) {
            popupWindow_view.findViewById(R.id.ll_cloose_color).setVisibility(View.VISIBLE);
            RecyclerView rv_color = popupWindow_view.findViewById(R.id.rv_color);
            rv_color.setLayoutManager(new AutoLineFeedLayoutManager());
            chooseColorAdapter = new ChooseColorAdapter(R.layout.item_color, result.getColor(),mActivity);
            rv_color.setAdapter(chooseColorAdapter);
            ChooseColor(rv_color, result.getColor());
        }

        if (!result.getSize().isEmpty()) {
            popupWindow_view.findViewById(R.id.ll_cloose_size).setVisibility(View.VISIBLE);
            RecyclerView rv_size = popupWindow_view.findViewById(R.id.rv_size);
            rv_size.setLayoutManager(new AutoLineFeedLayoutManager());
            chooseSizeAdapter = new ChooseSizeAdapter(R.layout.item_size, result.getSize());
            rv_size.setAdapter(chooseSizeAdapter);
            ChooseSize(rv_size, result.getSize());
        }




        /*提交*/
        popupWindow_view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_submit:
                        /*有颜色没尺寸*/
                        if (!result.getColor().isEmpty() && result.getColor().isEmpty()) {
                            if (skuId_color.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择颜色", Toast.LENGTH_SHORT).show();
                            } else {
                                mPresenter.PostAddProductToCart(id + "_" + skuId_color + "_0_0", count, Userkey);
                            }
                        }
                        /*有尺寸没颜色*/
                        if (!result.getSize().isEmpty() && result.getColor().isEmpty()) {
                            if (skuId_size.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择尺寸", Toast.LENGTH_SHORT).show();
                            } else {
                                mPresenter.PostAddProductToCart(id + "_0" + "_" + skuId_size + "_0", count, Userkey);
                            }
                        }

                        /*颜色尺寸都有*/
                        if (!result.getSize().isEmpty() && !result.getColor().isEmpty()) {
                            if (skuId_size.equals("0") || skuId_color.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择尺寸和颜色", Toast.LENGTH_SHORT).show();
                            } else {
                                mPresenter.PostAddProductToCart(id + "_" + skuId_color + "_" + skuId_size + "_0", count, Userkey);
                            }


                        }

                        //没有标签直接提交
                        if (result.getColor().isEmpty() && result.getSize().isEmpty()) {
                            mPresenter.PostAddProductToCart(id + "_0_0_0", count, Userkey);
                            Log.d("=====>", id + "_0_0_0");
                            Log.d("=====>", count);

                        }

                        break;
                }
            }
        });





     /*   img_bankcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });*/
    }


    @Override
    public void GetProductDetail(DetailResult Result) {
        Log.d("========>", "进入详情");

        if (Result.getSuccess().equals("true")) {
            /*ImagePath顶部图片轮播*/
            ArrayList<String> images = new ArrayList<>();
            for (int i = 0; i < Result.getProduct().getImagePath().size(); i++) {
                images.add(Result.getProduct().getImagePath().get(i));
            }
            mBannerGoods.setImageLoader(new GlideImageLoader());
            mBannerGoods.setImages(images);
            mBannerGoods.setBannerStyle(BannerConfig.NUM_INDICATOR);
            mBannerGoods.setIndicatorGravity(BannerConfig.CENTER);
            mBannerGoods.start();
            result = Result;

            /*判断是否收藏*/
            if (Result.getProduct().isIsFavorite()) {

                mImgcollect.setSelected(true);
                mTvcollection.setText("已收藏");
            } else {
                mImgcollect.setSelected(false);
                mTvcollection.setText("未收藏");
            }

            /*显示价格暂时取范围*/
            mTvGoodMoney.setText(Result.getProduct().getMinSalePrice() + "~" + Result.getProduct().getMarketPrice());

            /*判断是否免运费*/

            if (Result.getFree().equals("免运费")) {
                mTvExpressDelivery.setText("快递：0.00");
            } else {
                mTvExpressDelivery.setText("快递：" + Result.getShop().getFreeFreight());
            }
            /*月销量暂未总销量*/
            mTvSalesVolume.setText("月销" + Result.getMaxSaleCount());
            //商品名称
            mTvGoodName.setText(Result.getProduct().getProductName());

            /*地址*/
            mTvAddress.setText(Result.getProductAddress());



              /*服务
            "CashDepositsServer":{
            "IsSevenDayNoReasonReturn":false,   七天无理由
            "IsTimelyShip":false,           及时发货
            "IsCustomerSecurity":true,   消费者保障
            "CanSelfTake":false
            },*/

            String server = "";

            if (Result.getCashDepositsServer().isIsSevenDayNoReasonReturn()) {
                server = "七天无理由";
            }
            if (Result.getCashDepositsServer().isIsTimelyShip()) {
                server = server + " " + "及时发货";
            }
            if (Result.getCashDepositsServer().isIsCustomerSecurity()) {
                server = server + " " + "消费者保证";
            }
            mTvService.setText(server);

            /*添加颜色item*/
            // mRvcolor.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.HORIZONTAL,false));
            String html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "\t<meta charset=\"utf-8\">\n" +
                    "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "\t\n" +
                    "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                    "\n" +
                    "\t<title>detail</title>\n" +
                    "\n" +
                    "\t<style>body{border:0;padding:0;margin:0;}img{border:0;display:block;vertical-align: middle;padding:0;margin:0;}p{border:0;padding:0;margin:0;}div{border:0;padding:0;margin:0;}</style>\n" +
                    "</head>"
                    + "<body>"
                    + result.getProduct().getProductDescription() + "</body>" + "</html>";

            mWebview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
            mWebview.getSettings().setJavaScriptEnabled(true);
            mWebview.setWebChromeClient(new WebChromeClient());
            GlideUtil.loadImageViewLoding(mActivity,result.getVShopLog(),mIvStorePicture,R.drawable.image_loading,R.drawable.image_loading);
            mTvStoreName.setText(result.getShop().getName());

            mTvSellerServiceScore.setText(result.getShop().getServiceMark()+"");
            mTvBabyDescriptionScore.setText(result.getShop().getProductMark()+"");
            mTvLogisticsServicesScore.setText(result.getShop().getPackMark()+"");
        }
    }


    /*选择颜色*/
    private void ChooseColor(RecyclerView rv_color, List<ShopColor> list) {
        chooseColorAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.rl_choose:
                        for (int i = 0; i < list.size(); i++) {
                            adapter.getViewByPosition(rv_color, i, R.id.rl_choose).setSelected(false);
                        }
                        if (adapter.getViewByPosition(rv_color, position, R.id.rl_choose).isSelected()) {

                            adapter.getViewByPosition(rv_color, position, R.id.rl_choose).setSelected(false);
                        } else {
                            adapter.getViewByPosition(rv_color, position, R.id.rl_choose).setSelected(true);

                            Glide.with(mActivity)
                                    .load(result.getColor().get(position).getImg())
                                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                                    .into((ImageView) popupWindow_view.findViewById(R.id.img_shop));


                            skuId_color = ((ShopColor) adapter.getItem(position)).getSkuId();
                            color_name = ((ShopColor) adapter.getItem(position)).getValue();

                            if (!size_name.equals("") || !color_name.equals("")) {
                                ((TextView) popupWindow_view.findViewById(R.id.tv_choose)).setText("已选：" + size_name + " " + color_name);

                            }
                            //库存
                            SkuId = id + "_" + skuId_color + "_" + skuId_size + "_" + "0";  //最后一项先默认为0
                            getinventory = getinventory(SkuId);//获取库存
                            ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + getinventory + "件");

                            /*计算数量*/
                            adderView.setMaxValue(getinventory);//默认没标签选择
                            adderView.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
                                @Override
                                public void onValueChange(int value) {
                                    count = String.valueOf(value);
                                }
                            });


                            //价格
                            String price = getPrice(SkuId);
                            ((TextView) popupWindow_view.findViewById(R.id.tv_rmb)).setText(price);


                        }

                        break;
                }
            }
        });

    }

    /*选择尺寸*/
    private void ChooseSize(RecyclerView rv_size, List<ShopSize> list) {

        chooseSizeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.rl_choose:
                        for (int i = 0; i < list.size(); i++) {
                            adapter.getViewByPosition(rv_size, i, R.id.rl_choose).setSelected(false);
                        }
                        if (adapter.getViewByPosition(rv_size, position, R.id.rl_choose).isSelected()) {
                            adapter.getViewByPosition(rv_size, position, R.id.rl_choose).setSelected(false);
                        } else {
                            adapter.getViewByPosition(rv_size, position, R.id.rl_choose).setSelected(true);
                            skuId_size = ((ShopSize) adapter.getItem(position)).getSkuId();
                            size_name = ((ShopSize) adapter.getItem(position)).getValue();

                            if (!size_name.equals("") || !color_name.equals("")) {
                                ((TextView) popupWindow_view.findViewById(R.id.tv_choose)).setText("已选：" + size_name + " " + color_name);
                            }

                            SkuId = id + "_" + skuId_color + "_" + skuId_size + "_" + "0";  //最后一项先默认为0
                            //库存
                            getinventory = getinventory(SkuId);//获取库存
                            ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + getinventory + "件");

                            /*计算数量*/
                            adderView.setMaxValue(getinventory);//默认没标签选择
                            adderView.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
                                @Override
                                public void onValueChange(int value) {
                                    count = String.valueOf(value);
                                }
                            });


                            //价格
                            String price = getPrice(SkuId);
                            ((TextView) popupWindow_view.findViewById(R.id.tv_rmb)).setText(price);

                        }

                        break;
                }
            }
        });

    }


    /*获取商品库存*/
    public int getinventory(String skuId) {
        int inventory = 0;
        // Log.d("====>skuId", skuId);
        // Log.d("====>size", String.valueOf(skuArray.size()));
        // Log.d("====>getSKUId", String.valueOf(skuArray.get(0).getSKUId()));
        for (int i = 0; i < skuArray.size(); i++) {
            if (skuId.equals(skuArray.get(i).getSkuId())) {
                inventory = skuArray.get(i).getStock();
                Log.d("inventory======>", String.valueOf(inventory));
                return inventory;
            }

        }
        return inventory;
    }

    /*获取产品价格*/
    public String getPrice(String skuId) {
        String price = "";
        for (int i = 0; i < skuArray.size(); i++) {
            if (skuId.equals(skuArray.get(i).getSkuId())) {
                price = skuArray.get(i).getPrice();
                return price;
            }
        }
        return price;
    }


    /*添加到购物车*/
    @Override
    public void PostAddProductToCart(AddtoCartResult Result) {
        if (Result.getSuccess().equals("true")) {
            Toast.makeText(this, "已添加至购物车", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post("cart");
            mPopupWindow.dismiss();
        }

    }

    @Override
    public void GetSKUInfo(GetGoodSKu Result) {
        if (Result.getSuccess().equals("true")) {
            // skuArray.addAll(Result.getSkuArray());
            skuArray.addAll(Result.getSkuArray());


        }
    }

    /*收藏*/
    @Override
    public void PostAddFavoriteProduct(CollectResult Result) {
        Log.d("========>", "进入收藏");
    }

}
