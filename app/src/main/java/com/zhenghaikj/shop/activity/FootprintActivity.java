package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.FootprintAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.mvp.contract.HistoryVisiteContract;
import com.zhenghaikj.shop.mvp.model.HistoryVisiteModel;
import com.zhenghaikj.shop.mvp.presenter.HistoryVisitePresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FootprintActivity extends BaseActivity<HistoryVisitePresenter, HistoryVisiteModel> implements View.OnClickListener, HistoryVisiteContract.View {
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
    private SPUtils spUtils;
    private String userKey;

    private List<HistoryVisite.ProductBean> list=new ArrayList<>();
    private FootprintAdapter adapter;

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
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        adapter = new FootprintAdapter(R.layout.item_footprint,list);
        mRvFootprint.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvFootprint.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("我的足迹");

        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        mPresenter.GetHistoryVisite(userKey);
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
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    public void GetHistoryVisite(HistoryVisite result) {
        if (result.isSuccess()){
            list.addAll(result.getProduct());
            adapter.setNewData(list);
        }
    }
}
