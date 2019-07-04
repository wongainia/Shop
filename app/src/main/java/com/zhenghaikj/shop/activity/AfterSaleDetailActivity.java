package com.zhenghaikj.shop.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.android.tpush.service.gdb.ToolService;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
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

/*退货单详情页*/
public class AfterSaleDetailActivity extends BaseActivity<AfterSaleDetailPresenter, AfterSaleDetailModel> implements AfterSaleDetailContract.View, View.OnClickListener {
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


    @BindView(R.id.tv_sendgood)
    TextView mTvsendgood;
    @BindView(R.id.ll_toolbar)
    LinearLayout mLlToolbar;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_process)
    TextView mTvProcess;
    @BindView(R.id.tv_shouhoulaixing)
    TextView mTvShouhoulaixing;
    @BindView(R.id.tv_tuikuanjine)
    TextView mTvTuikuanjine;
    @BindView(R.id.tv_tuikuanreason)
    TextView mTvTuikuanreason;
    @BindView(R.id.tv_shouhouid)
    TextView mTvShouhouid;
    @BindView(R.id.tv_shenqingshijian)
    TextView mTvShenqingshijian;
    @BindView(R.id.tv_platform_intervention)
    TextView mTvPlatformIntervention;


    private String Id;
    private String OrderId;
    private OrderDetail Ordertail;
    private RefundDetailResult returnResult;
    private String userId;
    private AlertDialog dialog;

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
        userId = spUtils.getString("userName");
        Id = getIntent().getStringExtra("Id");
        OrderId = getIntent().getStringExtra("OrderId");
        mPresenter.GetRefundDetail(Id, userKey);
        mPresenter.GetOrderDetail(OrderId, userKey);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mRlprocess.setOnClickListener(this);
        mTvsendgood.setOnClickListener(this);
        mTvPlatformIntervention.setOnClickListener(this);
    }


    @Override
    public void GetRefundDetail(RefundDetailResult result) {
        if (result != null) {
            returnResult = result;
            mTvstate.setText(result.getSellerAuditStatus());

            mTvreturn.setText("退款金额¥:" + result.getAmount() + "");
            mTvdispose_time.setText("处理时间:" + result.getSellerAuditDate());//时间可能不对
            mTvtype.setText(result.getRefundMode());
            mTvreturn_money.setText("¥" + result.getAmount());
            mTvreturnmoney_reason.setText(result.getReason());
            mTvitemid.setText(result.getId());
            mTvapply_time.setText(result.getApplyDate());

            if (result.getSellerAuditStatusValue() == 2) {
                mTvsendgood.setVisibility(View.VISIBLE);
            }else if (result.getSellerAuditStatusValue()==4){
                mTvPlatformIntervention.setVisibility(View.VISIBLE);
                mTvstate.setText(result.getSellerAuditStatus() + ":" + result.getSellerRemark());
            }

        }


    }

    /*获取工单详情*/
    @Override
    public void GetOrderDetail(OrderDetail result) {
        if (result.isSuccess()) {
            Ordertail=result;
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
    public void PostSellerSendGoods(RefundApplyResult result) {

    }

    @Override
    public void PostOrderComplaint(PostOrderComplaint result) {

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
                AfterSaleDetailActivity.this.finish();
                break;
            case R.id.rl_process:
                //获取售后处理进程
                mPresenter.GetRefundProcessDetail(Id, userKey);
                break;
            case R.id.tv_sendgood:
                //mPresenter.PostSellerSendGoods(Id,"顺丰快递","121214124142",userKey);
                Intent intent = new Intent(mActivity, SellerSendGoodActivity.class);
                intent.putExtra("Id", Id);
                startActivity(intent);
                break;
            case R.id.tv_platform_intervention:
                showComplaint();

                break;


        }
    }

    private void showComplaint() {
        View view= LayoutInflater.from(mActivity).inflate(R.layout.dialog_complaint, null);
        EditText et_memo=view.findViewById(R.id.et_memo);
        TextView negtive=view.findViewById(R.id.negtive);
        TextView positive=view.findViewById(R.id.positive);

        negtive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason=et_memo.getText().toString();
                if ("".equals(reason)||reason==null){
                    ToastUtils.showShort("请输入投诉原因");
                }else {
                    mPresenter.PostOrderComplaint(userKey,Ordertail.getOrder().getShopId(),OrderId,reason,userId);
                    dialog.dismiss();
                }
            }
        });
        dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        dialog.show();
        Window window= dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());
    }
}
