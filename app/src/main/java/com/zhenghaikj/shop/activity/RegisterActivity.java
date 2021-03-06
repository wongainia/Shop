package com.zhenghaikj.shop.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.android.tpush.XGPushConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.entity.RegisterResult;
import com.zhenghaikj.shop.entity.SendMessage;
import com.zhenghaikj.shop.mvp.contract.RegisterContract;
import com.zhenghaikj.shop.mvp.model.RegisterModel;
import com.zhenghaikj.shop.mvp.presenter.RegisterPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements View.OnClickListener, RegisterContract.View {

    private static final String TAG = "RegisterActivity";
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.img_register_back)
    ImageView mImgRegisterBack;
    @BindView(R.id.et_register_phone)
    EditText mEtRegisterPhone;
    @BindView(R.id.et_register_yzm)
    EditText mEtRegisterYzm;
    @BindView(R.id.et_register_password)
    EditText mEtRegisterPassword;
    @BindView(R.id.et_register_password_again)
    EditText mEtRegisterPasswordAgain;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.img_agreement)
    ImageView mImgAgreement;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.iv_send_yzm)
    ImageView mIvSendYzm;
    @BindView(R.id.tv_send_yzm)
    TextView mTvSendYzm;
    private String phone;
    private GetImageCheckCode data = new GetImageCheckCode();
    private String userName;
    private String password;
    private String passwordAgain;
    private String code;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
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
        Log.d(TAG, "乱码外" + data.getFileContents());
    }

    @Override
    protected void initView() {
        mImgAgreement.setSelected(true);
        mPresenter.GetImageCheckCode();
    }

    @Override
    protected void setListener() {
        mImgAgreement.setOnClickListener(this);
        mImgRegisterBack.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvSendYzm.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_register_back:
                finish();
                break;
            case R.id.img_agreement:
                if (mImgAgreement.isSelected()) {
                    mImgAgreement.setSelected(false);
                } else {
                    mImgAgreement.setSelected(true);
                }
                break;
            case R.id.tv_send_yzm:
                phone = mEtRegisterPhone.getText().toString();
                if (phone.isEmpty()) {
                    ToastUtils.showShort("请输入手机号！");
                } else if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showShort("手机格式不正确！");
                } else {
                    mPresenter.GetCode(phone,"");
                }
//                mPresenter.GetImageCheckCode();
                break;
            case R.id.tv_register:
                userName = mEtRegisterPhone.getText().toString();
                password = mEtRegisterPassword.getText().toString();
                passwordAgain = mEtRegisterPasswordAgain.getText().toString();
                code = mEtRegisterYzm.getText().toString();
                if (userName.isEmpty()) {
                    ToastUtils.showShort("请输入手机号！");
                } else if (code.isEmpty()) {
                    ToastUtils.showShort("请输入验证码！");
                } else if (password.isEmpty()) {
                    ToastUtils.showShort("请输入密码！");
                } else if (passwordAgain.isEmpty()) {
                    ToastUtils.showShort("请再次输入密码！");
                } else if (password.length() < 6) {
                    ToastUtils.showShort("密码不小于六位！");
                } else if (!mImgAgreement.isSelected()) {
                    ToastUtils.showShort("请阅读并同意用户协议");
                } else if (password.equals(passwordAgain)) {
                    mPresenter.GetCheckPhoneOrEmailCheckCode(userName,code,"");//验证验证码
                } else {
                    ToastUtils.showShort("两次密码不一致！");
                }
                break;
            case R.id.tv_agreement:
                Intent intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("Url","http://mall.xigyu.com/m-Wap/Article/YSZC/20");
                intent.putExtra("Title","用户协议");
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

    @Override
    public void Reg(RegisterResult baseResult) {
        if (baseResult.isSuccess()) {
            ToastUtils.showShort("注册成功");
            mPresenter.LoginOn(userName, password);
        } else {
            ToastUtils.showShort(baseResult.getErrorMsg());
        }

    }


    @Override
    public void GetImageCheckCode(GetImageCheckCode baseResult) {
        byte[] decode;
        Log.d(TAG, "乱码" + baseResult.getFileContents());
        decode = Base64.decode(baseResult.getFileContents(), Base64.DEFAULT);
        Glide.with(mActivity).asBitmap().load(decode).into(mIvSendYzm);
    }

    @Override
    public void GetUser(LoginResult Result) {

        if (Result.getSuccess()) {
            spUtils.put("UserKey", Result.getUserKey());
            spUtils.put("userName", userName);
            spUtils.put("password", password);
            spUtils.put("isLogin", true);
//            EventBus.getDefault().post("PersonalInformation");
            ActivityUtils.finishAllActivities();
            startActivity(new Intent(mActivity, MainActivity.class));

        } else {
            ToastUtils.showShort(Result.getErrorMsg());
        }
    }

    @Override
    public void LoginOn(BaseResult<Data<String>> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                Data<String> data = Result.getData();
                if (data.isItem1()) {
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", userName);
                    mPresenter.GetUser(userName, password/*, "", "", ""*/);
//                    spUtils.put("passWord", password);
//                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this), "8", userName);
//                    startActivity(new Intent(mActivity, MainActivity.class));
//                    finish();
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {

                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    public void AddFactoryBrand(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetCode(SendMessage result) {
        if (result.isSuccess()){
            TimeCount timeCount = new TimeCount(60000, 1000);
            timeCount.start();
            ToastUtils.showShort("验证码已发送");
        }else {
            ToastUtils.showShort(result.getMsg());
        }
    }

    @Override
    public void GetCheckPhoneOrEmailCheckCode(CheckMessage result) {
        if (result.isSuccess()){
            mPresenter.Reg(userName, password, null, null, code, null, null);
        }else{
            ToastUtils.showShort(result.getMsg());
        }
    }

    @Override
    public void CheckUserName(CheckMessage result) {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if (!"RegiaterActivity".equals(name)) {
            return;
        }
        mPresenter.GetImageCheckCode();
    }
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onTick(long millisUntilFinished) {
            if (mTvSendYzm == null) {
                return;
            }
            mTvSendYzm.setClickable(false);
            mTvSendYzm.setTextColor(R.color.gray_three);
            mTvSendYzm.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            if (mTvSendYzm == null) {
                return;
            }
            mTvSendYzm.setText("重新获取验证码");
            mTvSendYzm.setClickable(true);
        }
    }
}
