package com.zhenghaikj.shop.activity;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lwkandroid.widget.stateframelayout.StateFrameLayout;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.ConfirmOrderAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.CommodityBean;
import com.zhenghaikj.shop.entity.ConfirmModel;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.GetConfirmModel;
import com.zhenghaikj.shop.entity.JsonStrOrderPay;
import com.zhenghaikj.shop.entity.PayResult;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.entity.StoreBean;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.mvp.contract.ConfirmOrderContract;
import com.zhenghaikj.shop.mvp.model.ConfirmOrderModel;
import com.zhenghaikj.shop.mvp.presenter.ConfirmOrderPresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.utils.SingleClick;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmOrderActivity extends BaseActivity<ConfirmOrderPresenter, ConfirmOrderModel> implements View.OnClickListener, ConfirmOrderContract.View, ConfirmOrderAdapter.SaveEditTextStrListener {

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
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.rv_confirm_order)
    RecyclerView mRvConfirmOrder;
    @BindView(R.id.tv_totalmoney)
    TextView mTvtotalmoney;
    @BindView(R.id.tv_submit)
    TextView mTvsubmit;
    @BindView(R.id.ll_address_choose)
    LinearLayout mLladdress_choose;
    @BindView(R.id.ll_add_address)
    LinearLayout mLlAddAddress;

    @BindView(R.id.stateLayout)
    StateFrameLayout mStateLayout;

    private String Userkey;
    private SPUtils spUtils = SPUtils.getInstance("token");
    Bundle extras;

    String addressid="";
    private ConfirmOrderAdapter confirmOrderAdapter;
    private ShippingAddressList.ShippingAddressBean address;
    List<StoreBean> list = new ArrayList<>();
    HashMap<Integer,String> messagemap=new HashMap<>(); //留言map
    GetConfirmModel GetConfirmModel=new GetConfirmModel();
    private PopupWindow mPopupWindow;
    private View popupWindow_view;
    private String orderinfo;
    private WXpayInfo wXpayInfo;
    private String userName;
    private String OrderId="";
    private IWXAPI api;

    List<JsonStrOrderPay> payList=new ArrayList<>();
    private JSONArray jsonArray;
    private Intent intent;
    private UserInfo.UserInfoDean userInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_confirm_order;
    }

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
        Userkey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");
        mPresenter.GetUserInfoList(userName,"1");
        api = WXAPIFactory.createWXAPI(mActivity, "wx92928bf751e1628e");
        // 将该app注册到微信
        api.registerApp("wx92928bf751e1628e");
        // mPresenter.GetShippingAddressList(Userkey);

        extras = getIntent().getExtras();
        if ("1".equals(extras.getString("TYPE"))) { //直接购买
            String skuid = extras.getString("skuid");
            String count = extras.getString("count");
            mPresenter.GetSubmitModel(skuid, count, Userkey);

        } else if ("2".equals(extras.getString("TYPE"))) {//购物车购买
            String cartItemIds = extras.getString("cartItemIds");
            mPresenter.GetSubmitByCartModel(cartItemIds, Userkey);
        }

        else {


        }


    }

    @Override
    protected void initView() {
        mStateLayout.changeState(StateFrameLayout.LOADING);
        //是否在展示内容布局的时候开启动画（200ms的Alpha动画）
        mStateLayout.enableContentAnim(false);

        //设置网络错误重试监听【不传netRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_net_error_retry"】
        mStateLayout.setOnNetErrorRetryListener(new StateFrameLayout.OnNetErrorRetryListener()
        {
            @Override
            public void onNetErrorRetry()
            {

                extras = getIntent().getExtras();
                if ("1".equals(extras.getString("TYPE"))) { //直接购买
                    String skuid = extras.getString("skuid");
                    String count = extras.getString("count");
                    mPresenter.GetSubmitModel(skuid, count, Userkey);

                } else if ("2".equals(extras.getString("TYPE"))) {//购物车购买
                    String cartItemIds = extras.getString("cartItemIds");
                    mPresenter.GetSubmitByCartModel(cartItemIds, Userkey);
                }


            }
        });
        //设置空数据重试监听【不传emptyRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_empty_retry"】
        mStateLayout.setOnEmptyRetryListener(new StateFrameLayout.OnEmptyRetryListener()
        {
            @Override
            public void onEmptyRetry()
            {
                extras = getIntent().getExtras();
                if ("1".equals(extras.getString("TYPE"))) { //直接购买
                    String skuid = extras.getString("skuid");
                    String count = extras.getString("count");
                    mPresenter.GetSubmitModel(skuid, count, Userkey);

                } else if ("2".equals(extras.getString("TYPE"))) {//购物车购买
                    String cartItemIds = extras.getString("cartItemIds");
                    mPresenter.GetSubmitByCartModel(cartItemIds, Userkey);
                }

            }
        });






        mTvTitle.setText("确认订单");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLladdress_choose.setOnClickListener(this);
        mTvsubmit.setOnClickListener(this);
    }
    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_address_choose:
                Intent intent = new Intent(mActivity, ShippingAddressActivity.class);
                intent.putExtra("CHOOSE_ADDRESS_REQUEST", true);
                startActivityForResult(intent, Config.CHOOSE_ADDRESS_REQUEST);
                break;
            case R.id.tv_submit:
                //结算
                if ("1".equals(extras.getString("TYPE"))) {//直接购买
                    String skuId = list.get(0).getList().get(0).getSkuId();
                    String count = list.get(0).getList().get(0).getCount();
                    mPresenter.PostSubmitOrder(skuId,count,addressid,getcoupons(list),"0","false","0","","",getleave_message(messagemap),Userkey);
                }else {
                    String cartItemIds = extras.getString("cartItemIds");

                     Log.d("====>优惠券",getcoupons(list)) ;
                   mPresenter.PostSubmitOrderByCart(cartItemIds,addressid,getcoupons(list),"0","false","0","","",getleave_message(messagemap),Userkey);

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
    public void GetShippingAddressList(ShippingAddressList result) {

    }

    /*立即购买提交*/
    @Override
    public void GetSubmitModel(GetConfirmModel result) {
        if (result.getSuccess().equals("true")) {
            GetConfirmModel=result;
            if (result.getAddress() != null) {
                addressid= String.valueOf(result.getAddress().getId());
                mTvName.setText(result.getAddress().getShipTo());
                mTvPhone.setText(result.getAddress().getPhone());
                mTvAddress.setText(result.getAddress().getAddress());
                mLlAddress.setVisibility(View.VISIBLE);
                mLlAddAddress.setVisibility(View.GONE);
            } else {
                mLlAddress.setVisibility(View.GONE);
                mLlAddAddress.setVisibility(View.VISIBLE);
            }

        }


        if (!result.getProducts().isEmpty()) {


            StoreBean storeBean = new StoreBean();
            storeBean.setShopLogo(""); //没店铺图标
            storeBean.setShopName(result.getProducts().get(0).getShopName());

            /*如果存在优惠券则存入*/
            if (result.getProducts().get(0).getOneCoupons()!=null){
                /*设置优惠券OneCoupons*/
                StoreBean.OneCoupons oneCoupons=new StoreBean.OneCoupons();
                oneCoupons.setBaseId(result.getProducts().get(0).getOneCoupons().getBaseId());
                oneCoupons.setBasePrice(result.getProducts().get(0).getOneCoupons().getBasePrice());
                oneCoupons.setBaseName(result.getProducts().get(0).getOneCoupons().getBaseName());
                oneCoupons.setBaseType(result.getProducts().get(0).getOneCoupons().getBaseType());
                oneCoupons.setBaseShopName(result.getProducts().get(0).getOneCoupons().getBaseShopName());
                oneCoupons.setBaseEndTime(result.getProducts().get(0).getOneCoupons().getBaseEndTime());
                oneCoupons.setBaseShopId(result.getProducts().get(0).getOneCoupons().getBaseShopId());
                oneCoupons.setOrderAmount(result.getProducts().get(0).getOneCoupons().getOrderAmount());
                storeBean.setOneCoupons(oneCoupons);
            }

            /**/
            List<CommodityBean> list_shop = new ArrayList<>();
            CommodityBean commodityBean = new CommodityBean();
            commodityBean.setSkuId(result.getProducts().get(0).getCartItemModels().get(0).getSkuId());
            commodityBean.setColor(result.getProducts().get(0).getCartItemModels().get(0).getColor());
            commodityBean.setSize(result.getProducts().get(0).getCartItemModels().get(0).getSize());
            commodityBean.setVersion(result.getProducts().get(0).getCartItemModels().get(0).getVersion());
            commodityBean.setId(result.getProducts().get(0).getCartItemModels().get(0).getId());
            commodityBean.setImgUrl(result.getProducts().get(0).getCartItemModels().get(0).getImgUrl());
            commodityBean.setName(result.getProducts().get(0).getCartItemModels().get(0).getName());
            commodityBean.setPrice(result.getProducts().get(0).getCartItemModels().get(0).getPrice());
            commodityBean.setCount(result.getProducts().get(0).getCartItemModels().get(0).getCount());
            commodityBean.setShopId(result.getProducts().get(0).getCartItemModels().get(0).getShopId());
            commodityBean.setVShopId(result.getProducts().get(0).getCartItemModels().get(0).getVshopId());
            list_shop.add(commodityBean);
            storeBean.setList(list_shop);
            list.add(storeBean);
            confirmOrderAdapter = new ConfirmOrderAdapter(R.layout.item_confirm_order, list,mActivity);
            confirmOrderAdapter.setEmptyView(getEmptyView());
            mRvConfirmOrder.setLayoutManager(new LinearLayoutManager(mActivity));
            mRvConfirmOrder.setAdapter(confirmOrderAdapter);
          //  mTvtotalmoney.setText("合计¥:" + String.format("%.2f", result.getTotalAmount()));
            //优惠后的价格
            mTvtotalmoney.setText("合计¥:" + String.format("%.2f", result.getOrderAmount()));
            mStateLayout.changeState(StateFrameLayout.SUCCESS);
        }else{
            mStateLayout.changeState(StateFrameLayout.EMPTY);
        }

    }





    @Override
    public void GetSubmitByCartModel(GetConfirmModel result) {

        if ("true".equals(result.getSuccess())) {

            GetConfirmModel=result;
            if (result.getAddress() != null) {
                addressid= String.valueOf(result.getAddress().getId());
                mTvName.setText(result.getAddress().getShipTo());
                mTvPhone.setText(result.getAddress().getPhone());
                mTvAddress.setText(result.getAddress().getAddress());
                mLlAddress.setVisibility(View.VISIBLE);
                mLlAddAddress.setVisibility(View.GONE);
            } else {
                mLlAddress.setVisibility(View.GONE);
                mLlAddAddress.setVisibility(View.VISIBLE);
            }

        }



        if (!result.getProducts().isEmpty()) {

            for (int i = 0; i <result.getProducts().size() ; i++) {
                StoreBean storeBean = new StoreBean();
                storeBean.setShopLogo(""); //没店铺图标
                storeBean.setShopName(result.getProducts().get(i).getShopName());
                /*如果存在优惠券则存入*/
                if (result.getProducts().get(i).getOneCoupons()!=null){
                    /*设置优惠券OneCoupons*/
                    StoreBean.OneCoupons oneCoupons=new StoreBean.OneCoupons();
                    oneCoupons.setBaseId(result.getProducts().get(i).getOneCoupons().getBaseId());
                    oneCoupons.setBasePrice(result.getProducts().get(i).getOneCoupons().getBasePrice());
                    oneCoupons.setBaseName(result.getProducts().get(i).getOneCoupons().getBaseName());
                    oneCoupons.setBaseType(result.getProducts().get(i).getOneCoupons().getBaseType());
                    oneCoupons.setBaseShopName(result.getProducts().get(i).getOneCoupons().getBaseShopName());
                    oneCoupons.setBaseEndTime(result.getProducts().get(i).getOneCoupons().getBaseEndTime());
                    oneCoupons.setBaseShopId(result.getProducts().get(i).getOneCoupons().getBaseShopId());
                    oneCoupons.setOrderAmount(result.getProducts().get(i).getOneCoupons().getOrderAmount());
                    storeBean.setOneCoupons(oneCoupons);
                }


                List<CommodityBean> list_shop = new ArrayList<>();
                for (int j = 0; j < result.getProducts().get(i).getCartItemModels().size() ; j++) {
                    CommodityBean commodityBean = new CommodityBean();
                    commodityBean.setSkuId(result.getProducts().get(i).getCartItemModels().get(j).getSkuId());
                    commodityBean.setColor(result.getProducts().get(i).getCartItemModels().get(j).getColor());
                    commodityBean.setSize(result.getProducts().get(i).getCartItemModels().get(j).getSize());
                    commodityBean.setVersion(result.getProducts().get(i).getCartItemModels().get(j).getVersion());
                    commodityBean.setId(result.getProducts().get(i).getCartItemModels().get(j).getId());
                    commodityBean.setImgUrl(result.getProducts().get(i).getCartItemModels().get(j).getImgUrl());
                    commodityBean.setName(result.getProducts().get(i).getCartItemModels().get(j).getName());
                    commodityBean.setPrice(result.getProducts().get(i).getCartItemModels().get(j).getPrice());
                    commodityBean.setCount(result.getProducts().get(i).getCartItemModels().get(j).getCount());
                    commodityBean.setShopId(result.getProducts().get(i).getCartItemModels().get(j).getShopId());
                    commodityBean.setVShopId(result.getProducts().get(i).getCartItemModels().get(j).getVshopId());
                    list_shop.add(commodityBean);
                }
                storeBean.setList(list_shop);
                list.add(storeBean);

            }

            confirmOrderAdapter = new ConfirmOrderAdapter(R.layout.item_confirm_order, list,mActivity);
            confirmOrderAdapter.setEmptyView(getEmptyView());
            mRvConfirmOrder.setLayoutManager(new LinearLayoutManager(mActivity));
            mRvConfirmOrder.setAdapter(confirmOrderAdapter);
         //   mTvtotalmoney.setText("合计¥:" + String.format("%.2f", result.getTotalAmount()));
           //优惠后的价格
            mTvtotalmoney.setText("合计¥:" + String.format("%.2f", result.getOrderAmount()));

            mStateLayout.changeState(StateFrameLayout.SUCCESS);

        }else {
            mStateLayout.changeState(StateFrameLayout.EMPTY);
        }


    }

    /*立即购买提交*/
    @Override
    public void PostSubmitOrder(ConfirmModel result) {
        if (result.getSuccess().equals("true")){
//            ConfirmOrderActivity.this.finish();
//            Toast.makeText(mActivity,"提交成功待支付",Toast.LENGTH_SHORT).show();
            for (int i = 0; i < result.getOrderIds().size(); i++) {
                OrderId+=result.getOrderIds().get(i)+",";
            }
            OrderId=OrderId.substring(0,OrderId.lastIndexOf(","));
            showPopupWindow(result);
        }else{
            Toast.makeText(mActivity,result.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void PostSubmitOrderByCart(ConfirmModel result) {

        if (result.getSuccess().equals("true")){
//            ConfirmOrderActivity.this.finish();
            EventBus.getDefault().post("cart");
//            Toast.makeText(mActivity,"提交成功待支付",Toast.LENGTH_SHORT).show();
            for (int i = 0; i < result.getOrderIds().size(); i++) {
                OrderId+=result.getOrderIds().get(i)+",";
            }
            OrderId=OrderId.substring(0,OrderId.lastIndexOf(","));
            showPopupWindow(result);
        }else {
            Toast.makeText(mActivity,result.getMsg(),Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*处理选择的地址*/
        if (resultCode == Config.CHOOSE_ADDRESS_RESULT) {
            if (requestCode == Config.CHOOSE_ADDRESS_REQUEST) {

                 address = (ShippingAddressList.ShippingAddressBean) data.getSerializableExtra("Address");
                if (address != null) {
                    addressid=address.getId();
                    mTvName.setText(address.getShipTo());
                    mTvPhone.setText(address.getPhone());
                    mTvAddress.setText(address.getRegionFullName() + " " + address.getAddress());
                    mLlAddress.setVisibility(View.VISIBLE);
                    mLlAddAddress.setVisibility(View.GONE);
                } else {
                    mLlAddress.setVisibility(View.GONE);
                    mLlAddAddress.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    @Override
    public void SaveEdit(int position, String string) {
        Log.d("位置为"+position,"留言内容为"+string);
        messagemap.put(position,string);

        if ("".equals(string)){
            messagemap.remove(position);

        }

    }


    /*留言添加方法*/
    public String getleave_message(HashMap<Integer,String> hashMap){
        String message="";
        if (hashMap.isEmpty()){
            message="";
        }else {
            for (int i = 0; i < list.size(); i++) {  //留言添加
                if (messagemap.get(i)==null){
                    message+=",";
                }else {
                    message+=","+messagemap.get(i);
                }
            }

            if (message.length()>1){
                message=message.substring(1,message.length());
            }else {
                message="";
            }
        }

        return message;
    }

    /*获得优惠券添加方法*/
    public String getcoupons(List<StoreBean> list){
         String onecoupons="";
        for (int i = 0; i < list.size(); i++) {  //购物券添加
            if (list.get(i).getOneCoupons()==null){
                onecoupons+=",";
            }else {
                onecoupons+=",_"+list.get(i).getOneCoupons().getBaseId();
            }
        }

        if (onecoupons.length()>1){
            onecoupons=onecoupons.substring(1,onecoupons.length());
        }else {
            onecoupons="";
        }
         return onecoupons;
    }


    /**
     * 弹出付款Popupwindow
     * @param confirmModel
     */
    public void showPopupWindow(ConfirmModel confirmModel) {
        Gson gson=new Gson();
        try {
            jsonArray = new JSONArray(gson.toJson(confirmModel.getPayInfo()));
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
                mPresenter.GetOrderStr(userName,"", "",GetConfirmModel.getTotalAmount()+"",jsonArray);
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
                mPresenter.GetWXOrderStr(userName,"", "",GetConfirmModel.getTotalAmount()+"",jsonArray);
//                Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
//                intent.putExtra("OrderID",OrderId);
//                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });

        ll_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInfo.getTotalMoney()-GetConfirmModel.getTotalAmount()>=0){
//                    mPresenter.GetOrderStr(userName,"", "",GetConfirmModel.getTotalAmount()+"",jsonArray);
                    mPopupWindow.dismiss();
                }else {
                    ToastUtils.showShort("余额不足，请充值或选择别的支付方式");
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
                PayTask alipay = new PayTask(ConfirmOrderActivity.this);
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
                        mPresenter.PostChangeOrderState(OrderId);
                        intent = new Intent(mActivity, PaymentSuccessActivity.class);
                        intent.putExtra("OrderID", OrderId);
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付失败");
                        intent =new Intent(mActivity,OrderDetailActivity.class);
                        intent.putExtra("orderId",OrderId);
                        startActivity(intent);
                        finish();
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
                Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
                intent.putExtra("OrderID", OrderId);
                startActivity(intent);
                finish();
                break;
            case -1:
                ToastUtils.showShort("支付出错");
                intent =new Intent(mActivity,OrderDetailActivity.class);
                intent.putExtra("orderId",OrderId);
                startActivity(intent);
                finish();
                break;
            case -2:
                ToastUtils.showShort("支付取消");
                intent =new Intent(mActivity,OrderDetailActivity.class);
                intent.putExtra("orderId",OrderId);
                startActivity(intent);
                finish();
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
                    intent =new Intent(mActivity,OrderDetailActivity.class);
                    intent.putExtra("orderId",OrderId);
                    startActivity(intent);
                    finish();
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                intent =new Intent(mActivity,OrderDetailActivity.class);
                intent.putExtra("orderId",OrderId);
                startActivity(intent);
                finish();
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
                    intent =new Intent(mActivity,OrderDetailActivity.class);
                    intent.putExtra("orderId",OrderId);
                    startActivity(intent);
                    finish();
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                intent =new Intent(mActivity,OrderDetailActivity.class);
                intent.putExtra("orderId",OrderId);
                startActivity(intent);
                finish();
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
}
