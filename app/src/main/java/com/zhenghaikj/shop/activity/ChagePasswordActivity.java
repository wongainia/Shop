package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.ChagePassword;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.ChagePasswordContract;
import com.zhenghaikj.shop.mvp.model.ChagePasswordModel;
import com.zhenghaikj.shop.mvp.presenter.ChagePasswordPresenter;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChagePasswordActivity extends BaseActivity<ChagePasswordPresenter, ChagePasswordModel> implements View.OnClickListener, ChagePasswordContract.View {

    private static final String TAG = "ChagePasswordActivity";
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

    private String userId;
    private SPUtils spUtils;
    private String userKey;
    private String UserID;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("修改密码");
    }

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
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        UserID = spUtils.getString("userName2");

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_save:
                String oldPassword=mEtOldPassword.getText().toString();
                String password=mEtNewPassword.getText().toString();
                String passworeAgain=mEtNewPasswordAgain.getText().toString();
                if (oldPassword.isEmpty()){
                    ToastUtils.showShort("请输入旧密码");
                }else if (password.isEmpty()){
                    ToastUtils.showShort("请输入新密码");
                }else if (passworeAgain.isEmpty()){
                    ToastUtils.showShort("请确认密码");
                }else if (!password.equals(passworeAgain)){
                    ToastUtils.showShort("两次密码不一致");
                }else {
                    mPresenter.PostChangePassword(oldPassword,password,userKey);
                    mPresenter.UpdatePassword(UserID,password);
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
    public void PostChangePassword(ChagePassword result) {
        if (result.isSuccess()){
            ToastUtils.showShort("密码修改成功");
            finish();
        }else {
            ToastUtils.showShort(result.getMsg());
        }
    }

    @Override
    public void UpdatePassword(BaseResult<Data> result) {

    }
}
