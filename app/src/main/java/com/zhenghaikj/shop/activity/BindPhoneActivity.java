package com.zhenghaikj.shop.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.SendMessage;
import com.zhenghaikj.shop.mvp.contract.BindPhoneContract;
import com.zhenghaikj.shop.mvp.model.BindPhoneModel;
import com.zhenghaikj.shop.mvp.presenter.BindPhonePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BindPhoneActivity extends BaseActivity<BindPhonePresenter, BindPhoneModel> implements View.OnClickListener, BindPhoneContract.View {
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
    private String phone;
    private String code;
    private SPUtils spUtils;
    private String userKey;

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
        mPresenter.GetImageCheckCode();

        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
    }

    @Override
    protected void setListener() {
        mImgRegisterBack.setOnClickListener(this);
        mTvSendYzm.setOnClickListener(this);
        mIvSendYzm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_register_back:
                finish();
                break;
            case R.id.iv_send_yzm:
                mPresenter.GetImageCheckCode();
                break;
            case R.id.tv_send_yzm:
                phone = mEtPhone.getText().toString();
                code = mEtPhoneYzm.getText().toString();
                if (phone.isEmpty()){
                    ToastUtils.showShort("请输入手机号");
                }else if (!RegexUtils.isMobileExact(phone)){
                    ToastUtils.showShort("手机号格式不正确！");
                } else if (code.isEmpty()){
                    ToastUtils.showShort("图片验证码不能为空");
                }else {
                    mPresenter.CheckUserName(phone,code);
                }
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
    public void GetCode(SendMessage result) {

    }

    @Override
    public void GetCheckPhoneOrEmailCheckCode(CheckMessage result) {

    }

    @Override
    public void GetImageCheckCode(GetImageCheckCode baseResult) {
        byte[] decode;
        decode = Base64.decode(baseResult.getFileContents(), Base64.DEFAULT);
        Glide.with(mActivity).asBitmap().load(decode).into(mIvSendYzm);
    }

    @Override
    public void CheckUserName(CheckMessage result) {
        if (result.isSuccess()){
            TimeCount timeCount = new TimeCount(60000, 1000);
            timeCount.start();
            mPresenter.GetCode(phone,userKey);
        }else {
            ToastUtils.showShort(result.getMsg());
        }
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
