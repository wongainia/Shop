package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.StoreDetailActivity;
import com.zhenghaikj.shop.adapter.StoreAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.CollectionShop;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.mvp.contract.CollectionShopContract;
import com.zhenghaikj.shop.mvp.model.CollectionShopModel;
import com.zhenghaikj.shop.mvp.presenter.CollectionShopPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StoreFragment extends BaseLazyFragment<CollectionShopPresenter, CollectionShopModel> implements CollectionShopContract.View {

    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.rv_store)
    RecyclerView mRvStore;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private List<CollectionShop.DataBean> storeList = new ArrayList<>();

    private int pageNo = 1;
    private String mParam1;
    private String mParam2;
    private StoreAdapter storeAdapter;

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_store;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        mPresenter.GetUserCollectionShop(Integer.toString(pageNo), "10", userKey);
        storeAdapter = new StoreAdapter(R.layout.item_store, storeList);
        mRvStore.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvStore.setAdapter(storeAdapter);
        storeAdapter.setEmptyView(getEmptyViewCommodity());
        storeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_unsubscribe:
                        mPresenter.PostAddFavoriteShop(((CollectionShop.DataBean)adapter.getItem(position)).getShopId(),userKey);
                        mRefreshLayout.autoRefresh();
                        break;
                    case R.id.ll_store:
                        Intent intent=new Intent(mActivity, StoreDetailActivity.class);
                        intent.putExtra("VShopId",((CollectionShop.DataBean)adapter.getItem(position)).getId());

                        startActivity(intent);
                        break;
                }
            }
        });

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

    }

    @Override
    protected void setListener() {

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
