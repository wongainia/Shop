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
    LinearLayout mTvsendgood;
    //    @BindView(R.id.ll_toolbar)
//    LinearLayout mLlToolbar;
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
    @BindView(R.id.tv_appeal)
    TextView mTvAppeal;
    @BindView(R.id.tv_merchant_complaint)
    TextView mTvMerchantComplaint;
    @BindView(R.id.tv_complaint)
    TextView mTvComplaint;
    @BindView(R.id.rl_complaint)
    RelativeLayout mRlComplaint;
    @BindView(R.id.tv_specification)
    TextView mTvSpecification;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_status_two)
    TextView mTvStatusTwo;
    @BindView(R.id.iv_location)
    ImageView mIvLocation;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.tv_reason)
    TextView mTvReason;
    @BindView(R.id.ll_refuse)
    LinearLayout mLlRefuse;
    @BindView(R.id.tv_return_type)
    TextView mTvReturnType;
    @BindView(R.id.tv_dispose_time_type)
    TextView mTvDisposeTimeType;
    @BindView(R.id.tv_appeal_reason)
    TextView mTvAppealReason;
    @BindView(R.id.ll_appeal)
    LinearLayout mLlAppeal;


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
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
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
        mPresenter.GetRecord(userKey, "10", "1");
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
        mTvAppeal.setOnClickListener(this);
    }


    @Override
    public void GetRefundDetail(RefundDetailResult result) {
        if (result != null) {
            returnResult = result;
            mTvstate.setText(result.getSellerAuditStatus());

            mTvreturn.setText("¥" + result.getAmount() + "");
            StringBuilder stringBuilder = new StringBuilder(result.getSellerAuditDate());
            String time = "" + stringBuilder.replace(10, 11, " "); //替换"T"为" "
            mTvdispose_time.setText("" + time);//时间可能不对
            mTvtype.setText(result.getRefundMode());
            mTvreturn_money.setText("¥" + result.getAmount());
            mTvreturnmoney_reason.setText(result.getReason());
            mTvitemid.setText(result.getId());
            StringBuilder stringBuilder1 = new StringBuilder(result.getApplyDate());
            String time1 = "" + stringBuilder1.replace(10, 11, " "); //替换"T"为" "
            mTvapply_time.setText(time1);
            if (result.getSellerAuditStatusValue() == 1) {
                mTvStatus.setText("待商家确认");
                mTvStatusTwo.setText("请等待商家确认");
            } else if (result.getSellerAuditStatusValue() == 2) {
                mTvsendgood.setVisibility(View.VISIBLE);
                mTvStatus.setText("商家已同意退款退货");
                mTvStatusTwo.setText("请退货并填写物流信息");
                mLlAddress.setVisibility(View.VISIBLE);
            } else if (result.getSellerAuditStatusValue() == 3) {
                mTvStatus.setText("等待商家收货");
                mTvStatusTwo.setText("请等待商家确认收货");
            } else if (result.getSellerAuditStatusValue() == 4) {
                mTvAppeal.setVisibility(View.VISIBLE);
                mTvstate.setText(result.getSellerAuditStatus() + ":" + result.getSellerRemark());
                mTvStatus.setText("商家已拒绝");
                mTvStatusTwo.setText("请进行申诉行为");
                mLlReturn.setVisibility(View.GONE);
                mLlRefuse.setVisibility(View.VISIBLE);
                mLlAppeal.setVisibility(View.GONE);
                mTvReason.setText(result.getSellerRemark());
            } else if (result.getSellerAuditStatusValue() == 5) {
                mTvStatus.setText("商家通过审核");
                mTvStatusTwo.setText("商家通过审核");
            }

        }


    }

    /*获取工单详情*/
    @Override
    public void GetOrderDetail(OrderDetail result) {
        if (result.isSuccess()) {
            Ordertail = result;
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText("退款详情");
            Glide.with(mActivity)
                    .load(result.getOrderItem().get(0).getProductImage())
                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                    .into(mImgshop);
            mTvshop.setText(result.getOrderItem().get(0).getProductName());
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


    }

    @Override
    public void GetRefundProcessDetail(RefundProcessDetailResult result) {

    }

    @Override
    public void PostSellerSendGoods(RefundApplyResult result) {

    }

    @Override
    public void PostOrderComplaint(PostOrderComplaint result) {
        if (result.getSuccess()) {
            ToastUtils.showShort(result.getMsg());
            dialog.dismiss();
        } else {
            ToastUtils.showShort(result.getMsg());
        }
    }

    @Override
    public void ApplyArbitration(PostOrderComplaint result) {
        if (result.getSuccess()) {
            ToastUtils.showShort(result.getMsg());
        } else {
            ToastUtils.showShort(result.getMsg());
        }
    }

    @Override
    public void GetRecord(ComplaintRecord result) {
        for (int i = 0; i < result.getResult().size(); i++) {
            if (OrderId.equals(result.getResult().get(i).getOrderId())) {
//                mRlComplaint.setVisibility(View.VISIBLE);
                mTvComplaint.setText(result.getResult().get(i).getSellerReply());
                mTvAppeal.setVisibility(View.GONE);
//                mTvPlatformIntervention.setVisibility(View.VISIBLE);
                mLlAppeal.setVisibility(View.VISIBLE);
                mTvAppealReason.setText(result.getResult().get(i).getSellerReply());
                return;
            } else {
                mRlComplaint.setVisibility(View.GONE);
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
                intent.putExtra("OrderId", OrderId);
                startActivity(intent);
                break;
            case R.id.tv_platform_intervention:
                mPresenter.ApplyArbitration(userKey, OrderId);
                break;
            case R.id.tv_appeal:
                showComplaint();
                break;


        }
    }

    private void showComplaint() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_complaint, null);
        EditText et_memo = view.findViewById(R.id.et_memo);
        TextView negtive = view.findViewById(R.id.negtive);
        TextView positive = view.findViewById(R.id.positive);

        negtive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = et_memo.getText().toString();
                if ("".equals(reason) || reason == null) {
                    ToastUtils.showShort("请输入投诉原因");
                } else {
                    mPresenter.PostOrderComplaint(userKey, Ordertail.getOrder().getShopId(), OrderId, reason, userId);

                }
            }
        });
        dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());
    }
}
