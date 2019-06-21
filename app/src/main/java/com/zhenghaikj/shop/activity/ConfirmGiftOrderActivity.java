package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.ConfirmOrderOverResult;
import com.zhenghaikj.shop.entity.GiftConfirmOrder;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.mvp.contract.GiftConfirmOrderContract;
import com.zhenghaikj.shop.mvp.model.GiftConfirmOrderModel;
import com.zhenghaikj.shop.mvp.presenter.GiftConfirmOrderPresenter;
import com.zhenghaikj.shop.utils.GlideUtil;
import com.zhenghaikj.shop.utils.SingleClick;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmGiftOrderActivity extends BaseActivity<GiftConfirmOrderPresenter, GiftConfirmOrderModel> implements View.OnClickListener, GiftConfirmOrderContract.View{


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
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.ll_add_address)
    LinearLayout mLlAddAddress;
    @BindView(R.id.ll_address_choose)
    LinearLayout mLlAddressChoose;
    @BindView(R.id.tv_product_name)
    TextView mTvProductName;
    @BindView(R.id.tv_coin)
    TextView mTvCoin;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.tv_totalmoney)
    TextView mTvTotalmoney;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    private ShippingAddressList.ShippingAddressBean address;
    private String addressid;
    private String id;
    private String count;
    private GiftConfirmOrder giftConfirmOrder;
    private String giftId;
    private String regionId;
    private Intent intent;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_gift_confirm_order;
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
        mTvTitle.setText("确认订单");
        mTvTitle.setVisibility(View.VISIBLE);
        id =getIntent().getStringExtra("id");
        count =getIntent().getStringExtra("count");
        mPresenter.ConfirmOrder(id,count,userKey);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlAddressChoose.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_address_choose:
                Intent intent = new Intent(mActivity, ShippingAddressActivity.class);
                intent.putExtra("CHOOSE_ADDRESS_REQUEST", true);
                startActivityForResult(intent, Config.CHOOSE_ADDRESS_REQUEST);
                break;
            case R.id.tv_submit:
                giftId =Integer.toString(giftConfirmOrder.getGiftList().get(0).getGiftId());
                mPresenter.SubmitOrder(giftId,count, addressid,userKey);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*处理选择的地址*/
        if (resultCode == Config.CHOOSE_ADDRESS_RESULT) {
            if (requestCode == Config.CHOOSE_ADDRESS_REQUEST) {

                address = (ShippingAddressList.ShippingAddressBean) data.getSerializableExtra("Address");
                if (address != null) {
//                    regionId=address.getRegionId();
                    addressid = address.getId();
                    mTvName.setText(address.getShipTo());
                    mTvPhone.setText(address.getPhone());
                    mTvAddress.setText(address.getRegionFullName() + " " + address.getAddress());
                    mLlAddress.setVisibility(View.VISIBLE);
                    mLlAddAddress.setVisibility(View.GONE);
                } else {
                    mLlAddress.setVisibility(View.GONE);
                    mLlAddAddress.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void ConfirmOrder(GiftConfirmOrder result) {
        giftConfirmOrder =result;
        if (result.getShipAddress() != null) {
//            regionId=Integer.toString(result.getShipAddress().getRegionId());
            addressid= String.valueOf(result.getShipAddress().getId());
            mTvName.setText(result.getShipAddress().getShipTo());
            mTvPhone.setText(result.getShipAddress().getPhone());
            mTvAddress.setText(result.getShipAddress().getAddress());
            mLlAddress.setVisibility(View.VISIBLE);
            mLlAddAddress.setVisibility(View.GONE);
        } else {
            mLlAddress.setVisibility(View.GONE);
            mLlAddAddress.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < result.getGiftList().size(); i++) {
            GiftConfirmOrder.GiftListBean giftListBean=result.getGiftList().get(i);
            GlideUtil.loadImageViewLoding(mActivity,giftListBean.getDefaultImage(),mIvImg,R.drawable.image_loading,R.drawable.image_loading);
            mTvProductName.setText(giftListBean.getGiftName());
            mTvCoin.setText(giftListBean.getSaleIntegral()+"西瓜币");
            mTvTotalmoney.setText("兑换"+giftListBean.getQuantity()+"件礼品，"+giftConfirmOrder.getTotalAmount()+"西瓜币");
            mTvNum.setText("x"+giftListBean.getQuantity());
        }
    }

    @Override
    public void SubmitOrder(ConfirmOrderOverResult result) {
        if (result.isSuccess()){
            EventBus.getDefault().post("GiftDetail");
            ToastUtils.showShort("恭喜，礼品兑换成功！");
            intent = new Intent(mActivity, IntegralOrderActivity.class);
            intent.putExtra("intent", "待发货");
            startActivity(intent);
            finish();
        }
    }
}
