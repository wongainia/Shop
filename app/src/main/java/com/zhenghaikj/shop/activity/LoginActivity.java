package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.android.tpush.XGPushConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.mvp.contract.LoginContract;
import com.zhenghaikj.shop.mvp.model.LoginModel;
import com.zhenghaikj.shop.mvp.presenter.LoginPresenter;
import com.zhenghaikj.shop.widget.ClearEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements View.OnClickListener, LoginContract.View {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.et_username)
    ClearEditText mEtUsername;
    @BindView(R.id.et_password)
    ClearEditText mEtPassword;
    @BindView(R.id.ll_password)
    LinearLayout mLlPassword;
    @BindView(R.id.et_verification_code)
    ClearEditText mEtVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_change)
    TextView mTvChange;
    @BindView(R.id.cb)
    CheckBox mCb;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.view)
    View mView;
    private SPUtils spUtils;
    private String userName;
    private String password;
    private int login_state;
    private String code;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        userName=spUtils.getString("user");
        password=spUtils.getString("password");
        mEtUsername.setText(userName);
        mEtPassword.setText(password);
    }
    @Override
    protected void setListener() {
        mTvRegister.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mTvChange.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
        mTvGetVerificationCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(mActivity, RegisterActivity.class));
                break;
            case R.id.login:
                userName = mEtUsername.getText().toString();
                password = mEtPassword.getText().toString();
                code =mEtVerificationCode.getText().toString();
                if (login_state==0){

                    if ("".equals(userName)) {
                        ToastUtils.showShort("请输入手机号！");
                        return;
                    }
                    if ("".equals(password)) {
                        ToastUtils.showShort("请输入密码！");
                        return;
                    }
                    mPresenter.GettokenbyUserid(userName);
                }else {
                    if ("".equals(userName)) {
                        ToastUtils.showShort("请输入手机号！");
                        return;
                    }

                    if ("".equals(code)) {
                        ToastUtils.showShort("请输入验证码！");
                        return;
                    }
//                    mPresenter.LoginOnMessage(userName, code);

                }
                break;
            case R.id.tv_change:
                if (mLlCode.getVisibility()==View.GONE){
                    mLlCode.setVisibility(View.VISIBLE);
                    mLlPassword.setVisibility(View.GONE);
                    mTvChange.setText("密码登录>");
                    //状态为验证码登陆
                    login_state =1;

                }else{
                    mLlCode.setVisibility(View.GONE);
                    mLlPassword.setVisibility(View.VISIBLE);
                    mTvChange.setText("短信验证码登录>");
                    login_state =0;
                }
                break;
            case R.id.tv_agreement:
                Intent intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("Url","http://admin.xigyu.com/Agreement");
                intent.putExtra("Title","用户协议");
                startActivity(intent);
                break;
            case R.id.tv_get_verification_code:

                userName=mEtUsername.getText().toString();
                if (userName.isEmpty()){
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (!RegexUtils.isMobileExact(userName)){
                    ToastUtils.showShort("手机格式不正确！");
                    return;
                }
//                mPresenter.ValidateUserName(userName);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetUser(LoginResult Result) {

        if (Result.getSuccess()){
            spUtils.put("UserKey",Result.getUserKey());
            spUtils.put("user",userName);
            spUtils.put("userName",userName);
            spUtils.put("password",password);
            spUtils.put("isLogin",true);
            EventBus.getDefault().post("更新登录信息");
            finish();
//            ActivityUtils.finishAllActivities();
//            startActivity(new Intent(mActivity,MainActivity.class));
        }else {
            ToastUtils.showShort("系统错误");
        }
    }

    @Override
    public void LoginOn(BaseResult<Data<String>> Result){
        switch (Result.getStatusCode()) {
            case 200:
                Data<String> data=Result.getData();
                if (data.isItem1()){
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", userName);
                    mPresenter.GetUser(userName, password/*,"","",""*/);
//                    spUtils.put("passWord", password);
//                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this),"8",userName);
//                    startActivity(new Intent(mActivity, MainActivity.class));
//                    finish();
                }else{
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }
    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){

                }else{
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    public void GettokenbyUserid(BaseResult<Data<String>> Result) {

        switch (Result.getStatusCode()){
            case 200:
                /*已经登录过了 后台存有token*/
                if (Result.getData().isItem1()){
                    spUtils.put("adminToken", Result.getData().getItem2());
                    spUtils.put("userName", userName);
                    mPresenter.GetUser(userName, password/*,"","",""*/);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this),"8",userName);

                }else {
                    mPresenter.LoginOn(userName, password);
                }

                break;
        }

    }
}
