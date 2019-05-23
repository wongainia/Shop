package com.zhenghaikj.shop.fragment;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.AddWorkOrderActivity;
import com.zhenghaikj.shop.activity.CouponActivity;
import com.zhenghaikj.shop.activity.FavoritesActivity;
import com.zhenghaikj.shop.activity.FootprintActivity;
import com.zhenghaikj.shop.activity.GiftActivity;
import com.zhenghaikj.shop.activity.LoginActivity;
import com.zhenghaikj.shop.activity.OrderActivity;
import com.zhenghaikj.shop.activity.PersonalInformationActivity;
import com.zhenghaikj.shop.activity.ReturnActivity;
import com.zhenghaikj.shop.activity.SettingActivity;
import com.zhenghaikj.shop.activity.StoreActivity;
import com.zhenghaikj.shop.activity.WalletActivity;
import com.zhenghaikj.shop.activity.WorkOrderActivity;
import com.zhenghaikj.shop.activity.WorkOrderDetailActivity;
import com.zhenghaikj.shop.adapter.ServiceAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.dialog.CustomDialog;
import com.zhenghaikj.shop.dialog.ServiceDialog;
import com.zhenghaikj.shop.dialog.WordOrderDialog;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.Track;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.fragment.WorkOrder.OrderDetailFragment;
import com.zhenghaikj.shop.mvp.contract.MineContract;
import com.zhenghaikj.shop.mvp.model.MineModel;
import com.zhenghaikj.shop.mvp.presenter.MinePresenter;
import com.zhenghaikj.shop.utils.ZXingUtils;
import com.zhenghaikj.shop.widget.CircleImageView;
import com.zhenghaikj.shop.widget.StarBarView;
import com.zhenghaikj.shop.widget.SwitchView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MineFragment extends BaseLazyFragment<MinePresenter, MineModel> implements View.OnClickListener, MineContract.View {

    private static final String TAG = "MineFragment";//

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    @BindView(R.id.ll_integral)
    LinearLayout mLlIntegral;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.tv_favorites)
    TextView mTvFavorites;
    @BindView(R.id.ll_favorites)
    LinearLayout mLlFavorites;
    @BindView(R.id.tv_focus_on_the_store)
    TextView mTvFocusOnTheStore;
    @BindView(R.id.ll_focus_on_the_store)
    LinearLayout mLlFocusOnTheStore;
    @BindView(R.id.tv_baby)
    TextView mTvBaby;
    @BindView(R.id.ll_baby)
    LinearLayout mLlBaby;
    @BindView(R.id.tv_my_purse)
    TextView mTvMyPurse;
    @BindView(R.id.ll_my_purse)
    LinearLayout mLlMyPurse;
    @BindView(R.id.iv_wan_vip)
    ImageView mIvWanVip;
    @BindView(R.id.tv_contribution)
    TextView mTvContribution;
    @BindView(R.id.ll_contribution)
    LinearLayout mLlContribution;
    @BindView(R.id.ll_redemption_center)
    LinearLayout mLlRedemptionCenter;
    @BindView(R.id.ll_pending_payment)
    LinearLayout mLlPendingPayment;
    @BindView(R.id.ll_to_be_delivered)
    LinearLayout mLlToBeDelivered;
    @BindView(R.id.ll_pending_receipt)
    LinearLayout mLlPendingReceipt;
    @BindView(R.id.ll_comment)
    LinearLayout mLlComment;
    @BindView(R.id.ll_after_sale)
    LinearLayout mLlAfterSale;
    @BindView(R.id.ll_all_orders)
    LinearLayout mLlAllOrders;
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_express_information)
    TextView mTvExpressInformation;
    @BindView(R.id.ll_logistics)
    LinearLayout mLlLogistics;
    @BindView(R.id.ll_essential_service)
    LinearLayout mLlEssentialService;
    @BindView(R.id.ll_free_installation)
    LinearLayout mLlFreeInstallation;
    @BindView(R.id.ll_free_repair)
    LinearLayout mLlFreeRepair;
    @BindView(R.id.iv_copy)
    ImageView mIvCopy;
    @BindView(R.id.ll_purse)
    LinearLayout mLlPurse;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.iv_watch)
    ImageView mIvWatch;
    @BindView(R.id.tv_wallet_balance)
    TextView mTvWalletBalance;
    @BindView(R.id.iv_watch_one)
    ImageView mIvWatchOne;
    @BindView(R.id.ll_watermelon_wallet)
    LinearLayout mLlWatermelonWallet;
    @BindView(R.id.tv_account_balance)
    TextView mTvAccountBalance;
    @BindView(R.id.iv_account_watch)
    ImageView mIvAccountWatch;
    @BindView(R.id.tv_redemption_mall)
    TextView mTvRedemptionMall;
    @BindView(R.id.ll_redemption_mall)
    LinearLayout mLlRedemptionMall;
    @BindView(R.id.tv_how_money)
    TextView mTvHowMoney;
    @BindView(R.id.ll_gift)
    LinearLayout mLlGift;
    @BindView(R.id.ll_transaction)
    LinearLayout mLlTransaction;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.ll_get_work_order)
    LinearLayout mLlGetWorkOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_count_dfk)
    TextView mTvCountDfk;
    @BindView(R.id.tv_count_dfh)
    TextView mTvCountDfh;
    @BindView(R.id.tv_count_dsh)
    TextView mTvCountDsh;
    @BindView(R.id.tv_count_dpj)
    TextView mTvCountDpj;
    @BindView(R.id.tv_count_sh)
    TextView mTvCountSh;
    @BindView(R.id.tv_coupon)
    TextView mTvCoupon;
    @BindView(R.id.ll_coupon)
    LinearLayout mLlCoupon;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_merchant)
    LinearLayout mLlMerchant;
    @BindView(R.id.ll_coins)
    LinearLayout mLlCoins;
    @BindView(R.id.scrolltv)
    SwitchView mScrolltv;

    private CustomDialog customDialog;
    private RecyclerView rv_logistics;
    private ServiceDialog serviceDialog;
    private RecyclerView rv_service;
    private Bundle bundle;
    private Intent intent;
    private SPUtils spUtils;
    private String userKey;
    private int pageIndex;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private UserInfo.UserInfoDean userInfo;
    private String userName;
    private boolean isLogin;
    private View under_review;
    private AlertDialog underReviewDialog;

//    private String[] name=new String[]{"1","2","3"};
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
    private WorkOrder.DataBean data;
    private float starRating = 5;
    private float starRating1 = 5;
    private float starRating2 = 5;
    private float starRating3 = 5;
    private TextView tv_submit1;
    private EditText et_content1;
    private WorkOrder workOrder;
    private int i=0;
    private ClipData myClip;
    private ClipboardManager myClipboard;
    private ServiceAdapter serviceAdapter;
    private List<WorkOrder.DataBean> datalist;


    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");
        isLogin = spUtils.getBoolean("isLogin");
//        mPresenter.GetOrderByhmall(userName);
//        mPresenter.GetOrderInfoList(userName,"5","1","1");
//        mPresenter.GetOrderByhmalluserid(userName);
        if (!"".equals(userName) && !"".equals(userKey)) {
            mPresenter.GetUserInfoList(userName, "1");
            mPresenter.PersonalInformation(userKey);
            mPresenter.GetHistoryVisite(userKey);
            mPresenter.GetOrderByhmall(userName);
//        mPresenter.GetOrderInfoList(userName,"5","1","1");
            mPresenter.GetOrderByhmalluserid(userName);
        } else {
            mTvUsername.setText("未登录");
            mTvPhone.setVisibility(View.GONE);
        }

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (!"".equals(userName) && !"".equals(userKey)) {
                    mPresenter.GetUserInfoList(userName, "1");
                    mPresenter.PersonalInformation(userKey);
                    mPresenter.GetHistoryVisite(userKey);
                    i=0;
                    mPresenter.GetOrderByhmalluserid(userName);
//                    mPresenter.GetOrderByhmall(userName);
                }

                refreshlayout.finishRefresh(1000);
            }
        });
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);


    }
/*
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            return;
        }else {
            mPresenter.PersonalInformation(userKey);
        }
    }*/

    @Override
    protected void initView() {
        EvalateDialog = new AlertDialog.Builder(mActivity).setView(view).create();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if (!"PersonalInformation".equals(name)) {
            return;
        }
        if (!"".equals(userName) && !"".equals(userKey)) {
            mPresenter.GetUserInfoList(userName, "1");
            mPresenter.PersonalInformation(userKey);
            mPresenter.GetHistoryVisite(userKey);
        }
    }

    @Override
    protected void setListener() {
        mIvAvatar.setOnClickListener(this);
        mIvSetting.setOnClickListener(this);
        mLlCoupon.setOnClickListener(this);
        mLlFavorites.setOnClickListener(this);
        mLlAfterSale.setOnClickListener(this);
        mLlFocusOnTheStore.setOnClickListener(this);
        mLlEssentialService.setOnClickListener(this);
        mLlLogistics.setOnClickListener(this);
        mLlService.setOnClickListener(this);
        mLlAllOrders.setOnClickListener(this);
        mLlPendingPayment.setOnClickListener(this);
        mLlToBeDelivered.setOnClickListener(this);
        mLlPendingReceipt.setOnClickListener(this);
        mLlComment.setOnClickListener(this);
        mLlGetWorkOrder.setOnClickListener(this);
        mLlMyPurse.setOnClickListener(this);
        mLlGift.setOnClickListener(this);
        mLlBaby.setOnClickListener(this);
        mLlPurse.setOnClickListener(this);

        mLlFreeInstallation.setOnClickListener(this);
        mLlFreeRepair.setOnClickListener(this);
        mLlCustomerService.setOnClickListener(this);
        mLlCoins.setOnClickListener(this);

        mTvUsername.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ("".equals(userName) && "".equals(userKey)) {
            startActivity(new Intent(mActivity, LoginActivity.class));
            return;
        }
        switch (v.getId()) {
            case R.id.iv_avatar:
                //个人信息
                startActivity(new Intent(mActivity, PersonalInformationActivity.class));
                break;
            case R.id.iv_setting:
                //设置
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.ll_coupon:
                //优惠券
                startActivity(new Intent(mActivity, CouponActivity.class));
                break;
            case R.id.ll_favorites:
                //收藏
                startActivity(new Intent(mActivity, FavoritesActivity.class));
                break;
            case R.id.ll_after_sale:
                //售后
                startActivity(new Intent(mActivity, ReturnActivity.class));
                break;
            case R.id.ll_focus_on_the_store:
                //关注的店铺
                startActivity(new Intent(mActivity, StoreActivity.class));
                break;
            case R.id.ll_my_purse:
                //我的钱包
                startActivity(new Intent(mActivity, WalletActivity.class));
                break;
            case R.id.ll_essential_service:
                //发起服务
                startActivity(new Intent(mActivity, WorkOrderActivity.class));
                break;
            case R.id.ll_logistics:
                //物流信息
//                showLogistucs();
                break;
            case R.id.ll_service:
                //服务信息
//                showService();
                break;
            case R.id.ll_get_work_order:
                //获取工单
//                getWorkOrder();
                startActivity(new Intent(mActivity, WorkOrderActivity.class));
                break;
            case R.id.ll_all_orders:
                //订单界面
                intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("intent", "全部");
                startActivity(intent);
                break;
            case R.id.ll_pending_payment:
                bundle = new Bundle();
                bundle.putString("intent", "待付款");
                bundle.putInt("position", 1);
                intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.ll_to_be_delivered:
                bundle = new Bundle();
                bundle.putString("intent", "待发货");
                bundle.putInt("position", 2);
                intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.ll_pending_receipt:
                bundle = new Bundle();
                bundle.putString("intent", "待收货");
                bundle.putInt("position", 3);
                intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.ll_comment:
                bundle = new Bundle();
                bundle.putString("intent", "待评价");
                bundle.putInt("position", 4);
                intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.ll_gift:
                //赠送界面
                startActivity(new Intent(mActivity, GiftActivity.class));
                break;
            case R.id.ll_baby:
                //足迹
                startActivity(new Intent(mActivity, FootprintActivity.class));
                break;
            case R.id.ll_purse:
                //我的钱包
                startActivity(new Intent(mActivity, WalletActivity.class));
                break;

            case R.id.ll_free_installation:
                //免费安装
//                mPresenter.AddOrder("2", "安装", "18767773654", "75", "格力", "250", "冰箱", "251", "单门 容积X≤100", "330000", "330600", "330682", "330682001", "浙江省绍兴市上虞区百官街道 ", "又来", "18767773654", "测试测试测试测试", "42.0", "48", "Y", "N", "N", "0", "0", "1");
//                mPresenter.AddOrder("2", "安装", "18767773654", "", "", "", "", "251", "", "330000", "330600", "330682", "330682001", "浙江省绍兴市上虞区百官街道 ", "又来", "18767773654", "测试测试测试测试", "42.0", "48", "Y", "N", "N", "0", "0", "1");
                intent = new Intent(mActivity, AddWorkOrderActivity.class);
                intent.putExtra("title", "免费安装");
                startActivity(intent);
                break;
            case R.id.ll_free_repair:
                //免费维修
                intent = new Intent(mActivity, AddWorkOrderActivity.class);
                startActivity(intent);
//                mPresenter.AddOrder("1", "维修", "18767773654", "75", "格力", "250", "冰箱", "251", "单门 容积X≤100", "330000", "330600", "330682", "330682001", "浙江省绍兴市上虞区百官街道 ", "又来", "18767773654", "测试测试测试测试", "42.0", "48", "Y", "N", "N", "0", "0", "1");
//                mPresenter.AddOrder("1", "维修", "18767773654", "", "", "", "", "251", "", "330000", "330600", "330682", "330682001", "浙江省绍兴市上虞区百官街道 ", "又来", "18767773654", "测试测试测试测试", "42.0", "48", "Y", "N", "N", "0", "0", "1");
                break;
            case R.id.ll_customer_service:
                final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
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
            case R.id.ll_coins:
                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                Button btn_share_one = under_review.findViewById(R.id.btn_share_one);
                ImageView iv_code_one = under_review.findViewById(R.id.iv_code_one);
                Button btn_go_to_the_mall = under_review.findViewById(R.id.btn_go_to_the_mall);
                Bitmap bitmap = ZXingUtils.createQRImage("http://admin.xigyu.com/sign?phone=" + userName + "&type=7", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.iconn));
                iv_code_one.setImageBitmap(bitmap);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        underReviewDialog.dismiss();
//                        mShareAction.open();
                    }
                });

                btn_go_to_the_mall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        openShopApp("com.zhenghaikj.shop");
                        underReviewDialog.dismiss();
                    }
                });

                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
                        .create();
                underReviewDialog.show();
                window = underReviewDialog.getWindow();
//                window.setContentView(under_review);
                WindowManager.LayoutParams lp = window.getAttributes();
                window.setAttributes(lp);
//                window.setDimAmount(0.1f);
                window.setBackgroundDrawable(new ColorDrawable());
                break;

            case R.id.tv_username:
//                showOrderEvaluate();
                break;

            default:
                break;
        }
    }


    private ArrayList<Product> logisticsList = new ArrayList<>();

    /*private void showLogistucs() {
        customDialog = new CustomDialog(getContext());
        customDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        customDialog.show();
        customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                customDialog.dismiss();
            }
        });

        rv_logistics = customDialog.findViewById(R.id.rv_logistics);
        for (int i = 0; i < 10; i++) {
            logisticsList.add(new Product());
        }
        LogisticsAdapter logisticsAdapter = new LogisticsAdapter(R.layout.item_logistics, logisticsList);
        rv_logistics.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_logistics.setAdapter(logisticsAdapter);
    }*/


    private List<Track> ServiceList = new ArrayList<>();

    private void showService(String id,String state,String name) {
        mPresenter.GetOrderRecordByOrderID(id);
        serviceDialog = new ServiceDialog(getContext());
        serviceDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        serviceDialog.show();
        serviceDialog.setNoOnclickListener("取消", new ServiceDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                serviceDialog.dismiss();
            }
        });
        Log.d(TAG,"ServiceList"+state);
//        serviceDialog.setOrderId("订单号："+id);
//        serviceDialog.setState(state);
//        serviceDialog.setTitle(name);
        TextView tv_logistics_status =serviceDialog.findViewById(R.id.tv_logistics_status);
        TextView titleTv = serviceDialog.findViewById(R.id.tv_goods_name);
        TextView tv_order_number = serviceDialog.findViewById(R.id.tv_order_number);
        ImageView iv_copy=serviceDialog.findViewById(R.id.iv_copy);
        iv_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClip = ClipData.newPlainText("", id);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
            }
        });
        tv_logistics_status.setText(state);
        titleTv.setText(name);
        tv_order_number.setText("订单号："+id);
        rv_service = serviceDialog.findViewById(R.id.rv_service);
//        for (int i = 0; i < 10; i++) {
//            ServiceList.add(new Product());
//        }

//        Log.d(TAG,"ServiceList"+ServiceList);
        serviceAdapter = new ServiceAdapter(R.layout.item_service, ServiceList);
        rv_service.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_service.setAdapter(serviceAdapter);
    }

    private void getWorkOrder() {
        WordOrderDialog wordOrderDialog = new WordOrderDialog(getContext());
        wordOrderDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        wordOrderDialog.show();
    }

    @Override
    public void PersonalInformation(PersonalInformation result) {
        if (result.isSuccess()) {
            if (result.getUserName().equals(result.getCellPhone())) {
                mTvUsername.setText("未设置昵称");
            } else {
                mTvUsername.setText(result.getUserName());
            }

            mTvPhone.setText(result.getCellPhone());
            /*设置头像*/
            if (result.getPhoto() == null || "".equals(result.getPhoto())) {//显示默认头像
//                return;
            } else {
//                byte[] decode;
//                decode = Base64.decode(result.getPhoto(), Base64.DEFAULT);
                Glide.with(mActivity).asBitmap().load(result.getPhoto()).into(mIvAvatar);
            }
            mTvCoupon.setText(result.getCounpon());
            mTvFocusOnTheStore.setText(result.getFavoriteShop());
            mTvFavorites.setText(result.getFavoriteProduct());

            mTvCountDfk.setText(result.getWaitingForPay());
            mTvCountDfh.setText(result.getWaitingForDelivery());
            mTvCountDsh.setText(result.getWaitingForRecieve());
            mTvCountDpj.setText(result.getWaitingForComments());
            mTvCountSh.setText(result.getRefundOrders());
            if ("0".equals(result.getWaitingForPay())) {
                mTvCountDfk.setVisibility(View.GONE);
            } else {
                mTvCountDfk.setVisibility(View.VISIBLE);
            }
            if ("0".equals(result.getWaitingForDelivery())) {
                mTvCountDfh.setVisibility(View.GONE);
            } else {
                mTvCountDfh.setVisibility(View.VISIBLE);
            }
            if ("0".equals(result.getWaitingForRecieve())) {
                mTvCountDsh.setVisibility(View.GONE);
            } else {
                mTvCountDsh.setVisibility(View.VISIBLE);
            }
            if ("0".equals(result.getWaitingForComments())) {
                mTvCountDpj.setVisibility(View.GONE);
            } else {
                mTvCountDpj.setVisibility(View.VISIBLE);
            }
            if ("0".equals(result.getRefundOrders())) {
                mTvCountSh.setVisibility(View.GONE);
            } else {
                mTvCountSh.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void GetHistoryVisite(HistoryVisite result) {
        if (result.isSuccess()) {
            Log.d(TAG, "数量" + result.getProduct().size());
//            String number=result.getProduct().size();
            mTvBaby.setText("" + result.getProduct().size());
        }
    }


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                if (Result.getData().getData() == null) {

                } else {
                    userInfo = Result.getData().getData().get(0);
                    if (userInfo != null) {
//                        mTvMoney.setText();本月消费
                        mTvWalletBalance.setText(userInfo.getTotalMoney() + "");//钱包余额
                        mTvAccountBalance.setText(userInfo.getCon() + "");//西瓜币
//                        mTvHowMoney.setText();兑换了多少西瓜币
                    }
                }


                break;

            default:
                break;

        }
    }

    @Override
    public void GetOrderByhmall(BaseResult<Data<List<WorkOrder.DataBean>>> Result) {
        switch (Result.getStatusCode()) {
            case 200:

//                Log.d(TAG,"服务完成"+Result.getData().getState());
                if (Result.getData().isItem1()){
                    if (Result.getData().getItem2()!=null){
                        if (Result.getData().getItem2().size()>0){
                            for (int i = 0; i < Result.getData().getItem2().size(); i++) {
                                data = Result.getData().getItem2().get(i);
                                if (data != null) {
                                    if ("服务完成".equals(data.getState())) {
                                        showOrderEvaluate();
                                    }
                                }
                            }
                        }
                    }

                }



                break;
        }
    }

    @Override
    public void EnSureOrder(BaseResult<Data<String>> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                Data<String> data = Result.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
//                    mPresenter.GetOrderInfo(OrderID);
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
//                workOrder = baseResult.getData();
//                if (workOrder.getData()!=null){
////                    workOrderList.addAll(workOrder.getData());
////                    mWorkOrderAdapter.setNewData(workOrderList);
//                    for (int i=0;i<workOrder.getData().size();i++){
//                        name.add(workOrder.getData().get(i).getBrandName()+"/"+workOrder.getData().get(i).getCategoryName()+"/"+workOrder.getData().get(i).getSubCategoryName()+"/"+workOrder.getData().get(i).getMemo());
//                        orderId.add(workOrder.getData().get(i).getOrderID());
//                        state.add(workOrder.getData().get(i).getState());
//                    }
//
//                }else {
//                    mLlService.setVisibility(View.GONE);
//                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }
    }

    @Override
    public void GetOrderByhmalluserid(BaseResult<Data<List<WorkOrder.DataBean>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    datalist =baseResult.getData().getItem2();
                    mScrolltv.removeAllViews();
                    mScrolltv.initView(R.layout.item_switchview,  new SwitchView.ViewBuilder() {
                        @Override
                        public void initView(View view) {
                            TextView tv_name=(TextView) view.findViewById(R.id.tv_name);
                            TextView tv_orderid=(TextView) view.findViewById(R.id.tv_orderid);
                            TextView tv_state=(TextView) view.findViewById(R.id.tv_state);
                            ImageView iv_copy=(ImageView) view.findViewById(R.id.iv_copy);
                            LinearLayout ll_swith=(LinearLayout) view.findViewById(R.id.ll_swith);

                            tv_name.setText(datalist.get(i%datalist.size()).getBrandName()+"/"+datalist.get(i%datalist.size()).getCategoryName()+"/"+datalist.get(i%datalist.size()).getSubCategoryName()+"/"+datalist.get(i%datalist.size()).getMemo());
                            tv_orderid.setText(datalist.get(i%datalist.size()).getOrderID());
                            tv_state.setText(datalist.get(i%datalist.size()).getState());
//
                            tv_name.setTag(i);

                            i++;
                            if (i==datalist.size()){
                                i=0;
                            }

                            iv_copy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String id = tv_orderid.getText().toString();
                                    myClip = ClipData.newPlainText("", id);
                                    myClipboard.setPrimaryClip(myClip);
                                    ToastUtils.showShort("复制成功");
                                }
                            });

                            ll_swith.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String id = tv_orderid.getText().toString();
                                    String state=tv_state.getText().toString();
                                    String name=tv_name.getText().toString();
                                    showService(id,state,name);
                                }
                            });
                        }
                    });
//                    workOrderList.addAll(workOrder.getData());
//                    mWorkOrderAdapter.setNewData(workOrderList);
//                    for (int i = 0; i < baseResult.getData().getItem2().size(); i++) {
//                        name.add(baseResult.getData().getItem2().get(i).getBrandName() + "/" + baseResult.getData().getItem2().get(i).getCategoryName() + "/" + baseResult.getData().getItem2().get(i).getSubCategoryName() + "/" + baseResult.getData().getItem2().get(i).getMemo());
//                        name[i]=baseResult.getData().getItem2().get(i).getBrandName() + "/" + baseResult.getData().getItem2().get(i).getCategoryName() + "/" + baseResult.getData().getItem2().get(i).getSubCategoryName() + "/" + baseResult.getData().getItem2().get(i).getMemo();
//                        orderId.add(baseResult.getData().getItem2().get(i).getOrderID());
//                        state.add(baseResult.getData().getItem2().get(i).getState());
//                    }

                } else {
                    mLlService.setVisibility(View.GONE);
                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }
    }

    @Override
    public void GetOrderRecordByOrderID(BaseResult<List<Track>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                ServiceList=baseResult.getData();
                Log.d(TAG,"ServiceList2"+ServiceList);
                serviceAdapter.setNewData(ServiceList);
                break;
            case 401:
                break;
        }
    }

    /*弹出确认工单评价*/
    public void showOrderEvaluate() {
        view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_evaluate, null);
        iv_close = view.findViewById(R.id.iv_close);
        tv_orderid = view.findViewById(R.id.tv_orderid);
        tv_serach = view.findViewById(R.id.tv_serach);
        TextView tv_orderid = view.findViewById(R.id.tv_orderid);
        tv_orderid.setText("工单号：" + data.getOrderID());
        tv_totle_grade = view.findViewById(R.id.tv_totle_grade);
        good_star = view.findViewById(R.id.good_star);
        tv_good_content = view.findViewById(R.id.tv_good_content);
        shangmen_star = view.findViewById(R.id.shangmen_star);
        tv_shangmen_content = view.findViewById(R.id.tv_shangmen_content);
        weixiu_star = view.findViewById(R.id.weixiu_star);
        tv_weixiu_content = view.findViewById(R.id.tv_weixiu_content);
        fuwu_star = view.findViewById(R.id.fuwu_star);
        tv_fuwu_content = view.findViewById(R.id.tv_fuwu_content);
        tv_submit1 = view.findViewById(R.id.tv_submit);
        et_content1 = view.findViewById(R.id.et_content);
        String content = et_content1.getText().toString();

        tv_serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(mActivity, WorkOrderDetailActivity.class);
                intent1.putExtra("OrderID", data.getOrderID());
                startActivity(intent1);
            }
        });

        good_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starRating = good_star.getStarRating();
                setStarName(tv_totle_grade, starRating);
                setStarName2(tv_good_content, starRating);
            }
        });

        shangmen_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starRating1 = shangmen_star.getStarRating();
                setStarName2(tv_shangmen_content, starRating1);
            }
        });
        weixiu_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starRating2 = weixiu_star.getStarRating();
                setStarName2(tv_weixiu_content, starRating2);
            }
        });
        fuwu_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starRating3 = fuwu_star.getStarRating();
                setStarName2(tv_fuwu_content, starRating3);
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvalateDialog.dismiss();
            }
        });


        tv_submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG,"starRating"+starRating+"starRating1"+starRating1+"starRating2"+starRating2+"starRating3"+starRating3+"....");
                mPresenter.EnSureOrder(data.getOrderID(), "888888", String.valueOf(starRating), String.valueOf(starRating1), String.valueOf(starRating2), String.valueOf(starRating3), content);
                EvalateDialog.dismiss();
            }
        });


        EvalateDialog = new AlertDialog.Builder(mActivity).setView(view).create();
        EvalateDialog.show();
        window = EvalateDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());
    }

    /**
     * 设置星星文字
     */
    private void setStarName(TextView textView, float star_num) {
        if (star_num == 5.0f) {
            textView.setText("5");
        } else if (star_num == 4.0f) {
            textView.setText("4");
        } else if (star_num == 3.0f) {
            textView.setText("3");
        } else if (star_num == 2.0f) {
            textView.setText("2");
        } else if (star_num == 1.0f) {
            textView.setText("1");
        }
    }

    /**
     * 设置星星文字
     */
    private void setStarName2(TextView textView, float star_num) {
        if (star_num == 5.0f) {
            textView.setText("很棒");
        } else if (star_num == 4.0f) {
            textView.setText("满意");
        } else if (star_num == 3.0f) {
            textView.setText("一般");
        } else if (star_num == 2.0f) {
            textView.setText("很差");
        } else if (star_num == 1.0f) {
            textView.setText("非常差");
        }
    }

}
