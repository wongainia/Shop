package com.zhenghaikj.shop.fragment;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
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
import com.zhenghaikj.shop.activity.DeliverySuccessActivity;
import com.zhenghaikj.shop.activity.EvaluateActivity;
import com.zhenghaikj.shop.activity.PaymentSuccessActivity;
import com.zhenghaikj.shop.adapter.OrderListAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.JsonStrOrderPay;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.PayResult;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.mvp.contract.OrderContract;
import com.zhenghaikj.shop.mvp.model.OrderModel;
import com.zhenghaikj.shop.mvp.presenter.OrderPresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.widget.paypassword.PasswordEditText;
import com.zhenghaikj.shop.widget.paypassword.PayPasswordView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


//全部订单
public class OrderFragment extends BaseLazyFragment<OrderPresenter, OrderModel> implements OrderContract.View,PasswordEditText.PasswordFullListener {
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
    private SPUtils spUtils;
    private String userKey;
    private int pagaNo = 1;
    private String mParam1;
    private OrderListAdapter orderListAdapter;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private int receipt_position;
    private WXpayInfo wXpayInfo;
    private String orderinfo;
    private IWXAPI api;
    private String userName;

    private String OrderId="";
    private List<JsonStrOrderPay> payList;
    private JSONArray jsonArray;

    private BottomSheetDialog bottomSheetDialog;


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
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");

        api = WXAPIFactory.createWXAPI(mActivity, "wx92928bf751e1628e");
        // 将该app注册到微信
        api.registerApp("wx92928bf751e1628e");

        getData();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                cartList.clear();
                pagaNo = 1;
                getData();
            }
        });
//        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++;
                getData();
            }
        });

//        mPresenter.PostCloseOrder("2017021489566321",userKey);
    }

    @Override
    protected void initView() {
        orderListAdapter = new OrderListAdapter(R.layout.item_order, cartList, mParam1);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.setAdapter(orderListAdapter);
        orderListAdapter.setEmptyView(getEmptyViewCommodity());
        orderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_buy:
                    case R.id.tv_buy_again://再次购买

//                        startActivity(new Intent(mActivity, OrderDetailActivity.class));
//                        mPresenter.PostCloseOrder(cartList.get(position).getId(),userKey);
                        break;
                    case R.id.tv_delete_order://删除订单
                        Log.d(TAG,"编号："+cartList.get(position).getId());
                        mPresenter.PostCloseOrder(cartList.get(position).getId(),userKey);
                        break;
                    case R.id.tv_confirm_receipt://确认收货
                        OrderId = cartList.get(position).getId();
                        receipt_position=position;






                        openPayPasswordDialog();


                        break;
                    case R.id.tv_payment://付款

                        OrderId = cartList.get(position).getId();
                        showPopupWindow(cartList.get(position));
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
                        intent.putExtra("OrderID",cartList.get(position).getId());
                        startActivity(intent);
                        break;
                    case R.id.tv_see_logistics://查看物流
                    case R.id.tv_logistics:
                    case R.id.tv_view_logistics:
                        mPresenter.GetExpressInfo(cartList.get(position).getId(),userKey);
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
                getData();
            }
        });



        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++;
                getData();
            }
        });

    }

    public void getData() {
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
        switch (name){
            case "evaluate":
                mPresenter.GetOrders("5", Integer.toString(pagaNo), "10", userKey);
                break;

        }


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
            Log.d(TAG, "00000" + result.getOrders());
            if (result.getOrders() != null) {
                cartList.addAll(result.getOrders());
                orderListAdapter.setNewData(cartList);
            }
            mRefreshLayout.finishRefresh();
            if (pagaNo != 1 && result.getOrders().size()==0) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore();
            }
        }
    }

    @Override
    public void PostCloseOrder(CloseOrder Result) {
        if (Result.isSuccess()){
            getData();
        }
    }

    @Override
    public void PostConfirmOrder(ConfirmOrder Result) {
        if ("true".equals(Result.getSuccess())){
            orderListAdapter.remove(receipt_position);
            bottomSheetDialog.dismiss();
            Intent intent=new Intent(mActivity, DeliverySuccessActivity.class);
            intent.putExtra("OrderID",OrderId);
            startActivity(intent);

        }
    }
    /**
     * 弹出付款Popupwindow
     * @param ordersBean
     */
    public void showPopupWindow(Order.OrdersBean ordersBean) {
        payList =new ArrayList<>();
        payList.add(new JsonStrOrderPay(Long.parseLong(OrderId),ordersBean.getBisId(),ordersBean.getOrderTotalAmount()));
        Gson gson=new Gson();
        try {
            jsonArray = new JSONArray(gson.toJson(payList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.payway_layout, null);
        LinearLayout ll_alipay = popupWindow_view.findViewById(R.id.ll_alipay);
        LinearLayout ll_wxpay = popupWindow_view.findViewById(R.id.ll_wxpay);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        ll_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.GetOrderStr(userName,"","",ordersBean.getOrderTotalAmount()+"",jsonArray);
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
                mPresenter.GetWXOrderStr(userName,"","",ordersBean.getOrderTotalAmount()+"",jsonArray);
//                Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
//                intent.putExtra("OrderID",OrderId);
//                startActivity(intent);
                mPopupWindow.dismiss();
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
    public void GetExpressInfo(String Result) {

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
                        Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
                        intent.putExtra("OrderID",OrderId);
                        startActivity(intent);
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
                Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
                intent.putExtra("OrderID",OrderId);
                startActivity(intent);
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
    public void WXNotifyManual(BaseResult<Data<String>> baseResult) {

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


    @Override
    public void passwordFull(String password) {
     if ("888888".equals(password)){
         mPresenter.PostConfirmOrder(OrderId,userKey);
     }else {
         Toast.makeText(mActivity,"支付密码错误",Toast.LENGTH_SHORT).show();
     }


    }


}
