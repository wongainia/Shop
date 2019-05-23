package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.AfterSaleAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.mvp.contract.RefundContract;
import com.zhenghaikj.shop.mvp.model.RefundModel;
import com.zhenghaikj.shop.mvp.presenter.RefundPresent;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AfterSaleActivity extends BaseActivity<RefundPresent, RefundModel> implements View.OnClickListener, RefundContract.View {
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
    @BindView(R.id.rv_after_sale)
    RecyclerView mRvAfterSale;
    @BindView(R.id.srl_after_sale)
    SmartRefreshLayout mSrlAfterSale;
    private int pageNo=1;
    private ArrayList<Refund.DataBean> afterSaleList=new ArrayList<>();
    private AfterSaleAdapter afterSaleAdapter;
    private SPUtils spUtil;
    private String userKey;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_after_sale;
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
        spUtil = SPUtils.getInstance("token");
        userKey = spUtil.getString("UserKey");
   //     mPresenter.GetRefundList(Integer.toString(pageNo),"10", userKey);
/*
        afterSaleAdapter = new AfterSaleAdapter(R.layout.item_after_sale,afterSaleList);
        afterSaleAdapter.setEmptyView(getEmptyView());
        mRvAfterSale.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAfterSale.setAdapter(afterSaleAdapter);*/
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("退换/售后");
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
    public void GetRefundList(Refund result) {
        if (result.isSuccess()){
            afterSaleList.addAll(result.getData());
            afterSaleAdapter.setNewData(afterSaleList);
        }
    }
}
