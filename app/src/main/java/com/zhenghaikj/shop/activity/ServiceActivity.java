package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.iv_goods_picture)
    ImageView mIvGoodsPicture;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_number_of_applications)
    TextView mTvNumberOfApplications;
    @BindView(R.id.ll_reason_for_application)
    LinearLayout mLlReasonForApplication;
    @BindView(R.id.et_problem_description)
    EditText mEtProblemDescription;
    @BindView(R.id.tv_word_count)
    TextView mTvWordCount;
    @BindView(R.id.iv_speak)
    ImageView mIvSpeak;
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.switcher_installation_work_order)
    Switch mSwitcherInstallationWorkOrder;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_contact)
    TextView mTvContact;
    @BindView(R.id.tv_contact_number)
    TextView mTvContactNumber;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_service;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("维修");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
