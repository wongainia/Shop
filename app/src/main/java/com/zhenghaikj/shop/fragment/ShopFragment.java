package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.zhenghaikj.shop.adapter.ExchageAdapter;
import com.zhenghaikj.shop.adapter.HotSearchAdapter;
import com.zhenghaikj.shop.adapter.MyRecyclerViewAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.utils.GlideImageLoader;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopFragment extends BaseLazyFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.rv_hot_search)
    RecyclerView mRvHotSearch;
    Unbinder unbinder;
    @BindView(R.id.banner_shop)
    Banner mBannerShop;
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
    @BindView(R.id.tv_more_snapped_up)
    TextView mTvMoreSnappedUp;
    @BindView(R.id.iv_one)
    ImageView mIvOne;
    @BindView(R.id.iv_two)
    ImageView mIvTwo;
    @BindView(R.id.iv_three)
    ImageView mIvThree;
    @BindView(R.id.rv_shop)
    RecyclerView mRvShop;
    @BindView(R.id.rv_exchage)
    RecyclerView mRvExchage;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ShopFragment newInstance(String param1, String param2) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private ArrayList<String> hotsearchList = new ArrayList<>();
    private String[] hot = new String[]{"冰箱", "电视机", "衣服", "玩具"};
    private HotSearchAdapter hotSearchAdapter;

    private ArrayList<MenuItem> mMainMenus;
    private MenuAdapter mMainAdapter;
    private List<HomeResult.ProductBean> mDatas = new ArrayList<>();
    private List<Product> exchageList=new ArrayList<>();

    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private static final int START_ALPHA = 0;//scrollview滑动开始位置
    private static final int END_ALPHA = 255;//scrollview滑动结束位置

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

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shop;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        for (int i = 0; i < 4; i++) {
            hotsearchList.add(hot[i]);
        }

        for (int i = 0; i <10 ; i++) {
            exchageList.add(new Product());
        }

        ExchageAdapter exchageAdapter= new ExchageAdapter(R.layout.item_exchage,exchageList);
        LinearLayoutManager linearLayout=new LinearLayoutManager(mActivity);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mRvExchage.setLayoutManager(linearLayout);
        mRvExchage.setAdapter(exchageAdapter);

        hotSearchAdapter = new HotSearchAdapter(R.layout.item_hot_search, hotsearchList);
        mRvHotSearch.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mRvHotSearch.setAdapter(hotSearchAdapter);

        List<Integer> images = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            images.add(R.drawable.home_one);
        }
        mBannerShop.setImageLoader(new GlideImageLoader());
        mBannerShop.setImages(images);
        mBannerShop.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBannerShop.setIndicatorGravity(BannerConfig.CENTER);
        mBannerShop.start();

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
        mRvShop.setLayoutManager(staggeredGridLayoutManager);
        init();
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), mDatas);
        mRvShop.setItemAnimator(new DefaultItemAnimator());
        mRvShop.setAdapter(myRecyclerViewAdapter);

//        mSv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (scrollY > fadingHeight) {
//                    scrollY = fadingHeight;
//                } else if (scrollY < 0) {
//                    scrollY = 0;
//                } else {
//
//                }
//                mToolbar.getBackground().setAlpha(scrollY*(END_ALPHA - START_ALPHA) / fadingHeight+ START_ALPHA);
//            }
//        });
//        if (Global.appTheme != null) {
//            mToolbar.setBackgroundColor(Color.parseColor(Global.appTheme.getHome_top_color() != null ? Global.appTheme.getHome_top_color() : "#E82C00"));
//        } else {
//            mToolbar.setBackgroundColor(Color.parseColor("#E82C00"));
//        }
//        mToolbar.getBackground().setAlpha(START_ALPHA);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    //初始化数据
    protected void init() {
//        mDatas = new ArrayList<String>();
//        for (int i = 48; i < 57; i++) {
//            mDatas.add("¥" + (char) i);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

}
