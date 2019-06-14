package com.zhenghaikj.shop.fragment.WorkOrder;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.zxing.integration.android.IntentIntegrator;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxui.view.dialog.RxDialogScaleView;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.ScanActivity;
import com.zhenghaikj.shop.adapter.AccessoryDetailAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.entity.Address;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.WorkOrdersDetailContract;
import com.zhenghaikj.shop.mvp.model.WorkOrdersDetailModel;
import com.zhenghaikj.shop.mvp.presenter.WorkOrdersDetailPresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.widget.StarBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderDetailFragment extends BaseLazyFragment<WorkOrdersDetailPresenter, WorkOrdersDetailModel> implements View.OnClickListener, WorkOrdersDetailContract.View {

    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "OrderDetailFragment";
    @BindView(R.id.tv_beyond_money)
    TextView mTvBeyondMoney;
    @BindView(R.id.tv_accessory_money)
    TextView mTvAccessoryMoney;
    @BindView(R.id.tv_service_money)
    TextView mTvServiceMoney;
    @BindView(R.id.tv_order_money)
    TextView mTvOrderMoney;
    @BindView(R.id.tv_post_money)
    TextView mTvPostMoney;
    @BindView(R.id.ll_post_money)
    LinearLayout mLlPostMoney;
    @BindView(R.id.tv_send_accessory)
    TextView mTvSendAccessory;
    @BindView(R.id.ll_send_accessory)
    LinearLayout mLlSendAccessory;

    private String mParam1;
    private String mParam2;
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
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.ll_contact_customer_service)
    LinearLayout mLlContactCustomerService;
    @BindView(R.id.tv_work_order_status)
    TextView mTvWorkOrderStatus;
    @BindView(R.id.tv_work_order_number)
    TextView mTvWorkOrderNumber;
    @BindView(R.id.tv_warranty_type)
    TextView mTvWarrantyType;
    @BindView(R.id.tv_work_order_type)
    TextView mTvWorkOrderType;
    @BindView(R.id.tv_recovery_time)
    TextView mTvRecoveryTime;
    @BindView(R.id.tv_estimated_recycling_time)
    TextView mTvEstimatedRecyclingTime;
    @BindView(R.id.tv_sent_out_accessories)
    TextView mTvSentOutAccessories;
    @BindView(R.id.tv_brand)
    TextView mTvBrand;
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.tv_model)
    TextView mTvModel;
    @BindView(R.id.tv_specify_door_to_door_time)
    TextView mTvSpecifyDoorToDoorTime;
    @BindView(R.id.tv_order_source)
    TextView mTvOrderSource;
    @BindView(R.id.tv_third_party)
    TextView mTvThirdParty;
    @BindView(R.id.tv_fault_description)
    TextView mTvFaultDescription;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.tv_reject)
    TextView mTvReject;
    @BindView(R.id.tv_pass)
    TextView mTvPass;
    @BindView(R.id.rv_accessories)
    RecyclerView mRvAccessories;
    @BindView(R.id.ll_approve_beyond_money)
    LinearLayout mLlApproveBeyondMoney;
    @BindView(R.id.tv_review_accessories)
    TextView mTvReviewAccessories;
    @BindView(R.id.tv_audit_service)
    TextView mTvAuditService;
    @BindView(R.id.tv_reject_service)
    TextView mTvRejectService;
    @BindView(R.id.tv_pass_service)
    TextView mTvPassService;
    @BindView(R.id.rv_service)
    RecyclerView mRvService;
    @BindView(R.id.ll_audit_service)
    LinearLayout mLlAuditService;
    @BindView(R.id.tv_status_accessory)
    TextView mTvStatusAccessory;
    @BindView(R.id.tv_status_service)
    TextView mTvStatusService;
    @BindView(R.id.tv_order_state)
    TextView mTvOrderState;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_accessory_memo)
    TextView mTvAccessoryMemo;
    @BindView(R.id.tv_accessory_sequency)
    TextView mTvAccessorySequency;
    @BindView(R.id.ll_approve_accessory)
    LinearLayout mLlApproveAccessory;
    @BindView(R.id.tv_reject_beyond)
    TextView mTvRejectBeyond;
    @BindView(R.id.tv_pass_beyond)
    TextView mTvPassBeyond;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_range)
    TextView mTvRange;
    @BindView(R.id.iv_range_one)
    ImageView mIvRangeOne;
    @BindView(R.id.iv_range_two)
    ImageView mIvRangeTwo;
    @BindView(R.id.iv_n)
    ImageView mIvN;
    @BindView(R.id.ll_n)
    LinearLayout mLlN;
    @BindView(R.id.iv_y)
    ImageView mIvY;
    @BindView(R.id.ll_y)
    LinearLayout mLlY;
    @BindView(R.id.iv_pay)
    ImageView mIvPay;
    @BindView(R.id.ll_pay)
    LinearLayout mLlPay;
    @BindView(R.id.iv_pay2)
    ImageView mIvPay2;
    @BindView(R.id.ll_pay2)
    LinearLayout mLlPay2;
    @BindView(R.id.ll_address_info)
    LinearLayout mLlAddressInfo;
    @BindView(R.id.tv_modify)
    TextView mTvModify;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.ll_old_accessory)
    LinearLayout mLlOldAccessory;
    @BindView(R.id.tv_addressback)
    TextView mTvAddressback;
    @BindView(R.id.tv_select_time)
    TextView mTvSelectTime;
    @BindView(R.id.tv_apply)
    TextView mTvApply;
    @BindView(R.id.ll_apply_custom_service)
    LinearLayout mLlApplyCustomService;
    @BindView(R.id.iv_bar_code)
    ImageView mIvBarCode;
    @BindView(R.id.ll_bar_code)
    LinearLayout mLlBarCode;
    @BindView(R.id.iv_machine)
    ImageView mIvMachine;
    @BindView(R.id.ll_machine)
    LinearLayout mLlMachine;
    @BindView(R.id.iv_fault_location)
    ImageView mIvFaultLocation;
    @BindView(R.id.ll_fault_location)
    LinearLayout mLlFaultLocation;
    @BindView(R.id.iv_new_and_old_accessories)
    ImageView mIvNewAndOldAccessories;
    @BindView(R.id.ll_new_and_old_accessories)
    LinearLayout mLlNewAndOldAccessories;
    @BindView(R.id.ll_return_information)
    LinearLayout mLlReturnInformation;
    @BindView(R.id.negtive)
    Button mNegtive;
    @BindView(R.id.column_line)
    View mColumnLine;
    @BindView(R.id.positive)
    Button mPositive;
    @BindView(R.id.ll_confirm)
    LinearLayout mLlConfirm;
    private String OrderID;
    private WorkOrder.DataBean data;
    private AccessoryDetailAdapter accessoryDetailAdapter;
//    private ServiceAdapter serviceAdapter;
    private CommonDialog_Home reject;
    private CommonDialog_Home pass;
    private Data<String> result;
    private View expressno_view;
    private AlertDialog expressno_dialog;
    private Button btn_negtive;
    private Button btn_positive;
    private TextView tv_title;
    private EditText et_expressno;
    private LinearLayout ll_scan;
    private TextView tv_message;
    private String expressno;
    private SimpleTarget<Bitmap> simpleTarget;
    private String IsReturn;
    private String PostPayType;
    private String AddressBack = "";
    private List<Address> addressList;
    private String userId;
    private Address address;
    private double Service_range = 15;//正常距离
    private Double distance;
    private Double beyond;
    private EditText et_new_money;
    private String newmoney;



    /*弹出的评价view*/
    private Window window;
    private View view;
    private AlertDialog EvalateDialog;
    private TextView tv_orderid;
    private TextView tv_serach;
    private TextView tv_totle_grade;
    private StarBarView good_star;
    private TextView tv_good_content;
    private StarBarView shangmen_star;
    private TextView tv_shangmen_content;
    private StarBarView weixiu_star;
    private TextView tv_weixiu_content;
    private StarBarView fuwu_star;
    private TextView tv_fuwu_content;
    private EditText et_content;
    private TextView tv_submit;
    private ImageView iv_close;




    public static OrderDetailFragment newInstance(String param1, String param2) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_accessories_list;
    }

//    @Override
//    protected void initImmersionBar() {
//        mImmersionBar = ImmersionBar.with(this);
////        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
//        mImmersionBar.statusBarView(mView);
//        mImmersionBar.keyboardEnable(true);
//        mImmersionBar.init();
//    }

    @Override
    protected void initData() {
        mToolbar.setVisibility(View.GONE);
        mView.setVisibility(View.GONE);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("订单详情");
        OrderID = mParam1;
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetOrderInfo(OrderID);
        mPresenter.GetAccountAddress(userId);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.GetOrderInfo(OrderID);
                mRefreshLayout.finishRefresh(1000);
            }
        });
    }

    @Override
    protected void initView() {
//        mIvY.setSelected(true);
        mIvN.setSelected(false);
        mIvPay.setSelected(true);
        mIvPay2.setSelected(false);
//        IsReturn = "1";
        PostPayType = "1";
    }

    public void scaleview(String url) {
        simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                    super Bitmap> transition) {
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                rxDialogScaleView.setImage(resource);
                rxDialogScaleView.show();
            }
        };

        Glide.with(mActivity)
                .asBitmap()
                .load(url)
                .into(simpleTarget);
    }

    @Override
    protected void setListener() {
        mTvSendAccessory.setOnClickListener(this);

        mIconBack.setOnClickListener(this);
        mLlContactCustomerService.setOnClickListener(this);
        mTvReject.setOnClickListener(this);
        mTvPass.setOnClickListener(this);
        mTvPassService.setOnClickListener(this);
        mTvRejectService.setOnClickListener(this);

        mTvRejectBeyond.setOnClickListener(this);
        mTvPassBeyond.setOnClickListener(this);
        mIvRangeOne.setOnClickListener(this);

        mLlY.setOnClickListener(this);
        mLlN.setOnClickListener(this);
        mLlPay.setOnClickListener(this);
        mLlPay2.setOnClickListener(this);
        mTvModify.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);

        mTvApply.setOnClickListener(this);
        mIvBarCode.setOnClickListener(this);
        mIvMachine.setOnClickListener(this);
        mIvFaultLocation.setOnClickListener(this);
        mIvNewAndOldAccessories.setOnClickListener(this);
        mNegtive.setOnClickListener(this);
        mPositive.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send_accessory:
                expressno_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_add_expressno2, null);
                btn_negtive = expressno_view.findViewById(R.id.negtive);
                btn_positive = expressno_view.findViewById(R.id.positive);
                tv_title = expressno_view.findViewById(R.id.title);
                tv_message = expressno_view.findViewById(R.id.message);
                et_expressno = expressno_view.findViewById(R.id.et_expressno);
                ll_scan = expressno_view.findViewById(R.id.ll_scan);
                expressno_dialog = new AlertDialog.Builder(mActivity)
                        .setView(expressno_view)
                        .create();
                expressno_dialog.show();
                tv_title.setText("提示");
                tv_message.setText("是否发送配件");
                ll_scan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scan();
                    }
                });
                btn_negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expressno_dialog.dismiss();
                    }
                });
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expressno = et_expressno.getText().toString();
                        if ("".equals(expressno)) {
                            expressno = "123";
                        }
                        mPresenter.AddOrUpdateExpressNo(OrderID, expressno);
                    }
                });
                break;
            case R.id.tv_apply:
                final CommonDialog_Home apply = new CommonDialog_Home(mActivity);
                apply.setMessage("是否要发起质保")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//发起质保
                        apply.dismiss();
                        mPresenter.ApplyCustomService(OrderID);
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        apply.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.negtive:
                mPresenter.FactoryEnsureOrder(OrderID, "888888");
                break;
            case R.id.positive:
                //用户确认
               // mPresenter.EnSureOrder(OrderID, "888888");

                showOrderEvaluate();

                break;
            case R.id.iv_bar_code:
                for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                    if ("img1".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                        scaleview("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl());
                    }
                }
                break;
            case R.id.iv_machine:
                for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                    if ("img2".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                        scaleview("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl());
                    }
                }
                break;
            case R.id.iv_fault_location:
                for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                    if ("img3".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                        scaleview("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl());
                    }
                }
                break;
            case R.id.iv_new_and_old_accessories:
                for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                    if ("img4".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                        scaleview("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl());
                    }
                }
                break;
            case R.id.ll_y:
                mIvY.setSelected(true);
                mIvN.setSelected(false);
                mLlAddressInfo.setVisibility(View.VISIBLE);
                IsReturn = "1";
                break;
            case R.id.ll_n:
                mIvY.setSelected(false);
                mIvN.setSelected(true);
                mLlAddressInfo.setVisibility(View.GONE);
                IsReturn = "2";
                break;
            case R.id.ll_pay:
                mIvPay.setSelected(true);
                mIvPay2.setSelected(false);
                PostPayType = "1";
                break;
            case R.id.ll_pay2:
                mIvPay.setSelected(false);
                mIvPay2.setSelected(true);
                PostPayType = "2";
                break;
            case R.id.tv_modify:
//                Intent intent = new Intent(mActivity, ShippingAddressActivity.class);
//                intent.putExtra("type", "0");
//                startActivityForResult(intent, 100);
                break;
            case R.id.tv_submit:
                mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
                break;
            case R.id.iv_range_one:
                if (data.getOrderBeyondImg() == null) {
                    return;
                }
                if (data.getOrderBeyondImg().size() == 0) {
                    return;
                }
                simpleTarget = new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                            super Bitmap> transition) {
                        RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                        rxDialogScaleView.setImage(resource);
                        rxDialogScaleView.show();
                    }
                };

                Glide.with(mActivity)
                        .asBitmap()
                        .load("http://47.96.126.145:8820/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(0).getUrl())
                        .into(simpleTarget);
                break;
            case R.id.ll_contact_customer_service:
                final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                dialog.setMessage("是否拨打电话给客服")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//拨打电话
                        dialog.dismiss();
                        call("tel:" + "4006262365");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_reject:
                reject = new CommonDialog_Home(mActivity);
                reject.setMessage("是否拒绝申请的配件")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        reject.dismiss();
                        mPresenter.ApproveOrderAccessory(OrderID, "-1", "0");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        reject.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_pass:
                if (!"2".equals(data.getTypeID())) {
                    if ("1".equals(IsReturn)) {
                        if ("".equals(AddressBack)) {
                            MyUtils.showToast(mActivity, "请添加旧件寄送地址");
                            return;
                        } else {
                            mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
                        }
                    } else {
                        mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
                    }
                }

                if ("0".equals(data.getAccessoryState())) {
                    expressno_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_add_expressno, null);
                    btn_negtive = expressno_view.findViewById(R.id.negtive);
                    btn_positive = expressno_view.findViewById(R.id.positive);
                    tv_title = expressno_view.findViewById(R.id.title);
                    tv_message = expressno_view.findViewById(R.id.message);
                    et_expressno = expressno_view.findViewById(R.id.et_expressno);
                    et_new_money = expressno_view.findViewById(R.id.et_new_money);
                    ll_scan = expressno_view.findViewById(R.id.ll_scan);
                    expressno_dialog = new AlertDialog.Builder(mActivity)
                            .setView(expressno_view)
                            .create();
                    expressno_dialog.show();
                    tv_title.setText("提示");
                    tv_message.setText("是否同意申请的配件");
                    ll_scan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            scan();
                        }
                    });
                    btn_negtive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            expressno_dialog.dismiss();
                        }
                    });
                    btn_positive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            expressno = et_expressno.getText().toString();
                            newmoney = et_new_money.getText().toString();
                            if ("".equals(expressno)) {
                                expressno = "123";
                            }
                            if ("".equals(newmoney)) {
                                newmoney = "0";
                            }
                            expressno_dialog.dismiss();
                            mPresenter.ApproveOrderAccessory(OrderID, "1", newmoney);

//                            mPresenter.AddOrUpdateExpressNo(OrderID, expressno);
                        }
                    });
                } else if ("1".equals(data.getAccessoryState())) {
                    expressno_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_newmoney, null);
                    btn_negtive = expressno_view.findViewById(R.id.negtive);
                    btn_positive = expressno_view.findViewById(R.id.positive);
                    tv_title = expressno_view.findViewById(R.id.title);
                    tv_message = expressno_view.findViewById(R.id.message);
                    et_new_money = expressno_view.findViewById(R.id.et_new_money);
//                    et_expressno = expressno_view.findViewById(R.id.et_expressno);
//                    ll_scan = expressno_view.findViewById(R.id.ll_scan);
                    expressno_dialog = new AlertDialog.Builder(mActivity)
                            .setView(expressno_view)
                            .create();
                    expressno_dialog.show();
                    tv_title.setText("提示");
                    tv_message.setText("是否同意申请的配件");
//                    ll_scan.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            scan();
//                        }
//                    });
                    btn_negtive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            expressno_dialog.dismiss();
                        }
                    });
                    btn_positive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            newmoney = et_new_money.getText().toString();
                            if ("".equals(newmoney)) {
                                newmoney = "0";
                            }
                            expressno_dialog.dismiss();
                            mPresenter.ApproveOrderAccessory(OrderID, "1", newmoney);
                        }
                    });
                } else {
                    mPresenter.ApproveOrderAccessory(OrderID, "1", newmoney);
                }

                break;

            case R.id.tv_reject_service:
                reject = new CommonDialog_Home(mActivity);
                reject.setMessage("是否拒绝申请的服务")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        reject.dismiss();
                        mPresenter.ApproveOrderService(OrderID, "-1");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        reject.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_pass_service:
                pass = new CommonDialog_Home(mActivity);
                pass.setMessage("是否同意申请的服务")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        pass.dismiss();
                        mPresenter.ApproveOrderService(OrderID, "1");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        pass.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_reject_beyond:
                reject = new CommonDialog_Home(mActivity);
                reject.setMessage("是否拒绝申请的远程费")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        reject.dismiss();
                        mPresenter.ApproveBeyondMoney(OrderID, "-1");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        reject.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_pass_beyond:
                pass = new CommonDialog_Home(mActivity);
                pass.setMessage("是否同意申请的远程费")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        pass.dismiss();
                        mPresenter.ApproveBeyondMoney(OrderID, "1");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        pass.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }
    }

    /**
     * 扫描二维码
     */
    public void scan() {
//        IntentIntegrator integrator = new IntentIntegrator(mActivity);
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
        integrator.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
        integrator.setCameraId(0); //前置或者后置摄像头
        integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                if (data.getState()!=null){
                    mTvOrderState.setText(data.getState());
                }
                mTvName.setText(data.getUserName());

                mTvBeyondMoney.setText("¥" + data.getBeyondMoney() + "");
                mTvAccessoryMoney.setText("¥" + data.getAccessoryMoney());
                mTvServiceMoney.setText("¥" + data.getServiceMoney());
//                mTvBeyondMoney.setVisibility(View.GONE);
//                mTvAccessoryMoney.setVisibility(View.GONE);
//                mTvServiceMoney.setVisibility(View.GONE);

                if ("3".equals(data.getTypeID())){
                    mTvOrderMoney.setText("¥" + data.getQuaMoney() + "");
                }else{
//                    if (data.getAccessoryMoney() != null && !"0.00".equals(data.getAccessoryMoney())) {
                    if ("1".equals(data.getAccessoryApplyState())) {
                        mTvOrderMoney.setText("¥" + data.getQuaMoney());
//                        mTvOrderMoney.setText("¥" + (Double.parseDouble(data.getAccessoryMoney()) + Double.parseDouble(data.getBeyondMoney()) + Double.parseDouble(data.getPostMoney())) + "");
                    } else {
                        mTvOrderMoney.setText("¥" + data.getOrderMoney() + "");
                    }
                }

                if (!"0.00".equals(data.getPostMoney()) && data.getPostMoney() != null) {
                    mLlPostMoney.setVisibility(View.VISIBLE);
                    mTvPostMoney.setText("¥" + data.getPostMoney());
                } else {
                    mLlPostMoney.setVisibility(View.GONE);
                }

                if ("保内".equals(data.getGuarantee())) {
                    mIvY.setSelected(true);
                    mIvN.setSelected(false);
                    mLlAddressInfo.setVisibility(View.VISIBLE);
                    IsReturn = "1";
                } else {
                    mIvY.setSelected(false);
                    mIvN.setSelected(true);
                    mLlAddressInfo.setVisibility(View.GONE);
                    IsReturn = "2";
                }

                mTvAccessoryMemo.setText("备注：" + data.getAccessoryMemo());
                mTvAccessorySequency.setText("寄件类型：" + data.getAccessorySequencyStr());
                mTvPhone.setText(data.getPhone());
                mTvAddress.setText(data.getAddress());
                mTvTime.setText(data.getCreateDate());
                mTvWorkOrderStatus.setText(data.getState());
                mTvWorkOrderNumber.setText(data.getOrderID());
                mTvWarrantyType.setText(data.getGuarantee());
                mTvWorkOrderType.setText(data.getTypeName());
                mTvRecoveryTime.setText(data.getRecycleOrderHour());
                mTvSentOutAccessories.setText(data.getAccessorySendState());
                mTvBrand.setText(data.getBrandName());
                mTvCategory.setText(data.getCategoryName());
                mTvModel.setText(data.getSubCategoryName());
                mTvFaultDescription.setText(data.getMemo());

                mTvSpecifyDoorToDoorTime.setText(data.getExtraTime());
                mTvOrderSource.setText(data.getExpressNo());
                mTvThirdParty.setText(data.getThirdPartyNo());


                if ("1".equals(data.getServiceApplyState())) {
                    mTvPassService.setVisibility(View.GONE);
                    mTvRejectService.setVisibility(View.GONE);
                    mTvStatusService.setVisibility(View.VISIBLE);
                    mTvStatusService.setText("已审核通过");
                } else if ("-1".equals(data.getServiceApplyState())) {
                    mTvPassService.setVisibility(View.GONE);
                    mTvRejectService.setVisibility(View.GONE);
                    mTvStatusService.setVisibility(View.VISIBLE);
                    mTvStatusService.setText("已拒绝");
                } else {
                    mTvPassService.setVisibility(View.VISIBLE);
                    mTvRejectService.setVisibility(View.VISIBLE);
                    mTvStatusService.setVisibility(View.GONE);
                }
                /*if (data.getOrderServiceDetail() == null) {
                    mLlAuditService.setVisibility(View.GONE);
                } else {
                    if (data.getOrderServiceDetail().size() == 0) {
                        mLlAuditService.setVisibility(View.GONE);
                    } else {
                        mLlAuditService.setVisibility(View.VISIBLE);
                        serviceAdapter = new ServiceAdapter(R.layout.item_accessories, data.getOrderServiceDetail());
                        mRvService.setLayoutManager(new LinearLayoutManager(mActivity));
                        mRvService.setAdapter(serviceAdapter);
                    }

                }*/

                if (data.getOrderAccessroyDetail() == null) {
                    mLlApproveAccessory.setVisibility(View.GONE);
                    mLlOldAccessory.setVisibility(View.GONE);
                    mLlSendAccessory.setVisibility(View.GONE);
                } else {
                    if (data.getOrderAccessroyDetail().size() == 0) {
                        mLlApproveAccessory.setVisibility(View.GONE);
                        mLlOldAccessory.setVisibility(View.GONE);
                        mLlSendAccessory.setVisibility(View.GONE);
                    } else {
                        for (int i = 0; i < data.getOrderAccessroyDetail().size(); i++) {
                            if ("".equals(data.getOrderAccessroyDetail().get(i).getExpressNo())){
                                mLlSendAccessory.setVisibility(View.VISIBLE);
                            }else{
                                mLlSendAccessory.setVisibility(View.GONE);
                            }
                        }
                        if ("1".equals(data.getAccessoryApplyState())) {
                            mTvPass.setVisibility(View.GONE);
                            mTvReject.setVisibility(View.GONE);
                            mTvStatusAccessory.setVisibility(View.VISIBLE);
                            mTvStatusAccessory.setText("已审核通过");
                            if (data.getIsReturn() != null) {
                                if ("1".equals(data.getIsReturn())) {
                                    mTvAddressback.setText(data.getAddressBack());
                                    mLlAddressInfo.setVisibility(View.VISIBLE);
                                    mLlY.setVisibility(View.VISIBLE);
                                    mIvY.setVisibility(View.GONE);
                                    mLlN.setVisibility(View.GONE);
                                    mTvModify.setVisibility(View.GONE);
                                    if ("1".equals(data.getPostPayType())) {
                                        mLlPay.setVisibility(View.VISIBLE);
                                        mIvPay.setVisibility(View.GONE);
                                        mLlPay2.setVisibility(View.GONE);
                                    } else {
                                        mLlPay.setVisibility(View.GONE);
                                        mIvPay2.setVisibility(View.GONE);
                                        mLlPay2.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    mLlAddressInfo.setVisibility(View.GONE);
                                    mLlY.setVisibility(View.GONE);
                                    mIvN.setVisibility(View.GONE);
                                    mLlN.setVisibility(View.VISIBLE);
                                }
                            }
                        } else if ("-1".equals(data.getAccessoryApplyState())) {
                            mTvPass.setVisibility(View.GONE);
                            mTvReject.setVisibility(View.GONE);
                            mTvStatusAccessory.setVisibility(View.VISIBLE);
                            mTvStatusAccessory.setText("已拒绝");
                            mLlOldAccessory.setVisibility(View.GONE);
                        } else {
                            mTvPass.setVisibility(View.VISIBLE);
                            mTvReject.setVisibility(View.VISIBLE);
                            mTvStatusAccessory.setVisibility(View.GONE);
                        }
                        for (int i=0;i<data.getOrderAccessroyDetail().size();i++){
                            if ("4".equals(data.getOrderAccessroyDetail().get(i).getSizeID())){
                                mLlApproveAccessory.setVisibility(View.GONE);
                                mLlSendAccessory.setVisibility(View.GONE);
                                mLlOldAccessory.setVisibility(View.GONE);
                            }else {
                                mLlApproveAccessory.setVisibility(View.VISIBLE);
                            }
                        }

//                        Log.d(TAG,"data.getSizeID()"+data.getOrderAccessroyDetail().get(0).getSizeID());
                        mLlOldAccessory.setVisibility(View.VISIBLE);
                        for (int i = 0; i < data.getOrderAccessroyDetail().size(); i++) {
                            if ("2".equals(data.getOrderAccessroyDetail().get(i).getState())) {
                                data.getOrderAccessroyDetail().remove(i);
                            }
                        }
                        accessoryDetailAdapter = new AccessoryDetailAdapter(R.layout.item_accessories, data.getOrderAccessroyDetail());
                        mRvAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
                        mRvAccessories.setAdapter(accessoryDetailAdapter);
                    }
                }
                if (data.getBeyondState() != null) {
                    mLlApproveBeyondMoney.setVisibility(View.VISIBLE);
                } else {
                    mLlApproveBeyondMoney.setVisibility(View.GONE);
                }
                distance = Double.parseDouble(data.getDistance());
                beyond = Double.parseDouble(data.getBeyondDistance());
                if (distance.equals(beyond)) {
                    mTvRange.setText(String.format("%.2f", beyond - Service_range));
                } else {
                    mTvRange.setText(String.format("%.2f", beyond));
                }


                if ("1".equals(data.getBeyondState())) {
                    mTvPassBeyond.setVisibility(View.GONE);
                    mTvRejectBeyond.setVisibility(View.GONE);
                    mTvStatus.setVisibility(View.VISIBLE);
                    mTvStatus.setText("已审核通过");
                } else if ("-1".equals(data.getBeyondState())) {
                    mTvPassBeyond.setVisibility(View.GONE);
                    mTvRejectBeyond.setVisibility(View.GONE);
                    mTvStatus.setVisibility(View.VISIBLE);
                    mTvStatus.setText("已拒绝");
                } else {
                    mTvPassBeyond.setVisibility(View.VISIBLE);
                    mTvRejectBeyond.setVisibility(View.VISIBLE);
                    mTvStatus.setVisibility(View.GONE);
                }
                if (data.getOrderBeyondImg() == null) {
                    return;
                }
                if (data.getOrderBeyondImg().size() == 1) {
                    Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(0).getUrl()).into(mIvRangeOne);
//                    Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(1).getUrl()).into(mIvRangeTwo);
                    mIvRangeOne.setVisibility(View.VISIBLE);
                    mIvRangeTwo.setVisibility(View.GONE);
                } else {
                    mIvRangeOne.setVisibility(View.GONE);
                    mIvRangeTwo.setVisibility(View.GONE);
                }
                if ("服务完成".equals(data.getState()) || "待评价".equals(data.getState()) || "已完成".equals(data.getState())) {
                    if ("已完成".equals(data.getState())) {
                        mLlApplyCustomService.setVisibility(View.VISIBLE);
                    } else {
                        mLlApplyCustomService.setVisibility(View.GONE);
                    }
                    if ("服务完成".equals(data.getState())) {
                        if ("1".equals(data.getIsReturn())) {
                            if ("".equals(data.getReturnAccessoryMsg()) || data.getReturnAccessoryMsg() == null) {
                                mLlConfirm.setVisibility(View.GONE);
                            } else {
                                mLlConfirm.setVisibility(View.VISIBLE);
                            }
                        } else {
                            mLlConfirm.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mLlConfirm.setVisibility(View.GONE);
                    }
                    mLlReturnInformation.setVisibility(View.VISIBLE);
                    if ("1".equals(data.getTypeID()) || "3".equals(data.getTypeID())) {//维修
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                            if ("img1".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl()).into(mIvBarCode);
                            }
                            if ("img2".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl()).into(mIvMachine);
                            }
                            if ("img3".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl()).into(mIvFaultLocation);
                            }
                            if ("img4".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl()).into(mIvNewAndOldAccessories);
                            }
                            list.add(data.getReturnaccessoryImg().get(i).getRelation());
                        }
                        if (!list.contains("img1")) {
                            mLlBarCode.setVisibility(View.GONE);
                        }
                        if (!list.contains("img2")) {
                            mLlMachine.setVisibility(View.GONE);
                        }
                        if (!list.contains("img3")) {
                            mLlFaultLocation.setVisibility(View.GONE);
                        }
                        if (!list.contains("img4")) {
                            mLlNewAndOldAccessories.setVisibility(View.GONE);
                        }
//                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(0).getUrl()).into(mIvBarCode);
//                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(1).getUrl()).into(mIvMachine);
//                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(2).getUrl()).into(mIvFaultLocation);
//                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(3).getUrl()).into(mIvNewAndOldAccessories);
                    } else {
//                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/FinishOrder/" + data.getOrderImg().get(0).getUrl()).into(mIvBarCode);
//                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/FinishOrder/" + data.getOrderImg().get(1).getUrl()).into(mIvMachine);
//                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/FinishOrder/" + data.getOrderImg().get(2).getUrl()).into(mIvFaultLocation);
//                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/FinishOrder/" + data.getOrderImg().get(3).getUrl()).into(mIvNewAndOldAccessories);
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                            if ("img1".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(0).getUrl()).into(mIvBarCode);
                            }
                            if ("img2".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(1).getUrl()).into(mIvMachine);

                            }
                            if ("img3".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(2).getUrl()).into(mIvFaultLocation);

                            }
                            if ("img4".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(3).getUrl()).into(mIvNewAndOldAccessories);

                            }
                            list.add(data.getReturnaccessoryImg().get(i).getRelation());
                        }
//                        Log.d(TAG,"data.getReturnaccessoryImg().size()"+data.getReturnaccessoryImg().size());
                        if (!list.contains("img1")) {
                            mLlBarCode.setVisibility(View.GONE);
                        }
                        if (!list.contains("img2")) {
                            mLlMachine.setVisibility(View.GONE);
                        }
                        if (!list.contains("img3")) {
                            mLlFaultLocation.setVisibility(View.GONE);
                        }
                        if (!list.contains("img4")) {
                            mLlNewAndOldAccessories.setVisibility(View.GONE);
                        }
                        mLlOldAccessory.setVisibility(View.GONE);
                    }
                } else {
                    mLlReturnInformation.setVisibility(View.GONE);
                    mLlApplyCustomService.setVisibility(View.GONE);
                    mLlConfirm.setVisibility(View.GONE);
                }
                if (data.getSendOrderList().size() != 0) {
                    mTvSelectTime.setText(data.getSendOrderList().get(0).getServiceDate());
                }
                if ("2".equals(data.getTypeID())) {
                    mLlOldAccessory.setVisibility(View.GONE);
                }
                break;
            case 401:
                break;
        }
    }

    @Override
    public void ApplyCustomService(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort("发起质保成功！");
                mActivity.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void ApproveOrderAccessory(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("审核成功！");
                    mPresenter.GetOrderInfo(OrderID);
                } else {
                    ToastUtils.showShort("审核失败！" + result.getItem2());
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void ApproveOrderService(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("审核成功！");
                    mPresenter.GetOrderInfo(OrderID);
                } else {
                    ToastUtils.showShort("审核失败！" + result.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void ApproveBeyondMoney(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("审核成功！");
                    mPresenter.GetOrderInfo(OrderID);
                } else {
                    ToastUtils.showShort("审核失败！" + result.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void AddOrUpdateExpressNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("添加成功！");
                    expressno_dialog.dismiss();
//                    mPresenter.ApproveOrderAccessory(OrderID, "1", newmoney);
                } else {
                    ToastUtils.showShort("添加失败！" + result.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void EnSureOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
                    mPresenter.GetOrderInfo(OrderID);
                    if (EvalateDialog!=null){
                        EvalateDialog.dismiss();
                    }
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void FactoryEnsureOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
                    mPresenter.GetOrderInfo(OrderID);
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void UpdateIsReturnByOrderID(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
//                    ToastUtils.showShort(data.getItem2());
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetAccountAddress(BaseResult<List<Address>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                /*addressList = baseResult.getData();
                if (addressList.size() != 0) {
                    for (int i = 0; i < addressList.size(); i++) {
                        if ("1".equals(addressList.get(i).getIsDefault())) {
                            AddressBack = addressList.get(i).getAddress() + "(" + addressList.get(i).getUserName() + "收)" + addressList.get(i).getPhone();
                            mTvAddressback.setText(AddressBack);
                            mTvModify.setText("修改地址");
                        }
                    }
                } else {
                    AddressBack = "";
                    mTvAddressback.setText(AddressBack);
                    mTvModify.setText("添加地址");
                }*/
                break;
            default:
                ToastUtils.showShort("获取失败");
                break;
        }
    }



    /*弹出确认工单评价*/
    public void showOrderEvaluate() {
        view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_evaluate, null);
        EvalateDialog = new AlertDialog.Builder(mActivity).setView(view).create();
        EvalateDialog.show();
        window = EvalateDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());


        String appraise;//评轮内容
        iv_close = view.findViewById(R.id.iv_close);
        tv_orderid = view.findViewById(R.id.tv_orderid);//工单号
        tv_serach = view.findViewById(R.id.tv_serach);
        tv_submit =view.findViewById(R.id.tv_submit);//提交
        et_content=view.findViewById(R.id.et_content);//评轮

        tv_totle_grade = view.findViewById(R.id.tv_totle_grade); //分数
        good_star = view.findViewById(R.id.good_star);//星星数
        tv_good_content = view.findViewById(R.id.tv_good_content);//满意度

        shangmen_star = view.findViewById(R.id.shangmen_star);
        tv_shangmen_content = view.findViewById(R.id.tv_shangmen_content);

        weixiu_star = view.findViewById(R.id.weixiu_star);
        tv_weixiu_content = view.findViewById(R.id.tv_weixiu_content);

        fuwu_star = view.findViewById(R.id.fuwu_star);
        tv_fuwu_content = view.findViewById(R.id.tv_fuwu_content);

         if (data!=null){
             tv_orderid.setText("工单号:"+data.getOrderID());
         }


        good_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float starRating = good_star.getStarRating();
                setStarName(tv_good_content, starRating);
                if (starRating == 1.0f) {
                    tv_totle_grade.setText("1");
                }
                else if (starRating == 2.0f)
                 {
                     tv_totle_grade.setText("2");
                }
                else if (starRating == 3.0f)
                {
                    tv_totle_grade.setText("3");
                }
                else if (starRating == 4.0f)
                {
                    tv_totle_grade.setText("4");
                }
                else
                {
                    tv_totle_grade.setText("5");
                }

            }
        });

        shangmen_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float starRating = shangmen_star.getStarRating();
                setStarName(tv_shangmen_content, starRating);
            }
        });

        weixiu_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float starRating = weixiu_star.getStarRating();
                setStarName(tv_weixiu_content, starRating);
            }
        });
        fuwu_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float starRating = fuwu_star.getStarRating();
                setStarName(tv_fuwu_content, starRating);
            }
        });


        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvalateDialog.dismiss();
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(et_content.getText().toString())){
                    mPresenter.EnSureOrder(data.getOrderID(),"888888","5","用户暂无评价");
                }else {
                    mPresenter.EnSureOrder(data.getOrderID(),"888888","5",et_content.getText().toString());
                }


            }
        });




    }













    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanResult != null) {
                String result = scanResult.getContents();
                if (result == null) {
                    return;
                } else {
                    et_expressno.setText(result);
                }
            }
        }
        if (requestCode == 100) {
            if (data != null) {
                address = (Address) data.getSerializableExtra("address");
                if (address != null) {
                    AddressBack = address.getAddress() + "(" + address.getUserName() + "收)" + address.getPhone();
                    mTvAddressback.setText(AddressBack);
                }
            }
        }
    }*/


    /**
     * 设置星星文字
     */
    private void setStarName(TextView textView, float star_num) {
        if (star_num == 5.0f) {
            textView.setText("非常好");
        } else if (star_num == 4.0f) {
            textView.setText("很好");
        } else if (star_num == 3.0f) {
            textView.setText("一般");
        } else if (star_num == 2.0f) {
            textView.setText("很差");
        } else if (star_num == 1.0f) {
            textView.setText("非常差");
        }
    }


}
