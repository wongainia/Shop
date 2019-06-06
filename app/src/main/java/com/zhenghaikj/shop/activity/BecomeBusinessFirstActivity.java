package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BecomeBusinessFirstActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.img_agreement)
    ImageView mImgAgreement;
    @BindView(R.id.tv_agree)
    TextView mTvAgree;
    @BindView(R.id.fl_first)
    FrameLayout mFlFirst;
    @BindView(R.id.ll_enterprise)
    LinearLayout mLlEnterprise;
    @BindView(R.id.ll_personal)
    LinearLayout mLlPersonal;
    @BindView(R.id.ll_two)
    LinearLayout mLlTwo;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_become_business_first;
    }

    @Override
    protected void initData() {

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
    protected void initView() {
        mTvTitle.setText("成为商家");
        mTvTitle.setVisibility(View.VISIBLE);

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvAgree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_agree:
                mFlFirst.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
