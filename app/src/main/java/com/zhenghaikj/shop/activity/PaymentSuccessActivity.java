package com.zhenghaikj.shop.activity;
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
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.PaymentSuccessAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.mvp.contract.PaymentSuccessContract;
import com.zhenghaikj.shop.mvp.model.PaymentSuccessModel;
import com.zhenghaikj.shop.mvp.presenter.PaymentSuccessPresenter;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentSuccessActivity extends BaseActivity<PaymentSuccessPresenter, PaymentSuccessModel> implements View.OnClickListener, PaymentSuccessContract.View {

    private SPUtils spUtils=SPUtils.getInstance("token");
    private String userKey;

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


    private PaymentSuccessAdapter paymentSuccessAdapter;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_paymentsuccess;
    }

    @Override
    protected void initData() {
        userKey=spUtils.getString("UserKey");
        String orderID = getIntent().getStringExtra("OrderID");
        Log.d("======>orderID",orderID);
        mPresenter.GetOrderDetail(orderID,userKey);

    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
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
            PaymentSuccessActivity.this.finish();
            break;

        }
    }

    @Override
    public void GetOrderDetail(OrderDetail result) {
     if (result.isSuccess()){

         mTvMoney.setText("成功付款:¥"+result.getOrder().getRealTotalAmount());

         Glide.with(mActivity).load(result.getOrderItem().get(0).getProductImage())
                 .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                 .into(mImgShop);

         mTvShop.setText(result.getOrderItem().get(0).getProductName());



     }
    }
}
