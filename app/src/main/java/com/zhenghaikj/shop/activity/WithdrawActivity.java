package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.widget.TradeTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WithdrawActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.iv_brank_logo)
    ImageView mIvBrankLogo;
    @BindView(R.id.tv_bank_name)
    TextView mTvBankName;
    @BindView(R.id.tv_tail_number)
    TextView mTvTailNumber;
    @BindView(R.id.tv_card_type)
    TextView mTvCardType;
    @BindView(R.id.ll_bank_card)
    LinearLayout mLlBankCard;
    @BindView(R.id.et_withdrawal_amount)
    EditText mEtWithdrawalAmount;
    @BindView(R.id.tv_available_balance)
    TextView mTvAvailableBalance;
    @BindView(R.id.btn_confirm_withdrawal)
    Button mBtnConfirmWithdrawal;
    @BindView(R.id.btn_price_1)
    TextView mBtnPrice1;
    @BindView(R.id.btn_price_2)
    TextView mBtnPrice2;
    @BindView(R.id.btn_price_3)
    TextView mBtnPrice3;
    @BindView(R.id.btn_price_4)
    TextView mBtnPrice4;
    @BindView(R.id.btn_price_5)
    TextView mBtnPrice5;
    @BindView(R.id.btn_price_6)
    TextView mBtnPrice6;
    @BindView(R.id.btn_price_7)
    TextView mBtnPrice7;
    @BindView(R.id.btn_price_8)
    TextView mBtnPrice8;
    @BindView(R.id.btn_price_9)
    TextView mBtnPrice9;
    @BindView(R.id.btn_price_0)
    TextView mBtnPrice0;
    @BindView(R.id.ll_hide)
    LinearLayout mLlHide;
    @BindView(R.id.btn_price_del)
    LinearLayout mBtnPriceDel;
    @BindView(R.id.btn_price_shoukuan)
    TextView mBtnPriceShoukuan;
    @BindView(R.id.linearlayout)
    LinearLayout mLinearlayout;
    @BindView(R.id.btn_price_point)
    TextView mBtnPricePoint;
    @BindView(R.id.rl_keyboard)
    RelativeLayout mRlKeyboard;
    @BindView(R.id.tv_withdrawal_amount)
    TextView mTvWithdrawalAmount;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw;
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
//        mEtWithdrawalAmount.setInputType(InputType.TYPE_NULL);
        mEtWithdrawalAmount.setEnabled(false);
        mEtWithdrawalAmount.addTextChangedListener(new TradeTextWatcher(mEtWithdrawalAmount, null));
        TextView[] mBtnkey_digits = new TextView[10];
        for (int i = 0; i < 10; i++) {
            String strid = String.format("btn_price_%d", i);
            mBtnkey_digits[i] = (TextView) findViewById(this
                    .getResources().getIdentifier(strid, "id",
                            this.getPackageName()));
            mBtnkey_digits[i].setOnClickListener(mClickListener);
        }

        mLlHide.setOnClickListener(mClickListener);
        mBtnPricePoint.setOnClickListener(mClickListener);
        mBtnPriceDel.setOnClickListener(mClickListener);
        mBtnPriceShoukuan.setOnClickListener(mClickListener);

    }


    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.btn_price_1
                    || id == R.id.btn_price_2
                    || id == R.id.btn_price_3
                    || id == R.id.btn_price_4
                    || id == R.id.btn_price_5
                    || id == R.id.btn_price_6
                    || id == R.id.btn_price_7
                    || id == R.id.btn_price_8
                    || id == R.id.btn_price_9
                    || id == R.id.btn_price_0) {
                String input = ((TextView) view).getText().toString();
                if (input == null) {
                    mEtWithdrawalAmount.setText(input);
                } else if (input != null) {
                    String strTmp = mEtWithdrawalAmount.getText().toString();
                    strTmp += input;
                    mEtWithdrawalAmount.setText(strTmp);
                }
                mEtWithdrawalAmount.setTextSize(30);
                mEtWithdrawalAmount.setTextColor(Color.BLACK);
            } else if (id == R.id.btn_price_point)//点
            {
                String inputa = ((TextView) view).getText().toString();
                if (inputa == null) {
                    mEtWithdrawalAmount.setText(inputa);
                } else if (inputa != null) {
                    String strTmp = mEtWithdrawalAmount.getText().toString();
                    strTmp += inputa;
                    mEtWithdrawalAmount.setText(strTmp);
                }
                mEtWithdrawalAmount.setTextSize(30);
                mEtWithdrawalAmount.setTextColor(Color.BLACK);
            } else if (id == R.id.btn_price_shoukuan) {//收款
                Toast.makeText(mActivity, "点击了确定", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.btn_price_del) {//清除
                if (mEtWithdrawalAmount.getText().length() > 0) {
                    String strTmp = mEtWithdrawalAmount.getText().toString();
                    strTmp = strTmp.substring(0, strTmp.length() - 1);
                    mEtWithdrawalAmount.setText(strTmp);
                } else {
                    mEtWithdrawalAmount.setText("");
                }
                mEtWithdrawalAmount.setTextSize(30);
                mEtWithdrawalAmount.setTextColor(Color.BLACK);
            } else if (id == R.id.ll_hide) {
                mRlKeyboard.setVisibility(View.GONE);
            }

        }
    };


    @Override
    protected void initView() {
        mTvTitle.setText("提现");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mEtWithdrawalAmount.setOnClickListener(this);
        mTvWithdrawalAmount.setOnClickListener(this);
        mBtnConfirmWithdrawal.setOnClickListener(this);
        mLlBankCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.et_withdrawal_amount:
                break;
            case R.id.btn_confirm_withdrawal:
                break;
            case R.id.tv_withdrawal_amount:
                mRlKeyboard.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_bank_card:
                startActivity(new Intent(mActivity,SelectBankCardActivity.class));
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
