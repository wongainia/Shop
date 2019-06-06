package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
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

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliverySuccessActivity extends BaseActivity<PaymentSuccessPresenter, PaymentSuccessModel> implements View.OnClickListener, PaymentSuccessContract.View {

    private SPUtils spUtils=SPUtils.getInstance("token");
    private String userKey;
    private String orderID;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.ll_success)
    LinearLayout mLlSuccess;
    @BindView(R.id.img_shop)
    ImageView mImgShop;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_pingjia)
    TextView mTvpingjia;
    @BindView(R.id.tv_pingjia_txt)
    TextView mTvpingjia_txt;
    @BindView(R.id.tv_yuyue)
    TextView mTvyuyue;
    @BindView(R.id.view)
    View mView;

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
        return R.layout.activity_deliverysuccess;
    }

    @Override
    protected void initData() {
        userKey=spUtils.getString("UserKey");
        orderID = getIntent().getStringExtra("OrderID");
        Log.d("======>orderID",orderID);
        mPresenter.GetOrderDetail(orderID,userKey);
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvpingjia.setOnClickListener(this);
        mTvyuyue.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.icon_back:
            DeliverySuccessActivity.this.finish();
              break;
            case R.id.tv_yuyue:
                Intent intent0=new Intent(mActivity,OrderInstallActivity.class);
                intent0.putExtra("OrderId",orderID);
                startActivityForResult(intent0,Config.RECEIPT_REQUEST);
            break;
            case R.id.tv_pingjia:
                Intent intent=new Intent(mActivity,EvaluateActivity.class);
                intent.putExtra("OrderID",orderID);
                startActivityForResult(intent,400);
                break;



        }
    }

    @Override
    public void GetOrderDetail(OrderDetail result) {
     if (result.isSuccess()){
         Glide.with(mActivity).load(result.getOrderItem().get(0).getProductImage())
                 .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                 .into(mImgShop);
         mTvShop.setText(result.getOrderItem().get(0).getProductName());

     }
    }

    @Override
    public void IsMallid(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (!baseResult.getData().isItem1()){
                    mTvyuyue.setText("已预约");
                    mTvyuyue.setClickable(false);
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==400){ //评价请求码
            if (resultCode==401){ //评价返回码
                mTvpingjia_txt.setText("已评价");
                mTvpingjia.setVisibility(View.GONE);
            }

        }

        if (requestCode== Config.RECEIPT_REQUEST){
            if (resultCode==Config.RECEIPT_RESULT){
                mTvyuyue.setText("已预约");
                mTvyuyue.setClickable(false);
            }
        }

    }


}
