package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.img_register_back)
    ImageView mImgRegisterBack;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_phone_yzm)
    EditText mEtPhoneYzm;
    @BindView(R.id.iv_send_yzm)
    ImageView mIvSendYzm;
    @BindView(R.id.et_phone_code)
    EditText mEtPhoneCode;
    @BindView(R.id.tv_send_yzm)
    TextView mTvSendYzm;
    @BindView(R.id.tv_bind_immediately)
    TextView mTvBindImmediately;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_binding_phone;
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
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgRegisterBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_register_back:
                finish();
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
