package com.zhenghaikj.shop.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.mvp.contract.LoginContract;
import com.zhenghaikj.shop.mvp.model.LoginModel;
import com.zhenghaikj.shop.mvp.presenter.LoginPresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.activity.CallChageActivity;
import com.zhenghaikj.shop.activity.FoundGoodGoodsActivity;
import com.zhenghaikj.shop.activity.GoodDailyShopActivity;
import com.zhenghaikj.shop.activity.MainActivity;
import com.zhenghaikj.shop.activity.PanicBuyingActivity;
import com.zhenghaikj.shop.activity.SearchActivity;
import com.zhenghaikj.shop.adapter.MyRecyclerViewAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Global;
import com.zhenghaikj.shop.widget.ObservableScrollView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseLazyFragment<LoginPresenter, LoginModel> implements View.OnClickListener, LoginContract.View {


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


    private ArrayList<MenuItem> mMainMenus;
    private int fadingHeight = 600; // 当ScrollView滑动到什么位置时渐变消失（根据需要进行调整）
    private static final int START_ALPHA = 0;//scrollview滑动开始位置
    private static final int END_ALPHA = 255;//scrollview滑动结束位置

    private ArrayList<String> mDatas;
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

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        List<Integer> images = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            images.add(R.drawable.home);
        }
        mBannerHome.setImageLoader(new GlideImageLoader());
        mBannerHome.setImages(images);
        mBannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
        mBannerHome.start();
        mMainMenus = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mMainMenus.add(new MenuItem(icons[i], names[i], picture[i]));
        }
        mMainAdapter = new MenuAdapter(R.layout.item_main_menu, mMainMenus);
        mRvMainMenu.setLayoutManager(new GridLayoutManager(mActivity, 5));
        mRvMainMenu.setAdapter(mMainAdapter);
        mMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        mPresenter.GetUser("17855837725","abcd1234",null,null,null);
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
            }
        });


        //声名为瀑布流的布局方式: 2列,垂直方向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRvHome.setLayoutManager(staggeredGridLayoutManager);
        init();
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), mDatas);
        mRvHome.setItemAnimator(new DefaultItemAnimator());
        mRvHome.setAdapter(myRecyclerViewAdapter);

        mSv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > fadingHeight) {
                    scrollY = fadingHeight;
                } else if (scrollY < 0) {
                    scrollY = 0;
                } else {

                }
                mToolbar.getBackground().setAlpha(scrollY * (END_ALPHA - START_ALPHA) / fadingHeight + START_ALPHA);
            }
        });
        if (Global.appTheme != null) {
            mToolbar.setBackgroundColor(Color.parseColor(Global.appTheme.getHome_top_color() != null ? Global.appTheme.getHome_top_color() : "#E82C00"));
        } else {
            mToolbar.setBackgroundColor(Color.parseColor("#E82C00"));
        }
        mToolbar.getBackground().setAlpha(START_ALPHA);

    }

    @Override
    protected void initView() {

    }


    //初始化数据
    protected void init() {
        mDatas = new ArrayList<String>();
        for (int i = 48; i < 57; i++) {
            mDatas.add("¥" + (char) i);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
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
    public void GetUser(LoginResult Result) {

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
