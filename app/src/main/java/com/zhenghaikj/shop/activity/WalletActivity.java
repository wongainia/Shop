package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.Track;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.MineContract;
import com.zhenghaikj.shop.mvp.model.MineModel;
import com.zhenghaikj.shop.mvp.presenter.MinePresenter;
import com.zhenghaikj.shop.widget.CircleImageView;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends BaseActivity<MinePresenter, MineModel> implements View.OnClickListener, MineContract.View {

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
    private SPUtils spUtils;
    private String userName;
    private UserInfo.UserInfoDean userInfo;

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
        spUtils = SPUtils.getInstance("token");
        userName = spUtils.getString("userName2");
        mPresenter.GetUserInfoList(userName,"1");
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

    @Override
    public void PersonalInformation(PersonalInformation result) {

    }

    @Override
    public void GetHistoryVisite(HistoryVisite result) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                if (Result.getData().getData()==null){

                }else {
                    userInfo = Result.getData().getData().get(0);
                    if (userInfo !=null){
                        mTvBalance.setText(""+userInfo.getTotalMoney()+"");//钱包余额
                        mTvWatermelonBalance.setText("¥"+userInfo.getCon()+"");//西瓜币
                    }
                }


                break;

            default:
                break;

        }
    }

    @Override
    public void GetOrderByhmall(BaseResult<Data<List<WorkOrder.DataBean>>> Result) {

    }

    @Override
    public void EnSureOrder(BaseResult<Data<String>> Result) {

    }

    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {

    }

    @Override
    public void GetOrderByhmalluserid(BaseResult<Data<List<WorkOrder.DataBean>>> baseResult) {

    }

    @Override
    public void GetOrderRecordByOrderID(BaseResult<List<Track>> baseResult) {

    }

    @Override
    public void PressWokerAccount(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult) {

    }

    @Override
    public void GetOrders(Order result) {

    }

    @Override
    public void GetExpress(Express Result) {

    }
}
