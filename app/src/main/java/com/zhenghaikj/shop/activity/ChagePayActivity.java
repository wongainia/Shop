package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.ChangePayPasswordContract;
import com.zhenghaikj.shop.mvp.model.ChangePayPasswordModel;
import com.zhenghaikj.shop.mvp.presenter.ChangePayPasswordPresenter;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChagePayActivity extends BaseActivity<ChangePayPasswordPresenter, ChangePayPasswordModel> implements View.OnClickListener, ChangePayPasswordContract.View {

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
    @BindView(R.id.et_old_password)
    EditText mEtOldPassword;
    @BindView(R.id.et_new_password)
    EditText mEtNewPassword;
    @BindView(R.id.et_new_password_again)
    EditText mEtNewPasswordAgain;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    @BindView(R.id.ll_paypassword)
    LinearLayout mLlPaypassword;
    private String userkey;
    private String userName;
    private UserInfo.UserInfoDean userInfo;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_pay;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userkey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");
        mPresenter.GetUserInfoList(userName, "1");
    }

    @Override
    protected void initView() {
        mTvTitle.setText("修改支付密码");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_save:
                if ("".equals(userInfo.getPayPassWord())||userInfo.getPayPassWord().isEmpty()){
                    String password=mEtNewPassword.getText().toString();
                    String password2=mEtNewPasswordAgain.getText().toString();
                    if (password.isEmpty()){
                        ToastUtils.showShort("请输入密码");
                    }else if (password2.isEmpty()){
                        ToastUtils.showShort("请确认密码");
                    }else if (password.equals(password2)){
                        mPresenter.ChangePayPassword(userName,"Pass",password);
                    }else {
                        ToastUtils.showShort("两次密码不一致");
                    }
                }else {
                    String payPassword=mEtOldPassword.getText().toString();
                    String password=mEtNewPassword.getText().toString();
                    String password2=mEtNewPasswordAgain.getText().toString();
                    if (payPassword.isEmpty()){
                        ToastUtils.showShort("请输入原密码");
                    }else if (password.isEmpty()){
                        ToastUtils.showShort("请输入密码");
                    }else if (password2.isEmpty()){
                        ToastUtils.showShort("请确认密码");
                    } else if (!payPassword.equals(userInfo.getPayPassWord())){
                        ToastUtils.showShort("原密码不正确");
                    }else if (!password.equals(password2)){
                        ToastUtils.showShort("两次密码不一致");
                    }else {
                        mPresenter.ChangePayPassword(userName,payPassword,password);
                    }
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
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                if ("".equals(userInfo.getPayPassWord())) {
                    mTvTitle.setText("设置支付密码");
                    mTvTitle.setVisibility(View.VISIBLE);
                    mLlPaypassword.setVisibility(View.GONE);
                }else {
                    mTvTitle.setText("修改支付密码");
                    mTvTitle.setVisibility(View.VISIBLE);
                    mLlPaypassword.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void ChangePayPassword(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
               if (baseResult.getData().isItem1()){
                   ToastUtils.showShort(baseResult.getData().getItem2()+"");
               }
                break;

        }
    }
}
