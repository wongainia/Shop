package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.iv_see)
    ImageView mIvSee;
    @BindView(R.id.tv_watermelon_balance)
    TextView mTvWatermelonBalance;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    @BindView(R.id.tv_withdraw)
    TextView mTvWithdraw;
    @BindView(R.id.tv_gift)
    TextView mTvGift;
    @BindView(R.id.tv_bank_card_number)
    TextView mTvBankCardNumber;
    @BindView(R.id.ll_bank_card)
    LinearLayout mLlBankCard;
    @BindView(R.id.tv_voucher_number)
    TextView mTvVoucherNumber;
    @BindView(R.id.ll_voucher)
    LinearLayout mLlVoucher;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.ll_record)
    LinearLayout mLlRecord;
    @BindView(R.id.ll_mobile_phone_recharge)
    LinearLayout mLlMobilePhoneRecharge;
    @BindView(R.id.ll_fuel_card_recharge)
    LinearLayout mLlFuelCardRecharge;
    @BindView(R.id.ll_living_payment)
    LinearLayout mLlLivingPayment;
    @BindView(R.id.ll_credit_card_payments)
    LinearLayout mLlCreditCardPayments;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_wallet;
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
        mIconBack.setOnClickListener(this);
        mLlBankCard.setOnClickListener(this);
        mLlRecord.setOnClickListener(this);
        mTvRecharge.setOnClickListener(this);
        mTvWithdraw.setOnClickListener(this);
        mTvGift.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_bank_card:
                startActivity(new Intent(mActivity,BrandCardActivity.class));
                break;
            case R.id.ll_record:
                startActivity(new Intent(mActivity,RecordingActivity.class));
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(mActivity,RechargeActivity.class));
                break;
            case R.id.tv_withdraw:
                startActivity(new Intent(mActivity,WithdrawActivity.class));
                break;
            case R.id.tv_gift:
                startActivity(new Intent(mActivity,GiftActivity.class));
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
