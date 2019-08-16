package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.WithdrawContract;
import com.zhenghaikj.shop.mvp.model.WithdrawModel;
import com.zhenghaikj.shop.mvp.presenter.WithdrawPresenter;
import com.zhenghaikj.shop.widget.TradeTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WithdrawActivity extends BaseActivity<WithdrawPresenter, WithdrawModel> implements View.OnClickListener, WithdrawContract.View {

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
    @BindView(R.id.ll_toolbar)
    LinearLayout mLlToolbar;
    @BindView(R.id.ll_bank_card_one)
    LinearLayout mLlBankCardOne;
    private String endNum;
    private String bankNo;
    private UserInfo.UserInfoDean userInfoDeanrInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw;
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
    protected void initData() {
        mPresenter.GetUserInfoList(UserID, "1");
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
//                Toast.makeText(mActivity, "点击了确定", Toast.LENGTH_SHORT).show();
                Double money=Double.parseDouble(mEtWithdrawalAmount.getText().toString());
                if (bankNo==null||"".equals(bankNo)){
                    ToastUtils.showShort("请选择银行卡");
                } else if (money==null||"".equals(money)||money==0) {
                    ToastUtils.showShort("请输入提现金额");
                }else if (money>userInfoDeanrInfo.getTotalMoney()-userInfoDeanrInfo.getFrozenMoney()){
                    ToastUtils.showShort("超出可提现金额");
                } else {
                    mPresenter.WithDraw(String.valueOf(money),bankNo,UserID);
                }
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
        mLlBankCardOne.setOnClickListener(this);
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
                Double money=Double.parseDouble(mEtWithdrawalAmount.getText().toString());
                if (bankNo==null||"".equals(bankNo)){
                    ToastUtils.showShort("请选择银行卡");
                } else if (money==null||"".equals(money)||money==0) {
                    ToastUtils.showShort("请输入提现金额");
                }else if (money>userInfoDeanrInfo.getTotalMoney()-userInfoDeanrInfo.getFrozenMoney()){
                    ToastUtils.showShort("超出可提现金额");
                } else {
                    mPresenter.WithDraw(String.valueOf(money),bankNo,UserID);
                }
                break;
            case R.id.tv_withdrawal_amount:
                mRlKeyboard.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_bank_card_one:
            case R.id.ll_bank_card:
                startActivityForResult(new Intent(mActivity, BrandCardActivity.class), 2000);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2000:
                String bankName = data.getStringExtra("bankName");
                bankNo = data.getStringExtra("bankNo");
//                ToastUtils.showShort(bankNo);
                int length = bankNo.length();
                if (length > 4) {
                    endNum = bankNo.substring(length - 4, length);
                }
                mLlBankCard.setVisibility(View.VISIBLE);
                mLlBankCardOne.setVisibility(View.GONE);
                mTvBankName.setText(bankName);
                mTvTailNumber.setText(endNum);
                switch (bankName) {
                    case "光大银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.gaungda)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "广发银行股份有限公司":
                        Glide.with(mActivity)
                                .load(R.mipmap.gaungfa)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "工商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.gongshang)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国工商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.gongshang)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "华夏银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.huaxia)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;

                    case "中国建设银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.jianshe)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "建设银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.jianshe)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国交通银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.jiaotong)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "民生银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.minsheng)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "宁波银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.ningbo)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "农业银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.nongye)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国农业银行贷记卡":
                        Glide.with(mActivity)
                                .load(R.mipmap.nongye)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "浦发银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.pufa)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "兴业银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.xinye)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "邮政储蓄银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.youzheng)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "邮储银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.youzheng)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "招商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.zhaoshan)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);

                        break;
                    case "浙商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.zheshang)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国银行":

                        Glide.with(mActivity)
                                .load(R.mipmap.zhongguo)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中信银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.zhongxin)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;

                    default:
                        break;

                }
        }
    }

    @Override
    public void WithDraw(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                ToastUtils.showShort(baseResult.getData().getItem2());
                finish();
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {
        switch (Result.getStatusCode()){
            case 200:
                userInfoDeanrInfo = Result.getData().getData().get(0);

                break;
        }
    }
}
