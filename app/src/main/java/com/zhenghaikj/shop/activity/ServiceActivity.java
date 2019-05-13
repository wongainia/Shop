package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.AddOrderContract;
import com.zhenghaikj.shop.mvp.model.AddOrderModel;
import com.zhenghaikj.shop.mvp.presenter.AddOrderPresenter;
import com.zhenghaikj.shop.utils.GlideUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceActivity extends BaseActivity<AddOrderPresenter, AddOrderModel> implements View.OnClickListener, AddOrderContract.View {
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
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.iv_goods_picture)
    ImageView mIvGoodsPicture;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_number_of_applications)
    TextView mTvNumberOfApplications;
    @BindView(R.id.ll_reason_for_application)
    LinearLayout mLlReasonForApplication;
    @BindView(R.id.et_problem_description)
    EditText mEtProblemDescription;
    @BindView(R.id.tv_word_count)
    TextView mTvWordCount;
    @BindView(R.id.iv_speak)
    ImageView mIvSpeak;
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.switcher_installation_work_order)
    Switch mSwitcherInstallationWorkOrder;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_contact)
    TextView mTvContact;
    @BindView(R.id.tv_contact_number)
    TextView mTvContactNumber;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.ll_add_address)
    LinearLayout mLlAddAddress;
    @BindView(R.id.cb_under_warranty)
    CheckBox mCbUnderWarranty;
    @BindView(R.id.ll_under_warranty)
    LinearLayout mLlUnderWarranty;
    @BindView(R.id.cb_outside_the_warranty)
    CheckBox mCbOutsideTheWarranty;
    @BindView(R.id.ll_outside_the_warranty)
    LinearLayout mLlOutsideTheWarranty;
    @BindView(R.id.et_recovery_time)
    EditText mEtRecoveryTime;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.cb_yes)
    CheckBox mCbYes;
    @BindView(R.id.ll_yes)
    LinearLayout mLlYes;
    @BindView(R.id.cb_no)
    CheckBox mCbNo;
    @BindView(R.id.ll_no)
    LinearLayout mLlNo;
    @BindView(R.id.ll_accessories)
    LinearLayout mLlAccessories;
    @BindView(R.id.cb_yes_signing)
    CheckBox mCbYesSigning;
    @BindView(R.id.ll_yes_signing)
    LinearLayout mLlYesSigning;
    @BindView(R.id.cb_no_signing)
    CheckBox mCbNoSigning;
    @BindView(R.id.ll_no_signing)
    LinearLayout mLlNoSigning;
    @BindView(R.id.view_sig)
    View mViewSig;
    @BindView(R.id.et_expressno)
    EditText mEtExpressno;
    @BindView(R.id.ll_scan)
    LinearLayout mLlScan;
    @BindView(R.id.ll_number)
    LinearLayout mLlNumber;
    @BindView(R.id.ll_signing)
    LinearLayout mLlSigning;
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;
    @BindView(R.id.tv_expedited)
    TextView mTvExpedited;
    private OrderDetail.OrderItemBean bean;
    private String storeName;
    private String num;
    private String title;
    private String memo;
    private ShippingAddressList.ShippingAddressBean address;
    private String addressid;
    private OrderDetail.OrderBean order;
    private String addressStr;
    private String phone;
    private String name;
    private String Extra;
    private String ExtraTime;
    private String ExtraFee;
    private String OrderMoney;
    private String Guarantee;
    private String AccessorySendState;
    private String SigningState;
    private IntentIntegrator integrator;
    private String RecycleOrderHour;
    private String number;
    private String userID;
    private String BrandID;//品牌id
    private String BrandName;//品牌名称  例如 海尔
    private String ParentID;//父级ID
    private String ParentName;//父级名称  例如  冰箱
    private String FCategoryID;//子级ID
    private String FCategoryName;//子级名称  例如  单门 容积X≤100
    private String ProvinceCode;//省code
    private String CityCode;//市code
    private String AreaCode;//区code
    private String DistrictCode;//街道code
    private SPUtils spUtil;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_service;
    }

    @Override
    protected void initData() {
        bean = (OrderDetail.OrderItemBean) getIntent().getSerializableExtra("product");
        order = (OrderDetail.OrderBean) getIntent().getSerializableExtra("order");
        title = getIntent().getStringExtra("title");
        mTvTitle.setText(title);
        storeName = getIntent().getStringExtra("storeName");
        num = getIntent().getStringExtra("num");
        mTvGoodsName.setText(bean.getProductName());
        GlideUtil.loadImageViewLodingRadius(mActivity, bean.getProductImage(), mIvGoodsPicture, R.drawable.image_loading, R.drawable.image_loading, 10);
        mTvPrice.setText("价格：￥" + bean.getPrice());
        mTvNumber.setText("数量：" + bean.getCount());
        mTvNumberOfApplications.setText("申请数量：" + num);
        mTvStoreName.setText(storeName);
        addressStr = order.getAddress();
        name = order.getShipTo();
        phone = order.getPhone();
        mTvAddress.setText(order.getAddress());
        mTvContact.setText(order.getShipTo());
        mTvContactNumber.setText(order.getPhone());

        //默认保内
        mCbUnderWarranty.setChecked(true);
        mCbOutsideTheWarranty.setChecked(false);
        Guarantee = "Y";
        //已发配件默认否
        mCbYes.setChecked(false);
        mCbNo.setChecked(true);
        AccessorySendState = "N";
//        mPresenter.GetFactoryBrand(userID);

        switch (title) {
            case "安装":
                mLlAccessories.setVisibility(View.GONE);
                mLlSigning.setVisibility(View.VISIBLE);
                break;
            case "维修":
                mLlAccessories.setVisibility(View.VISIBLE);
                mLlSigning.setVisibility(View.GONE);
                break;

        }
    }

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
    protected void initView() {

        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
        mLlAddress.setOnClickListener(this);
        mSwitcherInstallationWorkOrder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLlAddress.setVisibility(View.GONE);
                    addressStr = order.getAddress();
                    name = order.getShipTo();
                    phone = order.getPhone();
                } else {
                    mLlAddress.setVisibility(View.VISIBLE);
                }
            }
        });

        mLlUnderWarranty.setOnClickListener(this);
        mLlOutsideTheWarranty.setOnClickListener(this);
        mLlYes.setOnClickListener(this);
        mLlNo.setOnClickListener(this);
        mLlYesSigning.setOnClickListener(this);
        mLlNoSigning.setOnClickListener(this);
        mLlScan.setOnClickListener(this);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Extra = "N";
                        ExtraTime = "0";
                        ExtraFee = "0";
                        break;
                    case 1:
                        Extra = "Y";
                        ExtraTime = "12";
                        ExtraFee = "60";
                        break;
                    case 2:
                        Extra = "Y";
                        ExtraTime = "24";
                        ExtraFee = "40";
                        break;
                    case 3:
                        Extra = "Y";
                        ExtraTime = "48";
                        ExtraFee = "20";
                        break;
                }
                mTvExpedited.setText("加急费用￥" + ExtraFee);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Extra = "N";
                ExtraTime = "0";
                ExtraFee = "0";
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_address:
                Intent intent = new Intent(mActivity, ShippingAddressActivity.class);
                intent.putExtra("CHOOSE_ADDRESS_REQUEST", true);
                startActivityForResult(intent, Config.CHOOSE_ADDRESS_REQUEST);
                break;
            case R.id.ll_under_warranty:
                mCbUnderWarranty.setChecked(true);
                mCbOutsideTheWarranty.setChecked(false);
                Guarantee = "Y";
                break;
            case R.id.ll_outside_the_warranty:
                mCbUnderWarranty.setChecked(false);
                mCbOutsideTheWarranty.setChecked(true);
                Guarantee = "N";
                break;
            case R.id.ll_yes:
                mCbYes.setChecked(true);
                mCbNo.setChecked(false);
                AccessorySendState = "Y";
                break;
            case R.id.ll_no:
                mCbYes.setChecked(false);
                mCbNo.setChecked(true);
                AccessorySendState = "N";
                break;
            case R.id.ll_yes_signing:
                mCbYesSigning.setChecked(true);
                mCbNoSigning.setChecked(false);
                SigningState = "Y";
                mViewSig.setVisibility(View.GONE);
                mLlNumber.setVisibility(View.GONE);
                break;
            case R.id.ll_no_signing:
                mCbYesSigning.setChecked(false);
                mCbNoSigning.setChecked(true);
                SigningState = "N";
                mViewSig.setVisibility(View.VISIBLE);
                mLlNumber.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_scan:
                IntentIntegrator integrator = new IntentIntegrator(ServiceActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                integrator.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            case R.id.tv_submit:
                spUtil = SPUtils.getInstance("token");
                userID = spUtil.getString("userName2");
                RecycleOrderHour = mEtRecoveryTime.getText().toString();
                memo = mEtProblemDescription.getText().toString();
                if ("".equals(memo)) {
                    ToastUtils.showShort("请填写问题描述！");
                    return;
                }
                if (Guarantee == null || "".equals(Guarantee)) {
                    ToastUtils.showShort("请选择保修期内或保修期外！");
                    return;
                }
                if (RecycleOrderHour == null || "".equals(RecycleOrderHour)) {
                    ToastUtils.showShort( "请输入回收时间！");
                    return;
                }
                if (!(Integer.parseInt(RecycleOrderHour) >= 12 || Integer.parseInt(RecycleOrderHour) <= 48)) {
                    ToastUtils.showShort( "回收时间需大于等于12小于等于48！");
                    return;
                }
                switch (title) {
                    case "安装":
                        OrderMoney = "100";
                        if (SigningState ==null||"".equals(SigningState)){
                            ToastUtils.showShort( "请选择客户是否为已签收产品");
                            return;
                        }
                        number = mEtExpressno.getText().toString();
                        if (mCbNoSigning.isChecked()){
                            if ("".equals(number)){
                                ToastUtils.showShort( "请填写快递单号");
                                return;
                            }
                        }

                        mPresenter.AddOrder("2", "安装", userID, BrandID, BrandName, ParentID, ParentName, FCategoryID, FCategoryName, ProvinceCode, CityCode, AreaCode, DistrictCode, addressStr, name, phone, memo, OrderMoney, RecycleOrderHour, Guarantee, null, Extra, ExtraTime, ExtraFee, num, SigningState, number);
                        break;
                    case "维修":
                        if (AccessorySendState == null || "".equals(AccessorySendState)) {
                            ToastUtils.showShort("请选择是否为已发配件！");
                            return;
                        }
                        OrderMoney = "100";
                        mPresenter.AddOrder("1", "维修", userID, BrandID, BrandName, ParentID, ParentName, FCategoryID, FCategoryName, ProvinceCode, CityCode, AreaCode, DistrictCode, addressStr, name, phone, memo, OrderMoney, RecycleOrderHour, Guarantee, AccessorySendState, Extra, ExtraTime, ExtraFee, num,null,null);
                        break;
                    default:
                        break;
                }

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void AddOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
//                    Bundle bundle = new Bundle();
//                    bundle.putString("title", "待接单");
//                    bundle.putInt("position", 0);
//                    Intent intent = new Intent(mActivity, AllWorkOrdersActivity.class);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
//                ToastUtils.showShort(data.getItem2());
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*处理选择的地址*/
        if (resultCode == Config.CHOOSE_ADDRESS_RESULT) {
            if (requestCode == Config.CHOOSE_ADDRESS_REQUEST) {

                address = (ShippingAddressList.ShippingAddressBean) data.getSerializableExtra("Address");
                addressid = address.getId();
                mTvContact.setText(address.getShipTo());
                mTvContactNumber.setText(address.getPhone());
                addressStr = address.getRegionFullName() + " " + address.getAddress();
                name = address.getShipTo();
                phone = address.getPhone();
                mTvAddress.setText(address.getRegionFullName() + " " + address.getAddress());
            }

        }

    }
}
