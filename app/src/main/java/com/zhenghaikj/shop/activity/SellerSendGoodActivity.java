package com.zhenghaikj.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.RefundApplyResult;
import com.zhenghaikj.shop.entity.RefundDetailResult;
import com.zhenghaikj.shop.entity.RefundProcessDetailResult;
import com.zhenghaikj.shop.mvp.contract.AfterSaleDetailContract;
import com.zhenghaikj.shop.mvp.model.AfterSaleDetailModel;
import com.zhenghaikj.shop.mvp.presenter.AfterSaleDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellerSendGoodActivity extends BaseActivity<AfterSaleDetailPresenter, AfterSaleDetailModel> implements View.OnClickListener, AfterSaleDetailContract.View {


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
    @BindView(R.id.et_shipordernumber)
    EditText mEtShipordernumber;
    @BindView(R.id.img_scan)
    ImageView mImgScan;
    @BindView(R.id.et_conpanyname)
    EditText mEtConpanyname;
    @BindView(R.id.tv_sumbit)
    TextView mTvSumbit;



    private String Id;
    @Override
    public void onClick(View v) {
     switch (v.getId()){
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
           if ("".equals(mEtShipordernumber.getText().toString())||"".equals(mEtConpanyname.getText().toString())){
            Toast.makeText(mActivity,"请输入快递信息",Toast.LENGTH_SHORT).show();
            return;
           }else {
               mPresenter.PostSellerSendGoods(Id,mEtConpanyname.getText().toString(),mEtShipordernumber.getText().toString(),userKey);
           }

             break;
}
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
    protected int setLayoutId() {
        return R.layout.activity_sellersendgood;
    }

    @Override
    protected void initData() {
    Id=getIntent().getStringExtra("Id");


    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgScan.setOnClickListener(this);
        mTvSumbit.setOnClickListener(this);
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

    }

    @Override
    public void GetRefundProcessDetail(RefundProcessDetailResult result) {

    }

    @Override
    public void PostSellerSendGoods(RefundApplyResult result) {

        if (result.isSuccess()){
            Toast.makeText(mActivity,"提交成功",Toast.LENGTH_SHORT).show();
            SellerSendGoodActivity.this.finish();
        }

    }
}
