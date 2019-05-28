package com.zhenghaikj.shop.activity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lwkandroid.widget.stateframelayout.StateFrameLayout;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.OrderDetailAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.JsonStrOrderPay;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.PayResult;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.mvp.contract.OrderDetailContract;
import com.zhenghaikj.shop.mvp.model.OrderDetailModel;
import com.zhenghaikj.shop.mvp.presenter.OrderDetailPresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.widget.paypassword.PasswordEditText;
import com.zhenghaikj.shop.widget.paypassword.PayPasswordView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter, OrderDetailModel> implements View.OnClickListener, OrderDetailContract.View,PasswordEditText.PasswordFullListener {

    private static final String TAG = "OrderDetailActivity";
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
    @BindView(R.id.tv_ship)
    TextView mTvShip;
    @BindView(R.id.tv_time_of_receipt)
    TextView mTvTimeOfReceipt;
    @BindView(R.id.tv_logistics)
    TextView mTvLogistics;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.ll_store)
    LinearLayout mLlStore;
    @BindView(R.id.rv_order_list)
    RecyclerView mRvOrderList;
    @BindView(R.id.tv_total_price_of_goods)
    TextView mTvTotalPriceOfGoods;
    @BindView(R.id.tv_freight)
    TextView mTvFreight;
    @BindView(R.id.tv_freight_insurance)
    TextView mTvFreightInsurance;
    @BindView(R.id.tv_total_order_price)
    TextView mTvTotalOrderPrice;
    @BindView(R.id.tv_real_payment)
    TextView mTvRealPayment;
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.tv_creation_time)
    TextView mTvCreationTime;
    @BindView(R.id.tv_payment_time)
    TextView mTvPaymentTime;
    @BindView(R.id.tv_delivery_time)
    TextView mTvDeliveryTime;
    @BindView(R.id.tv_delete_order)
    TextView mTvDeleteOrder;
    @BindView(R.id.tv_view_logistics)
    TextView mTvViewLogistics;
    @BindView(R.id.tv_evaluation)
    TextView mTvEvaluation;
    @BindView(R.id.ll_all_orders)
    LinearLayout mLlAllOrders;
    @BindView(R.id.tv_see_logistics)
    TextView mTvSeeLogistics;
    @BindView(R.id.tv_extended_receipt)
    TextView mTvExtendedReceipt;
    @BindView(R.id.tv_confirm_receipt)
    TextView mTvConfirmReceipt;
    @BindView(R.id.ll_pending_receipt)
    LinearLayout mLlPendingReceipt;
    @BindView(R.id.tv_friend_pay)
    TextView mTvFriendPay;
    @BindView(R.id.tv_payment)
    TextView mTvPayment;
    @BindView(R.id.tv_buy_again)
    TextView mTvBuyAgain;
    @BindView(R.id.ll_pending_payment)
    LinearLayout mLlPendingPayment;
    @BindView(R.id.tv_change_address)
    TextView mTvChangeAddress;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.ll_to_be_delivered)
    LinearLayout mLlToBeDelivered;
    @BindView(R.id.stateLayout)
    StateFrameLayout mStateLayout;
    @BindView(R.id.rl_serach_shifu)
    RelativeLayout mRl_serach_shifu;
    @BindView(R.id.tv_yuyue)
    TextView mTv_yuyue;
    @BindView(R.id.img_shifu1)
    ImageView mImgShifu1;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.ll_close)
    LinearLayout mLlClose;
    @BindView(R.id.tv_delete2)
    TextView mTvDelete2;

    private String userKey;
    private SPUtils spUtils;

    private ClipboardManager myClipboard;
    private ClipData myClip;


    private List<OrderDetail.OrderBean> orderBeans = new ArrayList<>();
    private List<OrderDetail.OrderItemBean> orderItemBeans = new ArrayList<>();
    private OrderDetailAdapter adapter;
    private String id;
    private OrderDetail.OrderBean orderBean;
    private Intent intent;
    private ArrayList<JsonStrOrderPay> payList;
    private View popupWindow_view;
    private JSONArray jsonArray;
    private String userName;
    private IWXAPI api;
    private WXpayInfo wXpayInfo;
    private String orderinfo;
    private PopupWindow mPopupWindow;
    private BottomSheetDialog bottomSheetDialog;
    private UserInfo.UserInfoDean userInfo;

    private int paytype;  //支付方式：支付密码：1  确认收货：2
    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_detail;
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
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("订单详情");

        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");
        mPresenter.GetUserInfoList(userName,"1");
        id = getIntent().getStringExtra("orderId");
        mPresenter.GetOrderDetail(id, userKey);
        mPresenter.IsMallid(id);

        api = WXAPIFactory.createWXAPI(mActivity, "wx92928bf751e1628e");
        // 将该app注册到微信
        api.registerApp("wx92928bf751e1628e");

        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);


    }

    @Override
    protected void initView() {
        mStateLayout.changeState(StateFrameLayout.LOADING);
        //是否在展示内容布局的时候开启动画（200ms的Alpha动画）
        mStateLayout.enableContentAnim(false);

        //设置网络错误重试监听【不传netRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_net_error_retry"】
        mStateLayout.setOnNetErrorRetryListener(new StateFrameLayout.OnNetErrorRetryListener() {
            @Override
            public void onNetErrorRetry() {
                //TODO 在这里相应重试操作
                mPresenter.GetOrderDetail(id, userKey);
            }
        });
        //设置空数据重试监听【不传emptyRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_empty_retry"】
        mStateLayout.setOnEmptyRetryListener(new StateFrameLayout.OnEmptyRetryListener() {
            @Override
            public void onEmptyRetry() {
                //TODO 在这里相应重试操作
                mPresenter.GetOrderDetail(id, userKey);
            }
        });
        adapter = new OrderDetailAdapter(R.layout.item_order_list, orderItemBeans, "");
        adapter.setEmptyView(getEmptyView());
        mRvOrderList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrderList.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_apply_refund:
                        Intent intent = new Intent(mActivity, AfterSalesTypeActivity.class);
                        intent.putExtra("storeName", orderBean.getShopName());
                        intent.putExtra("product", orderItemBeans.get(position));
                        intent.putExtra("order", orderBean);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                intent = new Intent(mActivity, GoodsDetailActivity.class);
                intent.putExtra("id", orderItemBeans.get(position).getProductId() + "");
                startActivity(intent);
            }
        });


    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvCopy.setOnClickListener(this);

        mTvDelete2.setOnClickListener(this);
        mTvDeleteOrder.setOnClickListener(this);
        mTvConfirmReceipt.setOnClickListener(this);
        mTvPayment.setOnClickListener(this);
        mTvFriendPay.setOnClickListener(this);
        mTvBuyAgain.setOnClickListener(this);
        mTvBuy.setOnClickListener(this);
        mTvExtendedReceipt.setOnClickListener(this);
        mTvSeeLogistics.setOnClickListener(this);
        mTvViewLogistics.setOnClickListener(this);
        mTvLogistics.setOnClickListener(this);
        mTvEvaluation.setOnClickListener(this);
        mTvChangeAddress.setOnClickListener(this);
        mTv_yuyue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_copy:
                myClip = ClipData.newPlainText("", id);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
                break;
            case R.id.tv_buy:
            case R.id.tv_buy_again://再次购买

//                        startActivity(new Intent(mActivity, OrderDetailActivity.class));
//                        mPresenter.PostCloseOrder(id,userKey);
                break;
            case R.id.tv_delete2://删除订单
            case R.id.tv_delete://删除订单
                mPresenter.PostCloseOrder(id,userKey);
                break;
            case R.id.tv_delete_order://取消订单
                Log.d(TAG,"编号："+id);
                mPresenter.PostCloseOrder(id,userKey);
                break;
            case R.id.tv_confirm_receipt://确认收货
                paytype=2;
                openPayPasswordDialog();
                break;
            case R.id.tv_payment://付款
                paytype=1;
                showPopupWindow();
                break;
            case R.id.tv_extended_receipt://延长收货
//                        showPopupWindow();
                break;
            // case R.id.tv_evaluation://评价
            //     showPopupWindow();
            //    break;
            case R.id.tv_change_address://修改地址
//                        showPopupWindow();
                break;
            case R.id.tv_friend_pay://朋友代付
//                        showPopupWindow();
                break;
            case R.id.tv_evaluation:
                Intent intent=new Intent(mActivity, EvaluateActivity.class);
                intent.putExtra("OrderID",id);
                startActivity(intent);
                break;
            case R.id.tv_see_logistics://查看物流
            case R.id.tv_logistics:
            case R.id.tv_view_logistics:
                mPresenter.GetExpressInfo(id,userKey);
                break;
            case R.id.tv_yuyue:
                Intent intent1 = new Intent(mActivity, OrderInstallActivity.class);
                intent1.putExtra("OrderId", id);
                startActivityForResult(intent1, Config.RECEIPT_REQUEST);
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
    public void GetOrderDetail(OrderDetail result) {

        if (result.isSuccess()) {

            orderBean = result.getOrder();
            mTvShip.setText(orderBean.getStatus());
            mTvName.setText(orderBean.getShipTo());
            mTvAddress.setText(orderBean.getAddress());
            mTvPhone.setText(orderBean.getPhone());
            mTvStoreName.setText(orderBean.getShopName());
            mTvTotalPriceOfGoods.setText("￥" + orderBean.getRealTotalAmount());
            mTvTotalOrderPrice.setText("￥" + orderBean.getRealTotalAmount());
            mTvRealPayment.setText("￥" + orderBean.getRealTotalAmount());
            mTvOrderNumber.setText(orderBean.getId());
            mTvCreationTime.setText(orderBean.getOrderDate());
            orderItemBeans.clear();
            orderItemBeans.addAll(result.getOrderItem());
            adapter.setNewData(orderItemBeans);
            adapter.setStatus(orderBean.getOrderStatus());
//           mTvShip.setText(orderBeans.get(0).getStatus());

            if ("5".equals(orderBean.getOrderStatus())||"7".equals(orderBean.getOrderStatus())) {
                mLlPendingPayment.setVisibility(View.INVISIBLE);
                mLlPendingReceipt.setVisibility(View.INVISIBLE);
                mLlAllOrders.setVisibility(View.VISIBLE);
                mLlToBeDelivered.setVisibility(View.INVISIBLE);
                mRl_serach_shifu.setVisibility(View.VISIBLE);
                mLlClose.setVisibility(View.INVISIBLE);
            }

            if ("1".equals(orderBean.getOrderStatus())) {
                mLlPendingPayment.setVisibility(View.VISIBLE);
                mLlPendingReceipt.setVisibility(View.INVISIBLE);
                mLlAllOrders.setVisibility(View.INVISIBLE);
                mLlToBeDelivered.setVisibility(View.INVISIBLE);
                mLlClose.setVisibility(View.GONE);
            }

            if ("2".equals(orderBean.getOrderStatus())) {
                mLlPendingPayment.setVisibility(View.INVISIBLE);
                mLlPendingReceipt.setVisibility(View.INVISIBLE);
                mLlAllOrders.setVisibility(View.INVISIBLE);
                mLlToBeDelivered.setVisibility(View.VISIBLE);
                mRl_serach_shifu.setVisibility(View.VISIBLE);
                mLlClose.setVisibility(View.GONE);
            }

            if ("3".equals(orderBean.getOrderStatus())||"6".equals(orderBean.getOrderStatus())) {
                mLlPendingPayment.setVisibility(View.INVISIBLE);
                mLlPendingReceipt.setVisibility(View.VISIBLE);
                mLlAllOrders.setVisibility(View.INVISIBLE);
                mLlToBeDelivered.setVisibility(View.INVISIBLE);
                mRl_serach_shifu.setVisibility(View.VISIBLE);
                mLlClose.setVisibility(View.GONE);
            }

            if ("4".equals(orderBean.getOrderStatus())) {
                mLlPendingPayment.setVisibility(View.INVISIBLE);
                mLlPendingReceipt.setVisibility(View.INVISIBLE);
                mLlAllOrders.setVisibility(View.INVISIBLE);
                mLlToBeDelivered.setVisibility(View.INVISIBLE);
                mRl_serach_shifu.setVisibility(View.INVISIBLE);
                mLlClose.setVisibility(View.VISIBLE);
            }
        }
        mStateLayout.changeState(StateFrameLayout.SUCCESS);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Throwable e) {
        mStateLayout.changeState(StateFrameLayout.NET_ERROR);
    }


    @Override
    public void PostCloseOrder(CloseOrder Result) {
        if (Result.isSuccess()) {
            mPresenter.GetOrderDetail(id, userKey);
            EventBus.getDefault().post("UpdateOrderCount");
            EventBus.getDefault().post("待付款");
            EventBus.getDefault().post("全部");
        }
    }
    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                if (Result.getData().getData()==null){

                }else {
                    userInfo = Result.getData().getData().get(0);
//                    if (userInfo !=null){
//                        mTvBalance.setText(""+userInfo.getTotalMoney()+"");//钱包余额
//                        mTvWatermelonBalance.setText("￥"+userInfo.getCon()+"");//西瓜币
//                    }
                }


                break;

            default:
                break;

        }
    }

    @Override
    public void MallBalancePay(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    mPresenter.PostChangeOrderState(id);
                    Toast.makeText(mActivity,"支付成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
                    intent.putExtra("OrderID",id);
                    startActivity(intent);
                    bottomSheetDialog.dismiss();
                    OrderDetailActivity.this.finish();
                }else {
                Toast.makeText(mActivity,baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }

    @Override
    public void PostConfirmOrder(ConfirmOrder Result) {
        if ("true".equals(Result.getSuccess())){
            bottomSheetDialog.dismiss();
            Intent intent=new Intent(mActivity, DeliverySuccessActivity.class);
            intent.putExtra("OrderID",id);
            startActivity(intent);
            OrderDetailActivity.this.finish();
        }
    }
    /**
     * 弹出付款Popupwindow
     */
    public void showPopupWindow() {
        mPresenter.GetUserInfoList(userName,"1");
        payList = new ArrayList<>();
        payList.add(new JsonStrOrderPay(Long.parseLong(id), orderBean.getBisId(), orderBean.getRealTotalAmount()));
        Gson gson = new Gson();
        try {
            jsonArray = new JSONArray(gson.toJson(payList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.payway_layout, null);
        LinearLayout ll_alipay = popupWindow_view.findViewById(R.id.ll_alipay);
        LinearLayout ll_wxpay = popupWindow_view.findViewById(R.id.ll_wxpay);
        LinearLayout ll_balance = popupWindow_view.findViewById(R.id.ll_balance);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        ll_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.GetOrderStr(userName, "", "", orderBean.getRealTotalAmount() + "", jsonArray);
//                Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
//                intent.putExtra("OrderID",OrderId);
//                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });

        ll_wxpay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                mPresenter.GetWXOrderStr(userName, "", "", orderBean.getRealTotalAmount() + "", jsonArray);
//                Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
//                intent.putExtra("OrderID",OrderId);
//                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });
        ll_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(userInfo.getPayPassWord())){
                    startActivity(new Intent(mActivity, SettingPayPasswordActivity.class));
                    mPopupWindow.dismiss();
                }else {
                  openPayPasswordDialog();

                }

            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    @Override
    public void GetExpressInfo(Express Result) {

    }

    /**
     * 支付宝支付业务
     */
    public void alipay() {

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
//        orderinfo = "app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22IQJZSRC1YMQB5HU%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdomain.merchant.com%2Fpayment_notify&sign_type=RSA2&timestamp=2016-08-25%2020%3A26%3A31&version=1.0&sign=cYmuUnKi5QdBsoZEAbMXVMmRWjsuUj%2By48A2DvWAVVBuYkiBj13CFDHu2vZQvmOfkjE0YqCUQE04kqm9Xg3tIX8tPeIGIFtsIyp%2FM45w1ZsDOiduBbduGfRo1XRsvAyVAv2hCrBLLrDI5Vi7uZZ77Lo5J0PpUUWwyQGt0M4cj8g%3D";

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderDetailActivity.this);
                Map<String, String> result = alipay.payV2(orderinfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 微信支付
     */
    public void WXpay() {
        PayReq req = new PayReq();
        req.appId = wXpayInfo.getAppid();
        req.partnerId = wXpayInfo.getPartnerid();
        req.prepayId = wXpayInfo.getPrepayid();
        req.nonceStr = wXpayInfo.getNoncestr();
        req.timeStamp = wXpayInfo.getTimestamp();
        req.packageValue = wXpayInfo.getPackageX();
        req.sign = wXpayInfo.getSign();
        //req.extData			= "app data"; // optional
        api.sendReq(req);
    }

    /**
     * 支付宝支付结果回调
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付成功");
                        mPresenter.PostChangeOrderState(id);
                        Intent intent = new Intent(mActivity, PaymentSuccessActivity.class);
                        intent.putExtra("OrderID", id);
                        startActivity(intent);
                        OrderDetailActivity.this.finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 微信支付结果
     *
     * @param resp
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                mPresenter.WXNotifyManual(wXpayInfo.getOut_trade_no());
                ToastUtils.showShort("支付成功");
                mPresenter.PostChangeOrderState(id);
                Intent intent = new Intent(mActivity, PaymentSuccessActivity.class);
                intent.putExtra("OrderID", id);
                startActivity(intent);
                OrderDetailActivity.this.finish();
                break;
            case -1:
                ToastUtils.showShort("支付出错");
                break;
            case -2:
                ToastUtils.showShort("支付取消");
                break;
        }
    }

    @Override
    public void GetOrderStr(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    orderinfo = baseResult.getData().getItem2();
                    if (!"".equals(orderinfo)) {
                        alipay();
                    }
                } else {
                    ToastUtils.showShort("获取支付信息失败！");
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                break;
        }
    }

    @Override
    public void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    wXpayInfo = baseResult.getData().getItem2();
                    if (wXpayInfo != null) {
                        WXpay();
                    }
                } else {
                    ToastUtils.showShort("获取支付信息失败！");
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                break;
        }
    }

    @Override
    public void WXNotifyManual(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void PostChangeOrderState(EasyResult baseResult) {

    }


    @Override
    public void IsMallid(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                  if (!baseResult.getData().isItem1()){
                      //没发过安装单可以安装
                      mTv_yuyue.setText("已预约");
                      mTv_yuyue.setClickable(false);
                  }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.RECEIPT_REQUEST) {
            if (resultCode == Config.RECEIPT_RESULT) {
                mTv_yuyue.setText("已预约");
                mTv_yuyue.setClickable(false);
            }

        }
    }

    /*支付密码*/
    //确认收货
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

    @Override
    public void passwordFull(String password) {
        if (paytype==1){
            if (userInfo.getPayPassWord().equals(password)){
                mPresenter.MallBalancePay("","",jsonArray,userName);

                mPopupWindow.dismiss();
            } else {
                Toast.makeText(mActivity,"支付密码错误",Toast.LENGTH_SHORT).show();
            }
        }
        else if(paytype==2){
            if (userInfo.getPayPassWord().equals(password)){
                mPresenter.PostConfirmOrder(id,userKey);
            } else {
                Toast.makeText(mActivity,"支付密码错误",Toast.LENGTH_SHORT).show();
            }
        }


    }
}
