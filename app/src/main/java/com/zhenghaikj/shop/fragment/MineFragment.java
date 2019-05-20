package com.zhenghaikj.shop.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
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
import com.zhenghaikj.shop.adapter.ServiceAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CustomDialog;
import com.zhenghaikj.shop.dialog.ServiceDialog;
import com.zhenghaikj.shop.dialog.WordOrderDialog;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.MineContract;
import com.zhenghaikj.shop.mvp.model.MineModel;
import com.zhenghaikj.shop.mvp.presenter.MinePresenter;
import com.zhenghaikj.shop.widget.CircleImageView;
import com.zhenghaikj.shop.widget.StarBarView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

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
        if (!"".equals(userName) && !"".equals(userKey)) {
            mPresenter.GetUserInfoList(userName, "1");
            mPresenter.PersonalInformation(userKey);
            mPresenter.GetHistoryVisite(userKey);
        }

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (!"".equals(userName) && !"".equals(userKey)) {
                    mPresenter.GetUserInfoList(userName, "1");
                    mPresenter.PersonalInformation(userKey);
                    mPresenter.GetHistoryVisite(userKey);
                }
                refreshlayout.finishRefresh(1000);
            }
        });


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
                showService();
                break;
            case R.id.ll_get_work_order:
                //获取工单
                getWorkOrder();
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
                intent.putExtra("title","免费安装");
                startActivity(intent);
                break;
            case R.id.ll_free_repair:
                //免费维修
                intent = new Intent(mActivity, AddWorkOrderActivity.class);
                intent.putExtra("title","免费维修");
                startActivity(intent);
//                mPresenter.AddOrder("1", "维修", "18767773654", "75", "格力", "250", "冰箱", "251", "单门 容积X≤100", "330000", "330600", "330682", "330682001", "浙江省绍兴市上虞区百官街道 ", "又来", "18767773654", "测试测试测试测试", "42.0", "48", "Y", "N", "N", "0", "0", "1");
//                mPresenter.AddOrder("1", "维修", "18767773654", "", "", "", "", "251", "", "330000", "330600", "330682", "330682001", "浙江省绍兴市上虞区百官街道 ", "又来", "18767773654", "测试测试测试测试", "42.0", "48", "Y", "N", "N", "0", "0", "1");
                break;


              case R.id.tv_username:
                  showOrderEvaluate();
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


    private ArrayList<Product> ServiceList = new ArrayList<>();

    private void showService() {
        serviceDialog = new ServiceDialog(getContext());
        serviceDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        serviceDialog.show();
        serviceDialog.setNoOnclickListener("取消", new ServiceDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                serviceDialog.dismiss();
            }
        });

        rv_service = serviceDialog.findViewById(R.id.rv_service);
        for (int i = 0; i < 10; i++) {
            ServiceList.add(new Product());
        }
        ServiceAdapter serviceAdapter = new ServiceAdapter(R.layout.item_service, ServiceList);
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
            mTvUsername.setText(result.getUserName());
            /*设置头像*/
            if (result.getPhoto() == null || "".equals(result.getPhoto())) {//显示默认头像
                return;
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

    /*弹出确认工单评价*/
    public void showOrderEvaluate() {
        view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_evaluate, null);
        iv_close = view.findViewById(R.id.iv_close);
        tv_orderid = view.findViewById(R.id.tv_orderid);
        tv_serach = view.findViewById(R.id.tv_serach);

        tv_totle_grade = view.findViewById(R.id.tv_totle_grade);
        good_star = view.findViewById(R.id.good_star);
        tv_good_content = view.findViewById(R.id.tv_good_content);

        shangmen_star = view.findViewById(R.id.shangmen_star);
        tv_shangmen_content = view.findViewById(R.id.tv_shangmen_content);
        weixiu_star = view.findViewById(R.id.weixiu_star);
        tv_weixiu_content = view.findViewById(R.id.tv_weixiu_content);
        fuwu_star = view.findViewById(R.id.fuwu_star);
        tv_fuwu_content = view.findViewById(R.id.tv_fuwu_content);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
