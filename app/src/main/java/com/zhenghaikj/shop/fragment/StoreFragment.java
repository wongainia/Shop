package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.StoreDetailActivity;
import com.zhenghaikj.shop.adapter.StoreAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.entity.CollectionProduct;
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
//                        mRefreshLayout.autoRefresh();
                        adapter.remove(position);
                        break;
                    case R.id.ll_store:
                        Intent intent=new Intent(mActivity, StoreDetailActivity.class);
                        intent.putExtra("VShopId",((CollectionShop.DataBean)adapter.getItem(position)).getId());

                        startActivity(intent);
                        break;
                }
            }
        });

        storeAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_store:
                        final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
                        dialog.setMessage("是否删除商品")
                                //.setImageResId(R.mipmap.ic_launcher)
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {//拨打电话
                                dialog.dismiss();
                                mPresenter.PostAddFavoriteShop(((CollectionShop.DataBean)adapter.getItem(position)).getShopId(),userKey);
//                                mRefreshLayout.autoRefresh();
                                adapter.remove(position);
                            }

                            @Override
                            public void onNegtiveClick() {//取消
                                dialog.dismiss();
                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                        break;
                }
                return true;
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

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++; //页数加1
                mPresenter.GetUserCollectionShop(Integer.toString(pageNo), "10", userKey);
                storeAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
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
        if (result.isSuccess()){
            ToastUtils.showShort(result.getMsg());
        }
    }
}
