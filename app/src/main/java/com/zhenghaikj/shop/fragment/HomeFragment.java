package com.zhenghaikj.shop.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.CallChageActivity;
import com.zhenghaikj.shop.activity.FoundGoodGoodsActivity;
import com.zhenghaikj.shop.activity.GoodDailyShopActivity;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.activity.LoginActivity;
import com.zhenghaikj.shop.activity.MainActivity;
import com.zhenghaikj.shop.activity.PanicBuyingActivity;
import com.zhenghaikj.shop.activity.SearchActivity;
import com.zhenghaikj.shop.adapter.ExchageAdapter;
import com.zhenghaikj.shop.adapter.LimitedTimeAdapter;
import com.zhenghaikj.shop.adapter.MyRecyclerViewAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Global;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.mvp.contract.HomeContract;
import com.zhenghaikj.shop.mvp.model.HomeModel;
import com.zhenghaikj.shop.mvp.presenter.HomePresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.widget.ObservableScrollView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.Unbinder;

public class HomeFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements View.OnClickListener, HomeContract.View {


    @BindView(R.id.banner_home)
    Banner mBannerHome;
    Unbinder unbinder;
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
    Toolbar mToolbar;
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

    private List<Product> panicBuyList = new ArrayList<>();
    private List<Product> exchageList = new ArrayList<>();
    private ArrayList<Product> limitedTimeList=new ArrayList<>();

    private ArrayList<MenuItem> mMainMenus;
    private int fadingHeight = 600; // 当ScrollView滑动到什么位置时渐变消失（根据需要进行调整）
    private static final int START_ALPHA = 0;//scrollview滑动开始位置
    private static final int END_ALPHA = 255;//scrollview滑动结束位置
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private List<HomeResult.ProductBean> mDatas = new ArrayList<>();
    private Integer[] icons = new Integer[]{
            R.mipmap.juxing, R.mipmap.juxing_one, R.mipmap.juxing_two, R.mipmap.juxing_three, R.mipmap.juxing_four, R.mipmap.juxing_five,
            R.mipmap.juxing_six, R.mipmap.juxing_seven, R.mipmap.juxing_eight, R.mipmap.juxing_nine
    };

    private String[] names = new String[]{
            "免费兑", "家电", "服装", "数码", "领券", "签到", "充值", "品牌", "发现", "分类"
    };

    private Integer[] picture = new Integer[]{
            R.mipmap.duihuan, R.mipmap.bingxiang, R.mipmap.close, R.mipmap.shuma, R.mipmap.quan, R.mipmap.qiandao, R.mipmap.chongzhi, R.mipmap.pinpai,
            R.mipmap.find, R.mipmap.leibie,
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
        mPresenter.Get(Integer.toString(pageNo), "10");

        for (int i = 0; i < 10; i++) {
            panicBuyList.add(new Product());
            exchageList.add(new Product());
        }
        ExchageAdapter exchageAdapter = new ExchageAdapter(R.layout.item_exchage, panicBuyList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPanicBuying.setLayoutManager(linearLayoutManager);
        mRvPanicBuying.setAdapter(exchageAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mActivity);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvExchange.setLayoutManager(linearLayoutManager1);
        mRvExchange.setAdapter(exchageAdapter);

        for (int i=0;i<10;i++){
            limitedTimeList.add(new Product());
        }
        LimitedTimeAdapter limitedTimeAdapter=new LimitedTimeAdapter(R.layout.item_panic_buying,limitedTimeList);
        mRvPanic.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPanic.setAdapter(limitedTimeAdapter);

        mMainMenus = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mMainMenus.add(new MenuItem(icons[i], names[i], picture[i]));
        }
        mMainAdapter = new MenuAdapter(R.layout.item_main_menu, mMainMenus);
        mRvMainMenu.setLayoutManager(new GridLayoutManager(mActivity, 5));
        mRvMainMenu.setAdapter(mMainAdapter);
        mMainAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(mActivity, LoginActivity.class));
//                        mPresenter.GetUser("菊花之战神","abcd1234","","","");
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    startActivity(new Intent(mActivity, CallChageActivity.class));
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
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRvHome.setLayoutManager(staggeredGridLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), mDatas);
        mRvHome.setItemAnimator(new DefaultItemAnimator());
        mRvHome.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
            intent.putExtra("id", mDatas.get(position).getId());
            startActivity(intent);
        });


        mSv.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > fadingHeight) {
                scrollY = fadingHeight;
            } else if (scrollY < 0) {
                scrollY = 0;
            } else {

            }
            mToolbar.getBackground().setAlpha(scrollY * (END_ALPHA - START_ALPHA) / fadingHeight + START_ALPHA);
        });
        if (Global.appTheme != null) {
            mToolbar.setBackgroundColor(Color.parseColor(Global.appTheme.getHome_top_color() != null ? Global.appTheme.getHome_top_color() : "#E82C00"));
        } else {
            mToolbar.setBackgroundColor(Color.parseColor("#E82C00"));
        }
        mToolbar.getBackground().setAlpha(START_ALPHA);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            pageNo = 1;
            mDatas.clear();
            mPresenter.Get(Integer.toString(pageNo), "10");
            refreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pageNo++;
            mPresenter.Get(Integer.toString(pageNo), "10");
            refreshLayout.finishLoadMore(1000);
        });
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                startActivity(new Intent(mActivity, SearchActivity.class));
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
            case R.id.ll_watermelon_coin_mall:

                MainActivity activity = (MainActivity) getActivity();
                activity.setCurrentItem(3);
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
            List<String> images = new ArrayList<>();
            for (int i = 0; i < Result.getSlide().size(); i++) {
                images.add(Result.getSlide().get(i).getImageUrl());
            }
            mBannerHome.setImageLoader(new GlideImageLoader());
            mBannerHome.setImages(images);
            mBannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
            mBannerHome.start();
        }
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
