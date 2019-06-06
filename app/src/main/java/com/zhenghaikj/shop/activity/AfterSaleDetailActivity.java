package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.RefundDetailResult;
import com.zhenghaikj.shop.entity.RefundProcessDetailResult;
import com.zhenghaikj.shop.mvp.contract.AfterSaleDetailContract;
import com.zhenghaikj.shop.mvp.model.AfterSaleDetailModel;
import com.zhenghaikj.shop.mvp.presenter.AfterSaleDetailPresenter;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

/*退货单详情页*/
public class AfterSaleDetailActivity extends BaseActivity<AfterSaleDetailPresenter, AfterSaleDetailModel> implements AfterSaleDetailContract.View, View.OnClickListener
{
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

    @BindView(R.id.img_shop)
    ImageView mImgshop;
    @BindView(R.id.tv_shop)
    TextView mTvshop;

    @BindView(R.id.tv_state)
    TextView mTvstate;

    @BindView(R.id.tv_return)
    TextView mTvreturn;

    @BindView(R.id.tv_dispose_time)
    TextView mTvdispose_time;

    @BindView(R.id.tv_type)
    TextView mTvtype;

    @BindView(R.id.tv_return_money)
    TextView mTvreturn_money;

    @BindView(R.id.tv_return_money_reason)
    TextView mTvreturnmoney_reason;

    @BindView(R.id.tv_item_id)
    TextView mTvitemid;

    @BindView(R.id.tv_apply_time)
    TextView mTvapply_time;

    @BindView(R.id.rl_process)
    RelativeLayout mRlprocess;

    private SPUtils spUtils = SPUtils.getInstance("token");
    private String userKey;
    private String Id;
    private String OrderId;
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
        return R.layout.activity_after_sale_detail;
    }

    @Override
    protected void initData() {
        userKey = spUtils.getString("UserKey");
        Id = getIntent().getStringExtra("Id");
        OrderId=getIntent().getStringExtra("OrderId");
        mPresenter.GetRefundDetail(Id, userKey);
        mPresenter.GetOrderDetail(OrderId,userKey);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mRlprocess.setOnClickListener(this);
    }


    @Override
    public void GetRefundDetail(RefundDetailResult result) {
    if (result!=null){
        mTvstate.setText(result.getSellerAuditStatus());
        mTvreturn.setText("退款金额¥:"+result.getAmount()+"");
        mTvdispose_time.setText("处理时间:"+result.getSellerAuditDate());//时间可能不对
        mTvtype.setText(result.getRefundMode());
        mTvreturn_money.setText("¥"+result.getAmount());
        mTvreturnmoney_reason.setText(result.getReason());
        mTvitemid.setText(result.getId());
        mTvapply_time.setText(result.getApplyDate());

    }


    }

    /*获取工单详情*/
    @Override
    public void GetOrderDetail(OrderDetail result) {
     if (result.isSuccess()){
         mTvTitle.setVisibility(View.VISIBLE);
         mTvTitle.setText("详情");
         Glide.with(mActivity)
                 .load(result.getOrderItem().get(0).getProductImage())
                 .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                 .into(mImgshop);

         mTvshop.setText(result.getOrderItem().get(0).getProductName());



     }


    }

    @Override
    public void GetRefundProcessDetail(RefundProcessDetailResult result) {

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
                AfterSaleDetailActivity.this.finish();
                break;
            case R.id.rl_process:
                //获取售后处理进程
                mPresenter.GetRefundProcessDetail(Id,userKey);
                break;

        }
    }
}
