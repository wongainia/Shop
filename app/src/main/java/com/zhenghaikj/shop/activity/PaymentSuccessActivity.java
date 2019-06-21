package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.PaymentSuccessAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.mvp.contract.PaymentSuccessContract;
import com.zhenghaikj.shop.mvp.model.PaymentSuccessModel;
import com.zhenghaikj.shop.mvp.presenter.PaymentSuccessPresenter;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentSuccessActivity extends BaseActivity<PaymentSuccessPresenter, PaymentSuccessModel> implements View.OnClickListener, PaymentSuccessContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.img_shifu1)
    ImageView mImgShifu1;
    @BindView(R.id.img_shifu2)
    ImageView mImgShifu2;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.tv_check_order)
    TextView mTvCheckOrder;
    @BindView(R.id.tv_return_order)
    TextView mTvReturnOrder;
    private String orderID;

    @BindView(R.id.tv_count)
    TextView mTvcount;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.ll_success)
    LinearLayout mLlSuccess;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.img_shop)
    ImageView mImgShop;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.rl_shop)
    RelativeLayout mRlShop;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_yuyue)
    TextView mTvyuyue;

    private PaymentSuccessAdapter paymentSuccessAdapter;

    /**
     * 初始化沉浸式
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_paymentsuccess;
    }

    @Override
    protected void initData() {
        orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderDetail(orderID, userKey);

    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("支付完成");

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvyuyue.setOnClickListener(this);
        mTvCheckOrder.setOnClickListener(this);
        mTvReturnOrder.setOnClickListener(this);
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
                PaymentSuccessActivity.this.finish();
                break;
            case R.id.tv_yuyue:
                Intent intent = new Intent(mActivity, OrderInstallActivity.class);
                intent.putExtra("OrderId", orderID);
                startActivityForResult(intent, Config.RECEIPT_REQUEST);
                break;
            case R.id.tv_check_order:
                Intent intent1 = new Intent(mActivity, OrderDetailActivity.class);
                intent1.putExtra("orderId", orderID);
                startActivity(intent1);
                finish();
                break;
            case R.id.tv_return_order:
                Bundle bundle = new Bundle();
                bundle.putString("intent", "待发货");
                bundle.putInt("position", 2);
                intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                finish();
                break;


        }
    }

    @Override
    public void GetOrderDetail(OrderDetail result) {
        if (result.isSuccess()) {

            mTvMoney.setText("¥" + result.getOrder().getRealTotalAmount());

            Glide.with(mActivity).load(result.getOrderItem().get(0).getProductImage())
                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                    .into(mImgShop);

            mTvShop.setText(result.getOrderItem().get(0).getProductName());

            mTvcount.setText("数量: " + result.getOrderItem().get(0).getCount());
        }
    }

    @Override
    public void IsMallid(BaseResult<Data<String>> baseResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.RECEIPT_REQUEST) {
            if (resultCode == Config.RECEIPT_RESULT) {
                mTvyuyue.setText("已预约");
                mTvyuyue.setClickable(false);

            }
        }


    }
}
