package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.FilterResult;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.SearchContract;
import com.zhenghaikj.shop.mvp.model.SearchModel;
import com.zhenghaikj.shop.mvp.presenter.SearchPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchShopPreDetailActivity extends BaseActivity<SearchPresenter, SearchModel> implements SearchContract.View, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_serach)
    TextView mTvSerach;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.ll_serach)
    LinearLayout mLlSerach;


    private String shopid;
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_searchshoppredetail;
    }

    @Override
    protected void initData() {
        shopid=getIntent().getStringExtra("shopid");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlSerach.setOnClickListener(this);
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
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_serach:
                String content=mEtSearch.getText().toString();
                if ("".equals(content)||content==null){

               }else {
                    Intent intent=new Intent(mActivity,SearchDetailShopDetailActivity.class);
                    intent.putExtra("content",content);
                    intent.putExtra("shopid",shopid);
                    startActivity(intent);
                }


                break;
        }
    }

    @Override
    public void GetSearchProducts(SearchResult Result) {

    }

    @Override
    public void GetSearchFilter(FilterResult Result) {

    }

}
