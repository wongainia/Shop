package com.zhenghaikj.shop.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.CartActivity;
import com.zhenghaikj.shop.activity.DeliverySuccessActivity;
import com.zhenghaikj.shop.activity.EvaluateActivity;
import com.zhenghaikj.shop.activity.LogisticsInformationActivity;
import com.zhenghaikj.shop.activity.MainActivity;
import com.zhenghaikj.shop.activity.OrderActivity;
import com.zhenghaikj.shop.activity.OrderInstallActivity;
import com.zhenghaikj.shop.activity.PaymentSuccessActivity;
import com.zhenghaikj.shop.activity.RechargeActivity;
import com.zhenghaikj.shop.activity.SettingPayPasswordActivity;
import com.zhenghaikj.shop.activity.ShippingAddressActivity;
import com.zhenghaikj.shop.adapter.OrderListAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.dialog.CustomDialog;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.ChangeOrderAddress;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.JsonStrOrderPay;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.PayResult;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.mvp.contract.OrderContract;
import com.zhenghaikj.shop.mvp.model.OrderModel;
import com.zhenghaikj.shop.mvp.presenter.OrderPresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.widget.fingerprint.FingerprintCore;
import com.zhenghaikj.shop.widget.fingerprint.PwdFragment;
import com.zhenghaikj.shop.widget.fingerprint.PwdView;
import com.zhenghaikj.shop.widget.paypassword.PasswordEditText;
import com.zhenghaikj.shop.widget.paypassword.PayPasswordView;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


//全部订单
public class OrderFragment extends BaseLazyFragment<OrderPresenter, OrderModel> implements OrderContract.View,PasswordEditText.PasswordFullListener,PwdView.InputCallBack {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "OrderFragment";
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private List<Order.OrdersBean> cartList = new ArrayList<>();

    private RecyclerView.LayoutManager manager;
    //    private OrderAdapter orderAdapter;
    private int pagaNo = 1;
    private String mParam1;
    private OrderListAdapter orderListAdapter;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private int receipt_position;
    private WXpayInfo wXpayInfo;
    private String orderinfo;
    private IWXAPI api;

    private String OrderId="";
    private List<JsonStrOrderPay> payList;
    private JSONArray jsonArray;
    private int payposition;
    private int sendorderposition;

    private BottomSheetDialog bottomSheetDialog;
    private int closeid;
    private UserInfo.UserInfoDean userInfo;
    private Order order;
    private Order.OrdersBean ordersBean;
    private ZLoadingDialog dialog;
    private int paytype;  //支付方式：支付密码：1  确认收货：2

    private FingerprintCore mFingerprintCore;
    private PwdFragment fragment;
    private int count = 5;
    private ShippingAddressList.ShippingAddressBean address;
    String addressid="";

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initData() {
        dialog = new ZLoadingDialog(mActivity);
        mPresenter.GetUserInfoList(UserID,"1");

        api = WXAPIFactory.createWXAPI(mActivity, "wx92928bf751e1628e");
        // 将该app注册到微信
        api.registerApp("wx92928bf751e1628e");

        cartList.clear();
        pagaNo = 1;
        getData(mParam1);
        showLoading();


        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                cartList.clear();
                pagaNo = 1;
                getData(mParam1);
            }
        });


//        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++;
                getData(mParam1);
            }
        });

//        mPresenter.PostCloseOrder("2017021489566321",userKey);
    }

    @Override
    protected void initView() {
        orderListAdapter = new OrderListAdapter(R.layout.item_order, cartList, mParam1);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.setAdapter(orderListAdapter);

        orderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_buy:
                    case R.id.tv_buy_again://再次购买
                        List<Order.itemInfoBean> itemInfo = cartList.get(position).getItemInfo();
                        for (int i = 0; i < itemInfo.size(); i++) {
                            mPresenter.PostAddProductToCart(itemInfo.get(i).getSKuId(),itemInfo.get(i).getCount(),userKey);
                        }
                        final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
                        dialog.setMessage("商品已加入购物车")
                                //.setImageResId(R.mipmap.ic_launcher)
                                .setTitle("提示")
                                .setPositive("前往购物车")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                dialog.dismiss();
                                startActivity(new Intent(mActivity, CartActivity.class));
                            }

                            @Override
                            public void onNegtiveClick() {
                                dialog.dismiss();
                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
//                        startActivity(new Intent(mActivity, OrderDetailActivity.class));
//                        mPresenter.PostCloseOrder(cartList.get(position).getId(),userKey);
                        break;
                    case R.id.tv_delete2://删除订单
                    case R.id.tv_delete://删除订单
                        closeid =position;
                        mPresenter.CancelOrder(cartList.get(position).getId(),"0",userKey);
                        break;
                    case R.id.tv_delete_order://取消订单
                        Log.d(TAG,"编号："+cartList.get(position).getId());
                        closeid =position;
                        mPresenter.PostCloseOrder(cartList.get(position).getId(),userKey);
                        break;
                    case R.id.tv_confirm_receipt://确认收货
                        OrderId = cartList.get(position).getId();
                        receipt_position=position;
                        paytype=2;
                        openPayPasswordDialog();
//                        startFingerprintRecognition();
                        break;
                    case R.id.tv_payment://付款

                        OrderId = cartList.get(position).getId();
                        ordersBean =cartList.get(position);
                        payposition=position;
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
                        Intent intent2 = new Intent(mActivity, ShippingAddressActivity.class);
                        intent2.putExtra("CHOOSE_ADDRESS_REQUEST", true);
                        intent2.putExtra("orderId",cartList.get(position).getId());
                        startActivityForResult(intent2, Config.CHOOSE_ADDRESS_REQUEST);
                        break;
                    case R.id.tv_friend_pay://朋友代付
//                        showPopupWindow();
                        break;
                    case R.id.tv_evaluation:
                        Intent intent=new Intent(mActivity, EvaluateActivity.class);
                        intent.putExtra("OrderID",cartList.get(position).getId());
                        startActivity(intent);
                        break;
                    case R.id.tv_see_logistics://查看物流
                    case R.id.tv_logistics:
                    case R.id.tv_view_logistics:
                        Intent intent1=new Intent(mActivity, LogisticsInformationActivity.class);
                        intent1.putExtra("orederId",cartList.get(position).getId());
                        startActivity(intent1);
                        mPresenter.GetExpressInfo(cartList.get(position).getId(),userKey);
                        break;

                    case R.id.tv_sendorder://发单
                        sendorderposition=position;
                        mPresenter.IsMallid(cartList.get(position).getId());

                    break;

                }
            }
        });


    }

    @Override
    protected void setListener() {

        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                cartList.clear();
                pagaNo = 1;
                getData(mParam1);
                mRefreshLayout.finishRefresh(1000);
            }
        });



        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++;
                getData(mParam1);
            }
        });

    }

    public void getData(String mParam1) {
        switch (mParam1) {
            case "全部":
                mPresenter.GetOrders("0", Integer.toString(pagaNo), "10", userKey);
                break;
            case "待付款":
                mPresenter.GetOrders("1", Integer.toString(pagaNo), "10", userKey);
                break;
            case "待发货":
                mPresenter.GetOrders("2", Integer.toString(pagaNo), "10", userKey);
                break;
            case "待收货":
                mPresenter.GetOrders("3", Integer.toString(pagaNo), "10", userKey);
                break;
            case "待评价":
                mPresenter.GetOrders("5", Integer.toString(pagaNo), "10", userKey);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        getData(name);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }


    @Override
    public void GetOrders(Order result) {
        if (result.isSuccess()) {
            if (result.getOrders().isEmpty()){
                orderListAdapter.setEmptyView(getEmptyViewOrder());
            }
            Log.d(TAG, "00000" + result.getOrders());
            if (result.getOrders() != null) {
                order = result;
                cartList.addAll(result.getOrders());
                orderListAdapter.setNewData(cartList);
            }
            if (pagaNo != 1 && result.getOrders().size()==0) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore();
            }
            cancleLoading();
        }
        else {
            cancleLoading();
        }

    }

    @Override
    public void PostCloseOrder(CloseOrder Result) {
        if (Result.isSuccess()){
            switch (mParam1) {
                case "全部":
                    cartList.get(closeid).setStatus("已关闭");
                    cartList.get(closeid).setOrderStatus(4);
                    break;
                case "待付款":
                    cartList.remove(closeid);
                    break;
            }
            orderListAdapter.notifyDataSetChanged();
            EventBus.getDefault().post("UpdateOrderCount");
        }
    }

    @Override
    public void CancelOrder(EasyResult baseResult) {
        if (baseResult.getSuccess()){
            orderListAdapter.remove(closeid);
            orderListAdapter.notifyDataSetChanged();
            EventBus.getDefault().post("UpdateOrderCount");
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
//                        mTvWatermelonBalance.setText("¥"+userInfo.getCon()+"");//西瓜币
//                    }
                }


                break;

            default:
                break;

        }
    }

    @Override
    public void PostAddProductToCart(AddtoCartResult Result) {
        if ("true".equals(Result.getSuccess()) ){

        }
    }

    @Override
    public void PostChangeOrderAddress(ChangeOrderAddress Result) {
        if (Result.isSuccess()){
            ToastUtils.showShort(Result.getMsg());
        }else {
            ToastUtils.showShort(Result.getMsg());
        }
    }

    @Override
    public void IsMallid(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    Intent intent3=new Intent(mActivity, OrderInstallActivity.class);
                    intent3.putExtra("OrderId",cartList.get(sendorderposition).getId());
                    startActivity(intent3);
                }else {
                    Toast.makeText(mActivity,"该订单已发过工单",Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }

    @Override
    public void PostConfirmOrder(ConfirmOrder Result) {
        if ("true".equals(Result.getSuccess())){
            orderListAdapter.remove(receipt_position);

            Intent intent=new Intent(mActivity, DeliverySuccessActivity.class);
            intent.putExtra("OrderID",OrderId);
            startActivity(intent);
            EventBus.getDefault().post("UpdateOrderCount");
        }
    }
    /**
     * 弹出付款Popupwindow
     */
    public void showPopupWindow() {
        mPresenter.GetUserInfoList(UserID,"1");
        payList =new ArrayList<>();
        payList.add(new JsonStrOrderPay(Long.parseLong(OrderId),ordersBean.getBisId(),ordersBean.getOrderTotalAmount(),Double.parseDouble(ordersBean.getActualMoney())));
        Gson gson=new Gson();
        try {
            jsonArray = new JSONArray(gson.toJson(payList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.payway_layout, null);
        LinearLayout ll_alipay = popupWindow_view.findViewById(R.id.ll_alipay);
        LinearLayout ll_wxpay = popupWindow_view.findViewById(R.id.ll_wxpay);
        LinearLayout ll_balance=popupWindow_view.findViewById(R.id.ll_balance);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        ll_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.GetOrderStr(UserID,"","",ordersBean.getOrderTotalAmount()+"",jsonArray,ordersBean.getActualMoney());
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
                mPresenter.GetWXOrderStr(UserID,"","",ordersBean.getOrderTotalAmount()+"",jsonArray,ordersBean.getActualMoney());
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
                    Toast.makeText(mActivity,"请设置支付密码",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mActivity, SettingPayPasswordActivity.class));
                    mPopupWindow.dismiss();
                }
                else {
                    openPayPasswordDialog();
//                    startFingerprintRecognition();
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
     *
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
                PayTask alipay = new PayTask(getActivity());
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
    public void WXpay(){
        PayReq req = new PayReq();
        req.appId			= wXpayInfo.getAppid();
        req.partnerId		= wXpayInfo.getPartnerid();
        req.prepayId		= wXpayInfo.getPrepayid();
        req.nonceStr		= wXpayInfo.getNoncestr();
        req.timeStamp		= wXpayInfo.getTimestamp();
        req.packageValue	=  wXpayInfo.getPackageX();
        req.sign			= wXpayInfo.getSign();
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
                        orderListAdapter.remove(payposition);
                        mPresenter.PostChangeOrderState(OrderId);
                        Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
                        intent.putExtra("OrderID",OrderId);
                        startActivity(intent);
                        EventBus.getDefault().post("UpdateOrderCount");
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
     * @param resp
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseResp resp) {
        switch (resp.errCode){
            case 0:
                mPresenter.WXNotifyManual(wXpayInfo.getOut_trade_no());
                ToastUtils.showShort("支付成功");
                mPresenter.PostChangeOrderState(OrderId);
                orderListAdapter.remove(payposition);
                Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
                intent.putExtra("OrderID",OrderId);
                startActivity(intent);
                EventBus.getDefault().post("UpdateOrderCount");
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
        switch(baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    orderinfo =baseResult.getData().getItem2();
                    if (!"".equals(orderinfo)){
                        alipay();
                    }
                }else{
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
        switch(baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    wXpayInfo = baseResult.getData().getItem2();
                    if (wXpayInfo !=null){
                        WXpay();
                    }
                }else{
                    ToastUtils.showShort("获取支付信息失败！");
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                break;
        }
    }

    @Override
    public void MallBalancePay(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    mPresenter.PostChangeOrderState(OrderId);
                    Toast.makeText(mActivity,"支付成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
                    intent.putExtra("OrderID",OrderId);
                    startActivity(intent);
                    orderListAdapter.remove(payposition);
                    EventBus.getDefault().post("UpdateOrderCount");
                }else {
                    if ("余额不足".equals(baseResult.getData().getItem2())){
                        final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                        dialog.setMessage("余额不足，是否去充值？")
                                //.setImageResId(R.mipmap.ic_launcher)
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                dialog.dismiss();
                                bottomSheetDialog.dismiss();
                                startActivityForResult(new Intent(mActivity, RechargeActivity.class),100);
                            }

                            @Override
                            public void onNegtiveClick() {//取消
                                dialog.dismiss();
                            }
                        }).show();
                    }else{
                        Toast.makeText(mActivity,baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void WXNotifyManual(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void PostChangeOrderState(EasyResult baseResult) {

    }

    public void intoPwdFragment() {
        if (count <= 0)
            showPwdError();
        else {
            synchronized (OrderFragment.class){
                Log.e("chengww",fragment.getType() +""+ !fragment.isResumed());
                if (fragment == null || fragment.getType() != 0 || !fragment.isResumed()){
                    createDialogFragment(0);
                }
            }
        }
    }

    private void createDialogFragment(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(PwdFragment.TYPE, type);
        fragment = new PwdFragment();
        fragment.setInputPassword("输入密码", new PwdFragment.onSetInputPassword() {
            @Override
            public void onYesClick() {
                cancelAuthenticate();
                fragment.dismiss();
                intoPwdFragment();
            }
        });
        fragment.setArguments(bundle);
        fragment.setPaySuccessCallBack(this);
        fragment.show(getFragmentManager(), "Pwd");

    }



    @Override
    public void onInputFinish(String result) {
        if (result.equals(userInfo.getPayPassWord())) {
            fragment.dismiss();
//            Toast.makeText(this, "验证成功", Toast.LENGTH_SHORT).show();
//            ToastUtils.showShort("验证成功");
            if (paytype==1){
                mPresenter.MallBalancePay("","",jsonArray,UserID,ordersBean.getActualMoney());
                mPopupWindow.dismiss();
            }
            else if(paytype==2){
                mPresenter.PostConfirmOrder(OrderId,userKey);
            }

        }else {
            showPwdError();
        }
    }

    private void showPwdError() {
        count--;
        if (count <= 0){
            showTintDialog("提示：", "密码输入达到上限", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    fragment.dismiss();
                }
            });
        }else {
            showTintDialog("密码错误","密码输入有误，你还可以输入" + count + "次",null);
        }
    }

    public void showTintDialog(String title,String msg,DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder=new AlertDialog.Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("确定", listener);
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
        builder.create().show();
    }

    /**
     * 开始指纹识别
     */
    private void startFingerprintRecognition() {
        if (mFingerprintCore == null){
            mFingerprintCore = new FingerprintCore(mActivity);
            mFingerprintCore.setFingerprintManager(mResultListener);
        }

        if (mFingerprintCore.isSupport()) {
            if (!mFingerprintCore.isHasEnrolledFingerprints()) {
//                Toast.makeText(this,"您还没有录制指纹，请录入！",Toast.LENGTH_SHORT).show();
                ToastUtils.showShort("您还没有录制指纹，请录入！");
                return;
            }
            //指纹识别已开启
            if (mFingerprintCore.isAuthenticating()) {
                createFingerFragment();
            } else {
                mFingerprintCore.startAuthenticate();
            }
        } else {
//            Toast.makeText(this,"此设备不支持指纹解锁,或未开启屏幕锁",Toast.LENGTH_SHORT).show();
//            ToastUtils.showShort("此设备不支持指纹解锁,或未开启屏幕锁");
            intoPwdFragment();
        }
    }

    private void createFingerFragment() {
        if (fragment == null || fragment.getType() != 1 || !fragment.isResumed()) {
            createDialogFragment(1);
        }
//        cancelAuthenticate();
    }

    /**
     * 指纹识别结果回调
     */
    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            if (fragment != null)
                fragment.dismiss();
//            Toast.makeText(mActivity,"指纹解锁成功",Toast.LENGTH_SHORT).show();
            if (paytype==1){
                mPresenter.MallBalancePay("","",jsonArray,UserID,ordersBean.getActualMoney());
                mPopupWindow.dismiss();
            }
            else if(paytype==2){
                mPresenter.PostConfirmOrder(OrderId,userKey);
            }

        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            if (!fragment.setTextHint()){
                Toast.makeText(mActivity,"指纹解锁失败，请重试！",Toast.LENGTH_SHORT).show();
                intoPwdFragment();
            }

        }

        @Override
        public void onAuthenticateError(int errMsgId) {
            if (errMsgId == 7){
                Toast.makeText(mActivity,"指纹解锁超过限制",Toast.LENGTH_SHORT).show();
                if (mFingerprintCore != null){
                    mFingerprintCore = null;
                }
                if (fragment != null) {
                    fragment.dismiss();
                }
                intoPwdFragment();
            }
        }

        /**
         * 开启指纹监听结果回调
         * @param isSuccess
         */
        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {
            if (isSuccess) {
                createFingerFragment();
            }else{
                Toast.makeText(mActivity,"开启指纹监听失败",Toast.LENGTH_SHORT).show();
                intoPwdFragment();
            }

        }
    };

    public void cancelAuthenticate() {
        if (mFingerprintCore != null)
            mFingerprintCore.cancelAuthenticate();
    }

    /*支付密码*///确认收货
    private void openPayPasswordDialog() {
        PayPasswordView payPasswordView = new PayPasswordView(mActivity);
        bottomSheetDialog = new BottomSheetDialog(mActivity);
        bottomSheetDialog.setContentView(payPasswordView);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();
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
    public void onDestroy() {
        if(bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
        }

        if (mFingerprintCore != null) {
            mFingerprintCore.onDestroy();
            mFingerprintCore = null;
        }

        mResultListener = null;
        super.onDestroy();
    }

    @Override
    public void passwordFull(String password) {

        if (paytype==1){
            if (userInfo.getPayPassWord().equals(password)){
                mPresenter.MallBalancePay("","",jsonArray,UserID,ordersBean.getActualMoney());
                mPopupWindow.dismiss();
                bottomSheetDialog.dismiss();
            } else {
                Toast.makeText(mActivity,"支付密码错误",Toast.LENGTH_SHORT).show();
            }
        }
        else if(paytype==2){
            if (userInfo.getPayPassWord().equals(password)){
                mPresenter.PostConfirmOrder(OrderId,userKey);
                bottomSheetDialog.dismiss();
            } else {
                Toast.makeText(mActivity,"支付密码错误",Toast.LENGTH_SHORT).show();
            }
        }



    }


    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("加载中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }
    public void cancleLoading() {
        if (dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            mPresenter.GetUserInfoList(UserID,"1");
        }
        if (resultCode == Config.CHOOSE_ADDRESS_RESULT) {
            if (requestCode == Config.CHOOSE_ADDRESS_REQUEST) {
                address = (ShippingAddressList.ShippingAddressBean) data.getSerializableExtra("Address");
                String orderId=data.getStringExtra("orderId");
                if (address != null) {
                    addressid= address.getId();
                   mPresenter.PostChangeOrderAddress(orderId,addressid,userKey);
                } else {
                    return;
                }
            }

        }
    }
}
