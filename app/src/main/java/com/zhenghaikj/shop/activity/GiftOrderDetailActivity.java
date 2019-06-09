package com.zhenghaikj.shop.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.ConfirmOrderOverResult;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.entity.GiftOrder;
import com.zhenghaikj.shop.entity.GiftOrderDetail;
import com.zhenghaikj.shop.mvp.contract.IntegralOrderContract;
import com.zhenghaikj.shop.mvp.model.IntegralOrderModel;
import com.zhenghaikj.shop.mvp.presenter.IntegralOrderPresenter;
import com.zhenghaikj.shop.utils.GlideUtil;
import com.zhenghaikj.shop.utils.SingleClick;
import com.zhenghaikj.shop.widget.MyImageView;

import butterknife.BindView;

public class GiftOrderDetailActivity extends BaseActivity<IntegralOrderPresenter, IntegralOrderModel> implements View.OnClickListener, IntegralOrderContract.View {


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
    @BindView(R.id.ll_toolbar)
    LinearLayout mLlToolbar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.ll_add_address)
    LinearLayout mLlAddAddress;
    @BindView(R.id.ll_address_choose)
    LinearLayout mLlAddressChoose;
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;
    @BindView(R.id.tv_trading_status)
    TextView mTvTradingStatus;
    @BindView(R.id.ll_store)
    LinearLayout mLlStore;
    @BindView(R.id.iv_img)
    MyImageView mIvImg;
    @BindView(R.id.tv_product_name)
    TextView mTvProductName;
    @BindView(R.id.tv_coin)
    TextView mTvCoin;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.tv_goods_number)
    TextView mTvGoodsNumber;
    @BindView(R.id.tv_goods_price)
    TextView mTvGoodsPrice;
    @BindView(R.id.tv_confirm_receipt)
    TextView mTvConfirmReceipt;
    @BindView(R.id.tv_order_date)
    TextView mTvOrderDate;
    @BindView(R.id.tv_order_wl)
    TextView mTvOrderWl;
    @BindView(R.id.ll_wl)
    LinearLayout mLlWl;
    private SPUtils spUtil;
    private String Userkey;
    private String id;
    private GiftOrderDetail giftOrderDetail;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_gift_order_detail;
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
        mTvTitle.setText("礼品订单详情");
        mTvTitle.setVisibility(View.VISIBLE);
        id = getIntent().getStringExtra("id");
        spUtil = SPUtils.getInstance("token");
        Userkey = spUtil.getString("UserKey");
        mPresenter.GetOrder(id, Userkey);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvConfirmReceipt.setOnClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_confirm_receipt:
                //确认收货
                mPresenter.ConfirmOrderOver(id, Userkey);
                break;
        }
    }



    @Override
    public void GetMyOrderList(GiftOrder Result) {

    }

    @Override
    public void GetOrderCount(GiftDetailResult Result) {

    }

    @Override
    public void ConfirmOrderOver(ConfirmOrderOverResult Result) {
        if (Result.isSuccess()){
            mPresenter.GetOrder(id,Userkey);
        }
        ToastUtils.showShort(Result.getMsg());
    }

    @Override
    public void GetOrder(GiftOrderDetail Result) {
        giftOrderDetail =Result;
        mLlAddAddress.setVisibility(View.GONE);
        mTvName.setText(Result.getShipTo());
        mTvPhone.setText(Result.getCellPhone());
        mTvAddress.setText(Result.getRegionFullName());
        mTvOrderNumber.setText(Result.getId());
        mTvTradingStatus.setText(Result.getShowOrderStatus());
        mTvProductName.setText(Result.getItems().get(0).getGiftName());
        mTvCoin.setText(Result.getItems().get(0).getSaleIntegral()+"西瓜币");
        mTvNum.setText("x"+Result.getItems().get(0).getQuantity());
        mTvGoodsNumber.setText("共"+Result.getItems().get(0).getQuantity()+"件礼品，");
        mTvGoodsPrice.setText("合计："+Result.getTotalIntegral()+"西瓜币");
        mTvOrderDate.setText("兑换时间："+Result.getOrderDate());
        GlideUtil.loadImageViewLoding(mActivity,Result.getItems().get(0).getDefaultImage(),mIvImg,R.drawable.image_loading,R.drawable.image_loading);

        if (Result.getOrderStatus()==3){
            mTvConfirmReceipt.setVisibility(View.VISIBLE);
        }else{
            mTvConfirmReceipt.setVisibility(View.GONE);
        }
        if (Result.getOrderStatus()==5){
            mLlWl.setVisibility(View.VISIBLE);
        }else{
            mLlWl.setVisibility(View.GONE);
        }
        mTvOrderWl.setText("运单号码："+Result.getShipOrderNumber()+"     物流公司："+Result.getExpressCompanyName());
    }
}
