package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.StoreAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.CollectionShop;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.mvp.contract.CollectionShopContract;
import com.zhenghaikj.shop.mvp.model.CollectionShopModel;
import com.zhenghaikj.shop.mvp.presenter.CollectionShopPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*关注的店铺*/
public class StoreActivity extends BaseActivity<CollectionShopPresenter, CollectionShopModel> implements View.OnClickListener, CollectionShopContract.View {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_store)
    RecyclerView mRvStore;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int pageNo = 1;
    private List<CollectionShop.DataBean> storeList = new ArrayList<>();
    private StoreAdapter storeAdapter;
    private SPUtils spUtils;
    private String userKey;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_store;
    }

    /**
     * 初始化沉浸式
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        mPresenter.GetUserCollectionShop(Integer.toString(pageNo), "10", userKey);
        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pageNo=1;
                storeList.clear();
                mPresenter.GetUserCollectionShop(Integer.toString(pageNo), "10", userKey);
                storeAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
                mRefreshLayout.setNoMoreData(false);
            }
        });


        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNo++; //页数加1
                mPresenter.GetUserCollectionShop(Integer.toString(pageNo), "10", userKey);
                storeAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });


    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("我的关注");

        storeAdapter = new StoreAdapter(R.layout.item_store, storeList);
        storeAdapter.setEmptyView(getEmptyView());
        mRvStore.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvStore.setAdapter(storeAdapter);
        storeAdapter.setEmptyView(getEmptyView());
        storeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_unsubscribe:
                        mPresenter.PostAddFavoriteShop(((CollectionShop.DataBean)adapter.getItem(position)).getShopId(),userKey);
                        mRefreshLayout.autoRefresh();
                        break;
                    case R.id.ll_store:
                        Intent intent=new Intent(mActivity,StoreDetailActivity.class);
                        intent.putExtra("VShopId",((CollectionShop.DataBean)adapter.getItem(position)).getId());

                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    public void GetUserCollectionShop(CollectionShop result) {
        if (result.isSuccess()) {
            storeList.addAll(result.getData());
            storeAdapter.setNewData(storeList);
        }
    }

    @Override
    public void PostAddFavoriteShop(PostattentionResult result) {
//        mPresenter.GetUserCollectionShop(Integer.toString(pageNo), "10", userKey);
    }
}
