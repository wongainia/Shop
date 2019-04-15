package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChagePasswordActivity extends BaseActivity implements View.OnClickListener {

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
    protected void initView() {


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
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
