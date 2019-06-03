package com.zhenghaikj.shop.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.AddWorkOrderActivity;
import com.zhenghaikj.shop.activity.CallChageActivity;
import com.zhenghaikj.shop.activity.CheckinActivity;
import com.zhenghaikj.shop.activity.FoundGoodGoodsActivity;
import com.zhenghaikj.shop.activity.GoodDailyShopActivity;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.activity.LoginActivity;
import com.zhenghaikj.shop.activity.LotteryActivity;
import com.zhenghaikj.shop.activity.MainActivity;
import com.zhenghaikj.shop.activity.MessageActivity;
import com.zhenghaikj.shop.activity.PanicBuyingActivity;
import com.zhenghaikj.shop.activity.RegisterActivity;
import com.zhenghaikj.shop.activity.SearchDetailActivity;
import com.zhenghaikj.shop.activity.SearchPreDetailActivity;
import com.zhenghaikj.shop.adapter.ExchageAdapter;
import com.zhenghaikj.shop.adapter.HomeCategoryAdapter;
import com.zhenghaikj.shop.adapter.LimitedTimeAdapter;
import com.zhenghaikj.shop.adapter.MyRecyclerViewAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.HomeJsonResult;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.LimitBuyListResult;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.ShopResult;
import com.zhenghaikj.shop.mvp.contract.HomeContract;
import com.zhenghaikj.shop.mvp.model.HomeModel;
import com.zhenghaikj.shop.mvp.presenter.HomePresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.utils.ZXingUtils;
import com.zhenghaikj.shop.widget.ObservableScrollView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;

public class HomeFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements View.OnClickListener, HomeContract.View {


    @BindView(R.id.banner_home)
    Banner mBannerHome;
    @BindView(R.id.rv_main_menu)
    RecyclerView mRvMainMenu;
    @BindView(R.id.tv_message)
    TextSwitcher mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.tv_hour)
    TextView mTvHour;
    @BindView(R.id.tv_minute)
    TextView mTvMinute;
    @BindView(R.id.tv_second)
    TextView mTvSecond;
    @BindView(R.id.iv_panic_buying)
    ImageView mIvPanicBuying;
    @BindView(R.id.ll_panic_buying)
    LinearLayout mLlPanicBuying;
    @BindView(R.id.iv_found_good_goods)
    ImageView mIvFoundGoodGoods;
    @BindView(R.id.ll_found_good_goods)
    LinearLayout mLlFoundGoodGoods;
    @BindView(R.id.iv_coin_mall)
    ImageView mIvCoinMall;
    @BindView(R.id.iv_good_daily_shop)
    ImageView mIvGoodDailyShop;
    @BindView(R.id.rv_home)
    RecyclerView mRvHome;
    @BindView(R.id.sv)
    ObservableScrollView mSv;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.toolbar)
    LinearLayout mToolbar;
    @BindView(R.id.ll_good_daily_shop)
    LinearLayout mLlGoodDailyShop;
    @BindView(R.id.ll_watermelon_coin_mall)
    LinearLayout mLlWatermelonCoinMall;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.ll_scan_it)
    LinearLayout mLlScanIt;
    @BindView(R.id.ll_member_code)
    LinearLayout mLlMemberCode;
    @BindView(R.id.ll_mess)
    LinearLayout mLlMess;
    @BindView(R.id.rv_panic_buying)
    RecyclerView mRvPanicBuying;
    @BindView(R.id.rv_exchange)
    RecyclerView mRvExchange;
    @BindView(R.id.rv_panic)
    RecyclerView mRvPanic;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.iv_register)
    ImageView mIvRegister;
    @BindView(R.id.rv_category)
    RecyclerView mRvCategory;

    private List<ShopResult.GiftListNewBean> panicBuyList = new ArrayList<>();
    private List<Product> exchageList = new ArrayList<>();
    private List<LimitBuyListResult.ListBean> limitedTimeList = new ArrayList<>();
    private List<Product> categoryList = new ArrayList<>();

    private ArrayList<MenuItem> mMainMenus;
    private int fadingHeight = 600; // 当ScrollView滑动到什么位置时渐变消失（根据需要进行调整）
    private static final int START_ALPHA = 0;//scrollview滑动开始位置
    private static final int END_ALPHA = 255;//scrollview滑动结束位置
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LimitedTimeAdapter limitedTimeAdapter;
    private ExchageAdapter exchageAdapter;
    private List<HomeJsonResult.LModulesBean> modules;
    private List<HomeJsonResult.LModulesBean.ContentBean.DatasetBean> dataset = new ArrayList<>();
    private View under_review;
    private AlertDialog underReviewDialog;
    private Window window;
    private CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private SPUtils spUtils;
    private String userKey;
    private String userName;
    private Boolean isLogin;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private List<HomeResult.ProductBean> mDatas = new ArrayList<>();
//    private Integer[] icons = new Integer[]{
//            R.mipmap.juxing, R.mipmap.juxing_one, R.mipmap.juxing_two, R.mipmap.juxing_three, R.mipmap.juxing_four, R.mipmap.juxing_five,
//            R.mipmap.juxing_six, R.mipmap.juxing_seven, R.mipmap.juxing_eight, R.mipmap.juxing_nine
//    };

    private Integer[] icons = new Integer[]{
            R.drawable.gift, R.drawable.qiandao,
            R.drawable.charge, R.drawable.cmd, R.drawable.card
    };

//    private String[] names = new String[]{
//            "免费兑", "家电", "服装", "数码", "领券", "签到", "充值", "品牌", "发现", "分类"
//    };

    private String[] names = new String[]{
            "免费兑", "签到", "充值", "抽免单", "领券"
    };

//    private Integer[] picture = new Integer[]{
//            R.mipmap.duihuan, R.mipmap.bingxiang, R.mipmap.close, R.mipmap.shuma, R.mipmap.quan, R.mipmap.qiandao, R.mipmap.chongzhi, R.mipmap.pinpai,
//            R.mipmap.find, R.mipmap.leibie,
//    };

    private Integer[] picture = new Integer[]{
            R.mipmap.duihuan, R.mipmap.qiandao, R.mipmap.chongzhi,
            R.drawable.cmd, R.mipmap.quan
    };
    private MenuAdapter mMainAdapter;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private int pageNo = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");
        isLogin = spUtils.getBoolean("isLogin");
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new CustomShareListener(mActivity);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(mActivity).setDisplayList(
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
                            UMWeb web = new UMWeb("http://admin.xigyu.com/sign?phone=" + userName + "&type=8");
                            web.setTitle("西瓜鱼");
                            web.setDescription("注册送西瓜币了！！！！！");
                            web.setThumb(new UMImage(mActivity, R.drawable.iconn));
                            new ShareAction(mActivity).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }
                    }
                });

        mPresenter.Get();
        mPresenter.Get(Integer.toString(pageNo), "999");
        mPresenter.GetLismitBuyList(Integer.toString(pageNo), "999", "");

        for (int i=0;i<10;i++){
            categoryList.add(new Product());
        }
        HomeCategoryAdapter homeCategoryAdapter=new HomeCategoryAdapter(R.layout.item_category,categoryList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRvCategory.setLayoutManager(mLayoutManager);
        mRvCategory.setAdapter(homeCategoryAdapter);

        for (int i = 0; i < 10; i++) {
            panicBuyList.add(new ShopResult.GiftListNewBean());
            exchageList.add(new Product());
        }
        exchageAdapter = new ExchageAdapter(R.layout.item_exchage, panicBuyList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPanicBuying.setLayoutManager(linearLayoutManager);
        mRvPanicBuying.setAdapter(exchageAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mActivity);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvExchange.setLayoutManager(linearLayoutManager1);
        mRvExchange.setAdapter(exchageAdapter);

        limitedTimeAdapter = new LimitedTimeAdapter(R.layout.item_panic_buying, limitedTimeList);
        mRvPanic.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPanic.setAdapter(limitedTimeAdapter);
        limitedTimeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
                intent.putExtra("id", limitedTimeList.get(position).getProductId() + "");
                startActivity(intent);
            }
        });
        limitedTimeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(mActivity, PanicBuyingActivity.class);
////                intent.putExtra("id", limitedTimeList.get(position).getId());
//                startActivity(intent);

                Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
                intent.putExtra("id", limitedTimeList.get(position).getProductId() + "");
                startActivity(intent);
            }
        });

        mMainMenus = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mMainMenus.add(new MenuItem(icons[i], names[i], picture[i]));
        }

        mMainAdapter = new MenuAdapter(R.layout.item_main_menu, mMainMenus);
        mRvMainMenu.setLayoutManager(new GridLayoutManager(mActivity, 5));
        mRvMainMenu.setAdapter(mMainAdapter);
        mMainAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
//                    startActivity(new Intent(mActivity, LoginActivity.class));
//                        mPresenter.GetUser("菊花之战神","abcd1234","","","");
                    MainActivity activity = (MainActivity) getActivity();
                    activity.setCurrentItem(3);
                    break;
                case 1:
                    startActivity(new Intent(mActivity, CheckinActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(mActivity, CallChageActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(mActivity, LotteryActivity.class));
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:

                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }
        });

//声名为瀑布流的布局方式: 2列,垂直方向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRvHome.setLayoutManager(staggeredGridLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), mDatas);
        mRvHome.setItemAnimator(new DefaultItemAnimator());
        mRvHome.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
            intent.putExtra("id", mDatas.get(position).getId());
            startActivity(intent);
        });


//        mSv.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//            if (scrollY > fadingHeight) {
//                scrollY = fadingHeight;
//            } else if (scrollY < 0) {
//                scrollY = 0;
//            } else {
//
//            }
//            mToolbar.getBackground().setAlpha(scrollY * (END_ALPHA - START_ALPHA) / fadingHeight + START_ALPHA);
//        });
//        if (Global.appTheme != null) {
//            mToolbar.setBackgroundColor(Color.parseColor(Global.appTheme.getHome_top_color() != null ? Global.appTheme.getHome_top_color() : "#E82C00"));
//        } else {
//            mToolbar.setBackgroundColor(Color.parseColor("#E82C00"));
//        }
//        mToolbar.getBackground().setAlpha(START_ALPHA);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            pageNo = 1;
            mDatas.clear();
            mPresenter.Get();
            mPresenter.Get(Integer.toString(pageNo), "999");
            mPresenter.GetLismitBuyList(Integer.toString(pageNo), "999", "");
            refreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setEnableLoadMore(false);
//        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
//            pageNo++;
//            mPresenter.Get(Integer.toString(pageNo), "999");
//            refreshLayout.finishLoadMore(1000);
//        });
    }

    @Override
    protected void initView() {

    }


    //初始化数据
    protected void init() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void setListener() {
        mTvSearch.setOnClickListener(this);
        mLlPanicBuying.setOnClickListener(this);
        mLlFoundGoodGoods.setOnClickListener(this);
        mLlGoodDailyShop.setOnClickListener(this);
        mLlWatermelonCoinMall.setOnClickListener(this);
        mTvShop.setOnClickListener(this);
        mLlMemberCode.setOnClickListener(this);
        mIvRegister.setOnClickListener(this);
        mLlMess.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
               // startActivity(new Intent(mActivity, SearchDetailActivity.class));
                startActivity(new Intent(mActivity, SearchPreDetailActivity.class));
                break;
            case R.id.ll_panic_buying:
                startActivity(new Intent(mActivity, PanicBuyingActivity.class));
                break;
            case R.id.ll_found_good_goods:
                startActivity(new Intent(mActivity, FoundGoodGoodsActivity.class));
                break;
            case R.id.ll_good_daily_shop:
                startActivity(new Intent(mActivity, GoodDailyShopActivity.class));
                break;
            case R.id.tv_shop:
            case R.id.ll_watermelon_coin_mall:
                MainActivity activity = (MainActivity) getActivity();
                activity.setCurrentItem(3);
                break;
            case R.id.ll_member_code:
                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                Button btn_share_one = under_review.findViewById(R.id.btn_share_one);
                ImageView iv_code_one = under_review.findViewById(R.id.iv_code_one);
                Button btn_go_to_the_mall = under_review.findViewById(R.id.btn_go_to_the_mall);
                Bitmap bitmap = ZXingUtils.createQRImage("http://admin.xigyu.com/sign?phone=" + userName + "&type=7", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.iconn));
                iv_code_one.setImageBitmap(bitmap);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        underReviewDialog.dismiss();
                        mShareAction.open();
                    }
                });

                btn_go_to_the_mall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        openShopApp("com.zhenghaikj.shop");
                        underReviewDialog.dismiss();
                    }
                });

                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
                        .create();
                underReviewDialog.show();
                window = underReviewDialog.getWindow();
//                window.setContentView(under_review);
                WindowManager.LayoutParams lp = window.getAttributes();
                window.setAttributes(lp);
//                window.setDimAmount(0.1f);
                window.setBackgroundDrawable(new ColorDrawable());
                break;
            case R.id.iv_register:
                if ("".equals(userName)) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                } else {
                    startActivity(new Intent(mActivity, AddWorkOrderActivity.class));
                }

                break;
            case R.id.ll_mess:
                startActivity(new Intent(mActivity, MessageActivity.class));
                break;

        }
    }

    @Override
    public void Get(HomeResult Result) {
        if (Result.getSuccess()) {
            if (Result.getProduct().size() == 0) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mDatas.addAll(Result.getProduct());
                myRecyclerViewAdapter.setList(mDatas);
            }
            /*List<String> images = new ArrayList<>();
            for (int i = 0; i < Result.getSlide().size(); i++) {
                images.add(Result.getSlide().get(i).getImageUrl());
            }
            mBannerHome.setImageLoader(new GlideImageLoader());
            mBannerHome.setImages(images);
            mBannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
            mBannerHome.start();*/
        }
    }

    @Override
    public void Get(HomeJsonResult Result) {
        modules = Result.getLModules();
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getType() == 9) {
                dataset = modules.get(i).getContent().getDataset();
            }
        }
        List<String> images = new ArrayList<>();
        for (int i = 0; i < dataset.size(); i++) {
            images.add(dataset.get(i).getPic());
        }
        mBannerHome.setImageLoader(new GlideImageLoader());
        mBannerHome.setImages(images);
        mBannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
        mBannerHome.start();
    }

    @Override
    public void GetLismitBuyList(LimitBuyListResult Result) {
        limitedTimeList = Result.getList();
        limitedTimeAdapter.setNewData(limitedTimeList);
    }

    public class MenuItem {
        Integer icon;
        String name;
        Integer picture;

        public MenuItem(Integer icon, String name, Integer picture) {
            this.icon = icon;
            this.name = name;
            this.picture = picture;
        }

        public Integer getIcon() {
            return icon;
        }

        public void setIcon(Integer icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPicture() {
            return picture;
        }

        public void setPicture(Integer picture) {
            this.picture = picture;
        }
    }

    public class MenuAdapter extends BaseQuickAdapter<MenuItem, BaseViewHolder> {
        public MenuAdapter(int layoutResId, List<MenuItem> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MenuItem item) {
            // 加载网络图片
            Glide.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv_home));
            Glide.with(mContext).load(item.getPicture()).into((ImageView) helper.getView(R.id.iv_goods));
            helper.setText(R.id.tv_home, item.getName());
        }
    }

    public static class CustomShareListener implements UMShareListener {
        private Context mContext;

        public CustomShareListener(Context context) {
            mContext = context;
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mContext, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }


}
