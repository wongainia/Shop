package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillingActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.cb_yes)
    CheckBox mCbYes;
    @BindView(R.id.ll_yes)
    LinearLayout mLlYes;
    @BindView(R.id.cb_no)
    CheckBox mCbNo;
    @BindView(R.id.ll_no)
    LinearLayout mLlNo;
    @BindView(R.id.et_invoice)
    EditText mEtInvoice;
    @BindView(R.id.et_tax_number)
    EditText mEtTaxNumber;
    @BindView(R.id.ll_tax_number)
    LinearLayout mLlTaxNumber;
    @BindView(R.id.ll_no_invoice)
    LinearLayout mLlNoInvoice;
    @BindView(R.id.btn_complete)
    Button mBtnComplete;
    private Intent intent;
    private Bundle extras;
    private String invoiceType = "2";
    private int type = 0;
    private int position;
    private Bundle bundle;

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
        return R.layout.activity_billing;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("开具发票");
        mTvTitle.setVisibility(View.VISIBLE);
        extras = getIntent().getExtras();
        position = extras.getInt("position");
        mCbYes.setChecked(true);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlNoInvoice.setOnClickListener(this);
        mLlYes.setOnClickListener(this);
        mLlNo.setOnClickListener(this);
        mBtnComplete.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_no_invoice:
                intent = new Intent();
                intent.putExtra("noInvoice", "本次不开具发票");
                intent.putExtra("invoiceType", "0");
                bundle = new Bundle();
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                setResult(200, intent);
                finish();
                break;
            case R.id.ll_yes:
                mCbYes.setChecked(true);
                mCbNo.setChecked(false);
                mLlTaxNumber.setVisibility(View.GONE);
                type=0;
                break;
            case R.id.ll_no:
                mCbYes.setChecked(false);
                mCbNo.setChecked(true);
                mLlTaxNumber.setVisibility(View.VISIBLE);
                type=1;
                break;
            case R.id.btn_complete:
                String title=mEtInvoice.getText().toString();
                String number=mEtTaxNumber.getText().toString();
                if (type==0){
                    if (title==null||"".equals(title)){
                        ToastUtils.showShort("请输入发票抬头");
                    }else {
                        intent = new Intent();
                        intent.putExtra("noInvoice", title);
                        intent.putExtra("invoiceType", "2");
                        bundle = new Bundle();
                        bundle.putInt("position", position);
                        intent.putExtras(bundle);
                        setResult(200, intent);
                        finish();
                    }
                }else {
                    if (title==null||"".equals(title)||number==null||"".equals(number)){
                        ToastUtils.showShort("请输入发票抬头");
                    }else {
                        intent = new Intent();
                        intent.putExtra("noInvoice", title);
                        intent.putExtra("invoiceType", "2");
                        bundle = new Bundle();
                        bundle.putInt("position", position);
                        intent.putExtras(bundle);
                        setResult(200, intent);
                        finish();
                    }
                }
                break;
        }
    }
}
