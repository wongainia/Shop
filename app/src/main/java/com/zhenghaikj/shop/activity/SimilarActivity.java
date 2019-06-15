package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SimilarAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.SimilarProduct;
import com.zhenghaikj.shop.mvp.contract.HistoryVisiteContract;
import com.zhenghaikj.shop.mvp.model.HistoryVisiteModel;
import com.zhenghaikj.shop.mvp.presenter.HistoryVisitePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimilarActivity extends BaseActivity<HistoryVisitePresenter, HistoryVisiteModel> implements View.OnClickListener, HistoryVisiteContract.View {
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
    @BindView(R.id.rv_footprint)
    RecyclerView mRvFootprint;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private SPUtils spUtils;
    private String userKey;
    private int pagaNo = 1;
    private List<SimilarProduct> list = new ArrayList<>();
    private SimilarAdapter adapter;
    private String productId;
    private String categoryId;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_footprint;
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
        adapter = new SimilarAdapter(R.layout.item_similar_product, list);
        adapter.setEmptyView(getEmptyView());
        mRvFootprint.setLayoutManager(new GridLayoutManager(mActivity,2));
        mRvFootprint.setAdapter(adapter);
        adapter.setEmptyView(getEmptyView());
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
            intent.putExtra("id", list.get(position).getId());
            startActivity(intent);
        });
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("找相似");
        productId=getIntent().getStringExtra("productId");
        categoryId=getIntent().getStringExtra("categoryId");
        mPresenter.GetHotProduct(productId, categoryId);

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pagaNo=1;
                list.clear();
                mPresenter.GetHotProduct(productId, categoryId);
                refreshlayout.finishRefresh(1000);
                mRefreshLayout.setNoMoreData(false);
            }
        });


        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setEnableLoadMore(false);

    //上拉加载更多
//        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                pagaNo++; //页数加1
//                mPresenter.GetHistoryVisite("10",Integer.toString(pagaNo),userKey);
//                refreshlayout.finishLoadmore();
//            }
//        });

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
    public void GetHistoryVisite(HistoryVisite result) {
    }

    @Override
    public void GetHotProduct(List<SimilarProduct> result) {
        list=result;
        adapter.setNewData(list);
    }
}
