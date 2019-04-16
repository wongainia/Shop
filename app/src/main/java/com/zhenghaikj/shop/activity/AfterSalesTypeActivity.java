package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.widget.AdderView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AfterSalesTypeActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.adderview)
    AdderView mAdderview;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.ll_exchange)
    LinearLayout mLlExchange;
    @BindView(R.id.ll_contact_customer_service)
    LinearLayout mLlContactCustomerService;
    @BindView(R.id.ll_dial_number)
    LinearLayout mLlDialNumber;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_after_sales_type;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("选择售后类型");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_service:
                startActivity(new Intent(mActivity,ServiceActivity.class));
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
