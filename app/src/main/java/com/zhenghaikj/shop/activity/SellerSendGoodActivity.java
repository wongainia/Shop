package com.zhenghaikj.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.ComplaintRecord;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.PostOrderComplaint;
import com.zhenghaikj.shop.entity.RefundApplyResult;
import com.zhenghaikj.shop.entity.RefundDetailResult;
import com.zhenghaikj.shop.entity.RefundProcessDetailResult;
import com.zhenghaikj.shop.mvp.contract.AfterSaleDetailContract;
import com.zhenghaikj.shop.mvp.model.AfterSaleDetailModel;
import com.zhenghaikj.shop.mvp.presenter.AfterSaleDetailPresenter;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellerSendGoodActivity extends BaseActivity<AfterSaleDetailPresenter, AfterSaleDetailModel> implements View.OnClickListener, AfterSaleDetailContract.View, AdapterView.OnItemSelectedListener {


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
    @BindView(R.id.et_shipordernumber)
    EditText mEtShipordernumber;
    @BindView(R.id.img_scan)
    ImageView mImgScan;
    @BindView(R.id.et_conpanyname)
    TextView mEtConpanyname;
    @BindView(R.id.tv_sumbit)
    TextView mTvSumbit;
    @BindView(R.id.img_shop)
    ImageView mImgShop;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.tv_specification)
    TextView mTvSpecification;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.ll_choose)
    LinearLayout mLlChoose;
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;


    private String Id;
    private String orderId;
    private String message;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.img_scan:
                IntentIntegrator integrator = new IntentIntegrator((Activity) mActivity);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                integrator.setPrompt("请扫描快递条形码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            case R.id.tv_sumbit:
                if ("".equals(mEtShipordernumber.getText().toString())) {
                    Toast.makeText(mActivity, "请输入快递单号", Toast.LENGTH_SHORT).show();
                    return;
                } else if ("请选择快递公司".equals(message)) {
                    Toast.makeText(mActivity, "请选择快递公司", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mPresenter.PostSellerSendGoods(Id, message, mEtShipordernumber.getText().toString(), userKey);
//                    ToastUtils.showShort(message);
                }

                break;
            case R.id.ll_choose:

                break;
        }
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
    protected int setLayoutId() {
        return R.layout.activity_sellersendgood;
    }

    @Override
    protected void initData() {
        Id = getIntent().getStringExtra("Id");
        orderId = getIntent().getStringExtra("OrderId");
        mPresenter.GetOrderDetail(orderId, userKey);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("填写退货物流");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mImgScan.setOnClickListener(this);
        mTvSumbit.setOnClickListener(this);
        mLlChoose.setOnClickListener(this);
        mIconBack.setOnClickListener(this);
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            mEtShipordernumber.setText(result);
        }
    }


    @Override
    public void GetRefundDetail(RefundDetailResult result) {

    }

    @Override
    public void GetOrderDetail(OrderDetail result) {
        Glide.with(mActivity)
                .load(result.getOrderItem().get(0).getProductImage())
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                .into(mImgShop);
        mTvShop.setText(result.getOrderItem().get(0).getProductName());
        mTvPhone.setText(result.getOrder().getPhone());
        String type = "";
        if (result.getOrderItem().get(0).getSize() != null || result.getOrderItem().get(0).getColor() != null || result.getOrderItem().get(0).getVersion() != null) {

            mTvSpecification.setVisibility(View.VISIBLE);
            if (result.getOrderItem().get(0).getColor() != null) {
                type = result.getOrderItem().get(0).getColor();
                mTvSpecification.setText(type);
            }
            if (result.getOrderItem().get(0).getSize() != null) {
                type = type + " " + result.getOrderItem().get(0).getSize();
                mTvSpecification.setText(type);
            }
            if (result.getOrderItem().get(0).getVersion() != null) {
                type = type + " " + result.getOrderItem().get(0).getVersion();
                mTvSpecification.setText(type);
            }

        }
    }

    @Override
    public void GetRefundProcessDetail(RefundProcessDetailResult result) {

    }

    @Override
    public void PostSellerSendGoods(RefundApplyResult result) {

        if (result.isSuccess()) {
            Toast.makeText(mActivity, "提交成功", Toast.LENGTH_SHORT).show();
            SellerSendGoodActivity.this.finish();
        }

    }

    @Override
    public void PostOrderComplaint(PostOrderComplaint result) {

    }

    @Override
    public void ApplyArbitration(PostOrderComplaint result) {

    }

    @Override
    public void GetRecord(ComplaintRecord result) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner:
                String[] letter = getResources().getStringArray(R.array.letter);
//                Log.i("spinner1点击------",letter[position]);
                switch (position) {
                    case 0:
                        message = "请选择快递公司";
                        break;
                    case 1:
                        message = "邮政EMS";
                        break;
                    case 2:
                        message = "申通快递";
                        break;
                    case 3:
                        message = "顺丰快递";
                        break;
                    case 4:
                        message = "天天快递";
                        break;
                    case 5:
                        message = "圆通快递";
                        break;
                    case 6:
                        message = "韵达快递";
                        break;
                    case 7:
                        message = "宅急送";
                        break;
                    case 8:
                        message = "中通速递";
                        break;
                    case 9:
                        message = "邮政平邮";
                        break;
                    case 10:
                        message = "其他";
                        break;

                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
