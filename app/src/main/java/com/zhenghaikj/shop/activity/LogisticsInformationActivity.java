package com.zhenghaikj.shop.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.LogisticsAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.ShipmentNumber;
import com.zhenghaikj.shop.mvp.contract.ExpressContract;
import com.zhenghaikj.shop.mvp.model.ExpressModel;
import com.zhenghaikj.shop.mvp.presenter.ExpressPresenter;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogisticsInformationActivity extends BaseActivity<ExpressPresenter, ExpressModel> implements View.OnClickListener, ExpressContract.View {
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
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_logistics_company)
    TextView mTvLogisticsCompany;
    @BindView(R.id.tv_tracking_number)
    TextView mTvTrackingNumber;
    @BindView(R.id.rv_logistics)
    RecyclerView mRvLogistics;
    private String orderId;
    private List<Logistics> logisticsList = new ArrayList<>();
    private String express;
    private ClipData myClip;
    private ClipboardManager myClipboard;

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
        return R.layout.activity_logistics_information;
    }

    @Override
    protected void initData() {
        orderId = getIntent().getStringExtra("orederId");
//        ToastUtils.showShort(orderId);
//        mPresenter.GetExpress(orderId, userKey);
        mPresenter.GetExpressNum(orderId,userKey);
        mPresenter.GetOrders("3", "1", "10", userKey);
        mPresenter.GetOrderDetail(orderId,userKey);
    }

    @Override
    protected void initView() {
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
        mTvTitle.setText("物流详情");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvTrackingNumber.setOnClickListener(this);
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
                finish();
                break;
            case R.id.tv_tracking_number:
                myClip = ClipData.newPlainText("", express);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
                break;
        }
    }

    @Override
    public void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getItem2()!=null){
                    if (baseResult.getData().getItem2().size()!=0){
                        logisticsList.addAll(baseResult.getData().getItem2());
                        LogisticsAdapter logisticsAdapter = new LogisticsAdapter(R.layout.item_logistics, logisticsList);
                        mRvLogistics.setLayoutManager(new LinearLayoutManager(mActivity));
                        mRvLogistics.setAdapter(logisticsAdapter);
                    }
                }

                break;
        }
    }

    @Override
    public void GetOrders(Order result) {
        if (result.isSuccess()) {

        }
    }

    @Override
    public void GetExpress(Express Result) {
        if (Result.isSuccess()) {
            mPresenter.GetExpressInfo(Result.getExpressNum());
            express = Result.getExpressNum();
            mTvLogisticsCompany.setText("物流公司：" + Result.getExpressCompanyName());
            mTvTrackingNumber.setText("物流编号：" + Result.getExpressNum());
        }
    }

    @Override
    public void GetOrderDetail(OrderDetail result) {
        if (result.isSuccess()){
            Glide.with(mActivity).load(result.getOrderItem().get(0).getProductImage())
                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                    .into(mIvGoods);
            mTvAddress.setText("配送至："+result.getOrder().getAddress());
        }
    }

    @Override
    public void GetExpressNum(ShipmentNumber result) {
        if ("true".equals(result.getSuccess())){
            mPresenter.GetExpressInfo(result.getExpressNum());
            mTvLogisticsCompany.setText("物流公司：" + result.getExpressCompany());
            mTvTrackingNumber.setText("物流编号：" + result.getExpressNum());
        }
    }
}
