package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.GiftsDetailActivity;
import com.zhenghaikj.shop.adapter.ExchageAdapter;
import com.zhenghaikj.shop.adapter.ShopAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.GiftAds;
import com.zhenghaikj.shop.entity.Shop;
import com.zhenghaikj.shop.entity.ShopResult;
import com.zhenghaikj.shop.mvp.contract.ShopContract;
import com.zhenghaikj.shop.mvp.model.ShopModel;
import com.zhenghaikj.shop.mvp.presenter.ShopPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseLazyFragment<ShopPresenter, ShopModel> implements ShopContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.rv_exchage)
    RecyclerView mRvExchage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.view)
    View mView;

    private List<ShopResult.GiftListNewBean> exchageList = new ArrayList<>();
    private ExchageAdapter exchageAdapter;
    private int pagaNo = 1;
    private ShopAdapter shopAdapter;

    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        return R.layout.fragment_category;
    }

    @Override
    protected void initData() {
        mPresenter.IndexJson(String.valueOf(pagaNo));
//        exchageAdapter = new ExchageAdapter(R.layout.item_shop, exchageList);

//        exchageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(mActivity, GiftsDetailActivity.class);
//                intent.putExtra("giftId", exchageList.get(position).getId());
//                startActivity(intent);
//            }
//        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            exchageList.clear();
//            mPresenter.index();
            pagaNo = 1;
            mPresenter.IndexJson(String.valueOf(pagaNo));
            refreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh(1000);
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++;
                mPresenter.IndexJson(String.valueOf(pagaNo));
                mRefreshLayout.finishLoadmore();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void index(Shop result) {

    }

    @Override
    public void IndexJson(ShopResult result) {
        if (result.getGiftTotal() != 0) {
            exchageList.addAll(result.getGiftListNew());
//            exchageAdapter.setNewData(exchageList);
            shopAdapter = new ShopAdapter(mActivity,exchageList);
            StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            mRvExchage.setLayoutManager(staggeredGridLayoutManager);
//        mRvExchage.setAdapter(exchageAdapter);
            mRvExchage.setAdapter(shopAdapter);
        }
    }

    @Override
    public void GetSlideAds(List<GiftAds> result) {

    }

    @Override
    public void GetList(Announcement result) {

    }
}
