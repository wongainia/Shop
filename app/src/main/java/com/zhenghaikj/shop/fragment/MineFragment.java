package com.zhenghaikj.shop.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.AddWorkOrderActivity;
import com.zhenghaikj.shop.activity.ChageUserNameActivity;
import com.zhenghaikj.shop.activity.CouponActivity;
import com.zhenghaikj.shop.activity.FavoritesActivity;
import com.zhenghaikj.shop.activity.FootprintActivity;
import com.zhenghaikj.shop.activity.GiftActivity;
import com.zhenghaikj.shop.activity.LoginActivity;
import com.zhenghaikj.shop.activity.MessageActivity;
import com.zhenghaikj.shop.activity.OrderActivity;
import com.zhenghaikj.shop.activity.PersonalInformationActivity;
import com.zhenghaikj.shop.activity.ReturnActivity;
import com.zhenghaikj.shop.activity.SettingActivity;
import com.zhenghaikj.shop.activity.SettingPayPasswordActivity;
import com.zhenghaikj.shop.activity.StoreActivity;
import com.zhenghaikj.shop.activity.WalletActivity;
import com.zhenghaikj.shop.activity.WebActivity;
import com.zhenghaikj.shop.activity.WorkOrderActivity;
import com.zhenghaikj.shop.activity.WorkOrderDetailActivity;
import com.zhenghaikj.shop.adapter.LogisticsAdapter;
import com.zhenghaikj.shop.adapter.ServiceAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.dialog.CustomDialog;
import com.zhenghaikj.shop.dialog.ServiceDialog;
import com.zhenghaikj.shop.dialog.WordOrderDialog;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.Track;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.MineContract;
import com.zhenghaikj.shop.mvp.model.MineModel;
import com.zhenghaikj.shop.mvp.presenter.MinePresenter;
import com.zhenghaikj.shop.utils.GlideUtil;
import com.zhenghaikj.shop.utils.ZXingUtils;
import com.zhenghaikj.shop.widget.CircleImageView;
import com.zhenghaikj.shop.widget.StarBarView;
import com.zhenghaikj.shop.widget.SwitchView;
import com.zhenghaikj.shop.widget.paypassword.PasswordEditText;
import com.zhenghaikj.shop.widget.paypassword.PayPasswordView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MineFragment extends BaseLazyFragment<MinePresenter, MineModel> implements View.OnClickListener, MineContract.View , PasswordEditText.PasswordFullListener{

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
    @BindView(R.id.fl_message)
    FrameLayout mFlMessage;
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
    @BindView(R.id.scroll_express)
    SwitchView mScrollExpress;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.ll_become_master)
    LinearLayout mLlBecomeMaster;
    @BindView(R.id.fl_info)
    FrameLayout mFlInfo;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.tv_count_msg)
    TextView mTvCountMsg;

    private CustomDialog customDialog;
    private RecyclerView rv_logistics;
    private RecyclerView rv_service;
    private Bundle bundle;
    private Intent intent;
    private int pageIndex;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private UserInfo.UserInfoDean userInfo;
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
    private int i = 0;
    private int j = 0;
    private ClipData myClip;
    private ClipboardManager myClipboard;
    private ServiceAdapter serviceAdapter;
    private List<WorkOrder.DataBean> datalist;
    private CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private ShareAction mShareAction1;
    private Order order;
    private List<Logistics> expresslist;
    private ImageView iv_goods;
    private String expressNum;
    private Button btn_share_one;
    private ImageView iv_code_one;
    private Button btn_go_to_the_mall;
    private Bitmap bitmap;
    private TextView tv_undone;
    private BottomSheetDialog bottomSheetDialog;
    private String content;
    private int paytype;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

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

        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new CustomShareListener(mActivity);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("复制文本")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();

                        } else {
                            RxPermissions rxPermissions = new RxPermissions(mActivity);
                            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                    .subscribe(new Consumer<Boolean>() {
                                        @Override
                                        public void accept(Boolean aBoolean) throws Exception {
                                            if (aBoolean) {
                                                // 获取全部权限成功

                                                UMWeb web = new UMWeb("http://admin.xigyu.com/NewSign?phone=" + UserID + "&type=8");
                                                web.setTitle("西瓜鱼");
                                                web.setDescription("注册送西瓜币了！！！！！");
                                                web.setThumb(new UMImage(mActivity, R.drawable.shop));
                                                new ShareAction(mActivity).withMedia(web)
                                                        .setPlatform(share_media)
                                                        .setCallback(mShareListener)
                                                        .share();
                                            } else {
                                                // 获取全部权限失败
                                                ToastUtils.showShort("权限获取失败");
                                            }
                                        }
                                    });

                        }
                    }
                });

        mShareAction1 = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("复制文本")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();

                        } else {
                            RxPermissions rxPermissions = new RxPermissions(mActivity);
                            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                    .subscribe(new Consumer<Boolean>() {
                                        @Override
                                        public void accept(Boolean aBoolean) throws Exception {
                                            if (aBoolean) {
                                                // 获取全部权限成功

                                                UMWeb web = new UMWeb("https://sj.qq.com/myapp/detail.htm?apkName=com.ying.administrator.masterappdemo");
                                                web.setTitle("西瓜鱼");
                                                web.setDescription("注册送西瓜币了！！！！！");
                                                web.setThumb(new UMImage(mActivity, R.drawable.shop));
                                                new ShareAction(mActivity).withMedia(web)
                                                        .setPlatform(share_media)
                                                        .setCallback(mShareListener)
                                                        .share();
                                            } else {
                                                // 获取全部权限失败
                                                ToastUtils.showShort("权限获取失败");
                                            }
                                        }
                                    });

                        }
                    }
                });
        getData();
        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                i=0;
                getData();
                refreshlayout.finishRefresh(1000);
            }
        });
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }
    public void getData(){
        if (isLogin) {
            mPresenter.GetUserInfoList(UserID, "1");
            mPresenter.PersonalInformation(userKey);
            mPresenter.GetOrderByhmall(UserID);
            mPresenter.GetOrderByhmalluserid(UserID,"0");
            mPresenter.GetList("4","10","1",userKey);

            mTvPhone.setVisibility(View.VISIBLE);
            mTvUsername.setVisibility(View.VISIBLE);
            mTvLogin.setVisibility(View.GONE);
            mLlService.setVisibility(View.VISIBLE);
        } else {
            mTvPhone.setVisibility(View.GONE);
            mTvUsername.setVisibility(View.GONE);
            mTvLogin.setVisibility(View.VISIBLE);
            mLlService.setVisibility(View.GONE);
            mTvCountMsg.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView() {
        EvalateDialog = new AlertDialog.Builder(mActivity).setView(view).create();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("更新登录信息".equals(name)){
            getLoginMsg();
            getData();
        }
        if (isLogin) {
            if ("UserName".equals(name)) {
                mPresenter.GetUserInfoList(UserID, "1");
            }
            if ("UpdateReadCount".equals(name)) {
                mPresenter.GetList("4","10","1",userKey);
            }
                if ("UpdateOrderCount".equals(name)) {//更新各种数量
                mPresenter.PersonalInformation(userKey);
            }
            if ("待评价".equals(name)) {//更新各种数量
                mPresenter.PersonalInformation(userKey);
            }
            if (!"PersonalInformation".equals(name)) {
                return;
            }
            mPresenter.GetUserInfoList(UserID, "1");
            mPresenter.PersonalInformation(userKey);
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
        mFlMessage.setOnClickListener(this);

        mLlBecomeMaster.setOnClickListener(this);
        mLlMerchant.setOnClickListener(this);
        mFlInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!isLogin) {
            startActivity(new Intent(mActivity, LoginActivity.class));
            return;
        }
        switch (v.getId()) {
            case R.id.fl_info:
            case R.id.iv_avatar:
                //个人信息
                startActivity(new Intent(mActivity, PersonalInformationActivity.class));
                break;
            case R.id.iv_setting:
                //setting
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.fl_message:
                intent = new Intent(mActivity, MessageActivity.class);
                intent.putExtra("categoryId","4");
                intent.putExtra("title","消息");
                startActivity(intent);
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
//                mPresenter.GetExpressInfo("3969830383918");
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
                mActivity.overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
                break;
            case R.id.ll_pending_payment:
                bundle = new Bundle();
                bundle.putString("intent", "待付款");
                bundle.putInt("position", 1);
                intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                mActivity.overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
                break;
            case R.id.ll_to_be_delivered:
                bundle = new Bundle();
                bundle.putString("intent", "待发货");
                bundle.putInt("position", 2);
                intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                mActivity.overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
                break;
            case R.id.ll_pending_receipt:
                bundle = new Bundle();
                bundle.putString("intent", "待收货");
                bundle.putInt("position", 3);
                intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                mActivity.overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
                break;
            case R.id.ll_comment:
                bundle = new Bundle();
                bundle.putString("intent", "待评价");
                bundle.putInt("position", 4);
                intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                mActivity.overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
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
//                intent = new Intent(mActivity, IntegralUseActivity.class);
//                intent.putExtra("intent","全部");
//                startActivity(intent);
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
            case R.id.ll_become_master:
                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                btn_share_one = under_review.findViewById(R.id.btn_share_one);
                iv_code_one = under_review.findViewById(R.id.iv_code_one);
                btn_go_to_the_mall = under_review.findViewById(R.id.btn_go_to_the_mall);
                TextView tv_name = under_review.findViewById(R.id.tv_name);
                tv_name.setText("扫描下载师傅端APP，成为师傅");
                bitmap = ZXingUtils.createQRImage("https://sj.qq.com/myapp/detail.htm?apkName=com.ying.administrator.masterappdemo", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.shop));
                iv_code_one.setImageBitmap(bitmap);
                btn_share_one.setText("立即下载");
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        underReviewDialog.dismiss();
//                        mShareAction1.open();
                        openBrowser(mActivity,"https://sj.qq.com/myapp/detail.htm?apkName=com.ying.administrator.masterappdemo");
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
            case R.id.ll_coins:
                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                btn_share_one = under_review.findViewById(R.id.btn_share_one);
                iv_code_one = under_review.findViewById(R.id.iv_code_one);
                btn_go_to_the_mall = under_review.findViewById(R.id.btn_go_to_the_mall);
                bitmap = ZXingUtils.createQRImage("http://admin.xigyu.com/sign?NewSign=" + UserID + "&type=8", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.shop));
                iv_code_one.setImageBitmap(bitmap);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        underReviewDialog.dismiss();
                        mShareAction.open();
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
                WindowManager.LayoutParams lp1 = window.getAttributes();
                window.setAttributes(lp1);
//                window.setDimAmount(0.1f);
                window.setBackgroundDrawable(new ColorDrawable());
                break;

            case R.id.tv_username:
//                showOrderEvaluate();
                startActivity(new Intent(mActivity, ChageUserNameActivity.class));
                break;
            case R.id.ll_merchant:
                intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("Url","http://mall.xigyu.com/m-weixin/shopregister/step1");
                intent.putExtra("Title","商家入驻");
                startActivity(intent);
//                startActivity(new Intent(mActivity, BecomeBusinessFirstActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 调用第三方浏览器打开
     *
     * @param context
     * @param url     要浏览的资源地址
     */
    public static void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
//        if (intent.resolveActivity(context.getPackageManager()) != null) {
//            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
//            // 打印Log   ComponentName到底是什么
//            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
//        } else {
//            Toast.makeText(context.getApplicationContext(), "请下载浏览器", Toast.LENGTH_SHORT).show();
//        }
    }


    private ArrayList<Logistics> logisticsList = new ArrayList<>();
    private LogisticsAdapter logisticsAdapter = new LogisticsAdapter(R.layout.item_logistics, logisticsList);

    private void showLogistucs(int number) {
        customDialog = new CustomDialog(getContext());
        customDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        customDialog.show();
        customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                customDialog.dismiss();
            }
        });

        ImageView iv_goods_picture = customDialog.findViewById(R.id.iv_goods_picture);
        GlideUtil.loadImageViewLoding(mActivity, order.getOrders().get(number).getItemInfo().get(number).getImage(), iv_goods_picture, R.drawable.image_loading, R.drawable.image_loading);

        TextView tv_goods_name = customDialog.findViewById(R.id.tv_goods_name);
        tv_goods_name.setText(order.getOrders().get(number).getItemInfo().get(number).getProductName());

        TextView tv_express_delivery = customDialog.findViewById(R.id.tv_express_delivery);
        tv_express_delivery.setText("快递单号：" + expressNum);


        rv_logistics = customDialog.findViewById(R.id.rv_logistics);
//        for (int i = 0; i < 10; i++) {
//            logisticsList.add(new Logistics());
//        }

        rv_logistics.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_logistics.setAdapter(logisticsAdapter);
    }


    private List<Track> ServiceList = new ArrayList<>();

    private void showService(String id, String state, String name,String type) {
        mPresenter.GetOrderRecordByOrderID(id);
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_service, null);
        Log.d(TAG, "ServiceList" + state);
//        serviceDialog.setOrderId("订单号："+id);
//        serviceDialog.setState(state);
//        serviceDialog.setTitle(name);
        TextView tv_logistics_status = under_review.findViewById(R.id.tv_logistics_status);
        TextView titleTv = under_review.findViewById(R.id.tv_goods_name);
        TextView tv_type=under_review.findViewById(R.id.tv_type);
        TextView tv_order_number = under_review.findViewById(R.id.tv_order_number);
        ImageView iv_copy = under_review.findViewById(R.id.iv_copy);
        TextView tv_reminders = under_review.findViewById(R.id.tv_reminders);
        ImageView iv_close=under_review.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });
        tv_reminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminders(id);
            }
        });

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
        tv_type.setText(type);
        tv_order_number.setText("订单号：" + id);
        rv_service = under_review.findViewById(R.id.rv_service);
//        for (int i = 0; i < 10; i++) {
//            ServiceList.add(new Product());
//        }

//        Log.d(TAG,"ServiceList"+ServiceList);
        serviceAdapter = new ServiceAdapter(R.layout.item_service, ServiceList);
        rv_service.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_service.setAdapter(serviceAdapter);
        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
                .create();
        underReviewDialog.show();
        window = underReviewDialog.getWindow();
//                window.setContentView(under_review);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
//                window.setDimAmount(0.1f);
        window.setBackgroundDrawable(new ColorDrawable());
    }

    private void reminders(String id) {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_reminders, null);
        EditText et_memo = under_review.findViewById(R.id.et_memo);
        Button negtive = under_review.findViewById(R.id.negtive);
        Button positive = under_review.findViewById(R.id.positive);
        negtive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_memo.getText().toString();
                mPresenter.PressWokerAccount(id, content);
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
    }

    private void getWorkOrder() {
        WordOrderDialog wordOrderDialog = new WordOrderDialog(getContext());
        wordOrderDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        wordOrderDialog.show();
    }

    @Override
    public void GetList(Announcement result) {
        if (result.getCount()>0){
            mTvCountMsg.setVisibility(View.VISIBLE);
            mTvCountMsg.setText(result.getCount()+"");
        }else{
            mTvCountMsg.setVisibility(View.GONE);
        }
    }

    @Override
    public void PersonalInformation(PersonalInformation result) {
        if (result.isSuccess()) {
//            if (result.getUserName().equals(result.getCellPhone())) {
//                mTvUsername.setText("未设置昵称");
//            } else {
//                mTvUsername.setText(result.getNick());
//            }
//            mTvUsername.setText(result.getNick());
            String mobile = result.getCellPhone();
            String maskNumber = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
            mTvPhone.setText(maskNumber);
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

            mTvBaby.setText(result.getHistoryVisitCount());
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
                        if (userInfo.getNickName().equals(userInfo.getUserID())) {
                            mTvUsername.setText("未设置昵称");
                        } else {
                            mTvUsername.setText(userInfo.getNickName());
                        }
//                        mTvMoney.setText();本月消费
//                        mTvWalletBalance.setText(userInfo.getTotalMoney() + "");//钱包余额
                        mTvAccountBalance.setText(String.format("%.2f", userInfo.getCon()));//西瓜币
                        mTvWalletBalance.setText(String.format("%.2f", userInfo.getCon()));//西瓜币
//                        mTvBalance.setText(userInfo.getCon() + "");//西瓜币
                        mTvBalance.setText("¥" + String.format("%.2f", userInfo.getCon()));
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
                if (Result.getData().isItem1()) {

                    if (Result.getData().getItem2() == null) {
                        return;
                    }
                    if (Result.getData().getItem2().size() > 0) {
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
                    if (EvalateDialog != null) {
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
                    datalist = baseResult.getData().getItem2();
                    if (datalist == null||datalist.size()==0) {
                        mLlService.setVisibility(View.GONE);
                    } else {
                        mLlService.setVisibility(View.VISIBLE);
                        mScrolltv.removeAllViews();
                        mScrolltv.initView(R.layout.item_switchview, new SwitchView.ViewBuilder() {
                            @Override
                            public void initView(View view) {
                                TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                                TextView tv_orderid = (TextView) view.findViewById(R.id.tv_orderid);
                                TextView tv_state = (TextView) view.findViewById(R.id.tv_state);
                                ImageView iv_copy = (ImageView) view.findViewById(R.id.iv_copy);
                                LinearLayout ll_swith = (LinearLayout) view.findViewById(R.id.ll_swith);
                                TextView tv_type=(TextView) view.findViewById(R.id.tv_type);

                                tv_name.setText( datalist.get(i % datalist.size()).getMemo());
                                tv_type.setText(datalist.get(i%datalist.size()).getTypeName());
                                tv_orderid.setText(datalist.get(i % datalist.size()).getOrderID());
                                tv_state.setText(datalist.get(i % datalist.size()).getState());
//
                                tv_name.setTag(i);

                                i++;
                                if (i == datalist.size()) {
                                    i = 0;
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
                                        String state = tv_state.getText().toString();
                                        String name = tv_name.getText().toString();
                                        String type=tv_type.getText().toString();
                                        showService(id, state, name,type);
                                    }
                                });
                            }
                        });
                    }

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

                ServiceList = baseResult.getData();
                Log.d(TAG, "ServiceList2" + ServiceList);
                serviceAdapter.setNewData(ServiceList);
                break;
            case 401:
                break;
        }
    }

    @Override
    public void PressWokerAccount(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort(baseResult.getData().getItem2());
                } else {
                    ToastUtils.showShort(baseResult.getData().getItem2());
                }
                break;
        }
    }

    @Override
    public void GetOrders(Order result) {
        if (result.isSuccess()) {
            if (result.getOrders() != null) {
                order = result;
                for (int i = 0; i < order.getOrders().size(); i++) {
                    mPresenter.GetExpress(order.getOrders().get(i).getId(), userKey);

                }

            }
        }
    }

    @Override
    public void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getItem2() != null) {
                    expresslist = baseResult.getData().getItem2();
                    mScrollExpress.removeAllViews();
                    mScrollExpress.initView(R.layout.item_express, new SwitchView.ViewBuilder() {
                        @Override
                        public void initView(View view) {
                            LinearLayout ll_express = (LinearLayout) view.findViewById(R.id.ll_express);
                            TextView tv_express_information = (TextView) view.findViewById(R.id.tv_express_information);
                            iv_goods = (ImageView) view.findViewById(R.id.iv_goods);
//                            Log.d(TAG,"order.getOrders()"+order.getOrders());
//                            for (int k=0;k<order.getOrders().size();k++){
                            Log.d(TAG, "order.getOrders()" + order.getOrders().size());
//                                GlideUtil.loadImageViewLoding(mActivity,order.getOrders().get(k).getItemInfo().get(k).getImage(), iv_goods,R.drawable.image_loading,R.drawable.image_loading);
                            tv_express_information.setText(expresslist.get(j).getContent());
                            logisticsList.clear();
                            logisticsList.addAll(expresslist);
//                            Log.d(TAG,"order.getOrders()"+logisticsList);
                            logisticsAdapter.setNewData(logisticsList);
                            int number = j;
                            ll_express.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToastUtils.showShort("" + number);
//                                    showLogistucs(number);
                                }
                            });
//                            }

                            j++;
                            if (j == order.getOrders().size()) {
                                j = 0;
                            }


                        }
                    });
                }
                break;
        }
    }


    @Override
    public void GetExpress(Express Result) {
        if (Result.isSuccess()) {
            expressNum = Result.getExpressNum();
//            mPresenter.GetExpressInfo(Result.getExpressNum());


        }
    }

    /*弹出确认工单评价*/
    public void showOrderEvaluate() {
        view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_evaluate, null);
        iv_close = view.findViewById(R.id.iv_close);
        tv_orderid = view.findViewById(R.id.tv_orderid);
        tv_serach = view.findViewById(R.id.tv_serach);
        tv_undone = view.findViewById(R.id.tv_undone);
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
        content = et_content1.getText().toString();

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
                if ("".equals(userInfo.getPayPassWord())){
                    startActivity(new Intent(mActivity, SettingPayPasswordActivity.class));
                }else {
                    paytype=1;
                    openPayPasswordDialog();
                }

//                Log.d(TAG,"starRating"+starRating+"starRating1"+starRating1+"starRating2"+starRating2+"starRating3"+starRating3+"....");

//                EvalateDialog.dismiss();
            }
        });

        tv_undone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(userInfo.getPayPassWord())){
                    startActivity(new Intent(mActivity, SettingPayPasswordActivity.class));
                }else {
                    paytype=2;
                    openPayPasswordDialog();
                }
            }
        });


        EvalateDialog = new AlertDialog.Builder(mActivity).setView(view).create();
        EvalateDialog.show();
        window = EvalateDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());
    }

    /*支付密码*/
    private void openPayPasswordDialog() {
        PayPasswordView payPasswordView = new PayPasswordView(mActivity);
        bottomSheetDialog = new BottomSheetDialog(mActivity);
        bottomSheetDialog.setContentView(payPasswordView);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();
        /*注册监听*/
        payPasswordView.getmPasswordEditText().setPasswordFullListener(this);
        /*关闭*/
        payPasswordView.getImg_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
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

    @Override
    public void passwordFull(String password) {
        if (userInfo.getPayPassWord().equals(password)){
            if (paytype==1){
                mPresenter.EnSureOrder(data.getOrderID(), password, String.valueOf(starRating), String.valueOf(starRating1), String.valueOf(starRating2), String.valueOf(starRating3), content);
            }else if (paytype==2){
                mPresenter.EnSureOrder(data.getOrderID(), password, "1", String.valueOf(starRating1), String.valueOf(starRating2), String.valueOf(starRating3), content);
            }
            bottomSheetDialog.dismiss();
        }else {
            Toast.makeText(mActivity,"支付密码错误",Toast.LENGTH_SHORT).show();
        }
    }

    public static class CustomShareListener implements UMShareListener {
        private Context mContext;

        public CustomShareListener(Context context) {
            mContext = context;
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
//                Toast.makeText(mContext, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
//                    Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
//                Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

//            Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

}
