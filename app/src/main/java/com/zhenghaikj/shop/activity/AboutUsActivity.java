package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;
import com.tencent.bugly.beta.Beta;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.utils.MyUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.ll_check_for_updates)
    LinearLayout mLlCheckForUpdates;
    @BindView(R.id.ll_using_help)
    LinearLayout mLlUsingHelp;
    @BindView(R.id.ll_privacy_policy)
    LinearLayout mLlPrivacyPolicy;
    @BindView(R.id.tv_company_english)
    TextView mTvCompanyEnglish;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    private Intent intent;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        mTvVersion.setText("v"+MyUtils.getAppVersionName(mActivity));
    }


    @Override
    protected void initView() {
        mTvTitle.setText("关于我们");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlCheckForUpdates.setOnClickListener(this);
        mLlUsingHelp.setOnClickListener(this);
        mLlPrivacyPolicy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_check_for_updates:
                Beta.checkUpgrade(false,false);
                break;
            case R.id.ll_using_help:
                intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("Url","http://admin.xigyu.com/Agreement");
                intent.putExtra("Title","使用帮助");
                startActivity(intent);
                break;
            case R.id.ll_privacy_policy:
                intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("Url","http://admin.xigyu.com/Agreement");
                intent.putExtra("Title","隐私政策");
                startActivity(intent);
                break;

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("update".equals(name)){
            MyUtils.showToast(mActivity, "已是最新版本！");
        }
    }
}
