package com.zhenghaikj.shop.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
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
import com.zhenghaikj.shop.fragment.OrderFragment;
import com.zhenghaikj.shop.adapter.ConfirmOrderAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
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
import com.zhenghaikj.shop.widget.fingerprint.FingerprintCore;
import com.zhenghaikj.shop.widget.fingerprint.PwdFragment;
import com.zhenghaikj.shop.widget.fingerprint.PwdView;
import com.zhenghaikj.shop.widget.paypassword.PasswordEditText;
import com.zhenghaikj.shop.widget.paypassword.PayPasswordView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmOrderActivity extends BaseActivity<ConfirmOrderPresenter, ConfirmOrderModel> implements View.OnClickListener, ConfirmOrderContract.View, ConfirmOrderAdapter.SaveEditTextStrListener, PasswordEditText.PasswordFullListener, PwdView.InputCallBack {

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
    private String OrderId="";
    private IWXAPI api;

    List<JsonStrOrderPay> payList=new ArrayList<>();
    private JSONArray jsonArray;
    private Intent intent;
    private UserInfo.UserInfoDean userInfo;
    private BottomSheetDialog bottomSheetDialog;
    private ConfirmModel cmResult;
    private String content="本次不开具发票";
    private String invoiceType="0";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
//        mPresenter.GetUserInfoList(UserID,"1");
        api = WXAPIFactory.createWXAPI(mActivity, "wx92928bf751e1628e");
        // 将该app注册到微信
        api.registerApp("wx92928bf751e1628e");
        // mPresenter.GetShippingAddressList(userKey);

        extras = getIntent().getExtras();
        if ("1".equals(extras.getString("TYPE"))) { //直接购买
            String skuid = extras.getString("skuid");
            String count = extras.getString("count");
            mPresenter.GetSubmitModel(skuid, count, userKey);

        } else if ("2".equals(extras.getString("TYPE"))) {//购物车购买
            String cartItemIds = extras.getString("cartItemIds");
            mPresenter.GetSubmitByCartModel(cartItemIds, userKey);
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
                    mPresenter.GetSubmitModel(skuid, count, userKey);

                } else if ("2".equals(extras.getString("TYPE"))) {//购物车购买
                    String cartItemIds = extras.getString("cartItemIds");
                    mPresenter.GetSubmitByCartModel(cartItemIds, userKey);
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
                    mPresenter.GetSubmitModel(skuid, count, userKey);

                } else if ("2".equals(extras.getString("TYPE"))) {//购物车购买
                    String cartItemIds = extras.getString("cartItemIds");
                    mPresenter.GetSubmitByCartModel(cartItemIds, userKey);
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
                mPresenter.GetUserInfoList(UserID,"1");


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
            storeBean.setProvideInvoice(result.getProducts().get(0).getProvideInvoice());

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
            confirmOrderAdapter = new ConfirmOrderAdapter(R.layout.item_confirm_order, list,mActivity,content);
            confirmOrderAdapter.setEmptyView(getEmptyView());
            mRvConfirmOrder.setLayoutManager(new LinearLayoutManager(mActivity));
            mRvConfirmOrder.setAdapter(confirmOrderAdapter);
          //  mTvtotalmoney.setText("合计¥:" + String.format("%.2f", result.getTotalAmount()));
            //优惠后的价格
            mTvtotalmoney.setText("合计¥:" + String.format("%.2f", result.getOrderAmount()));
            mStateLayout.changeState(StateFrameLayout.SUCCESS);
            confirmOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()){
                        case R.id.ll_billing:
                            Intent intent=new Intent(mActivity,BillingActivity.class);
//                            intent.putExtra("position",position);
                            Bundle bundle = new Bundle();
                            bundle.putInt("position", position);
                            intent.putExtras(bundle);
                            startActivityForResult(intent,200);
                            break;
                    }
                }
            });
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
                storeBean.setProvideInvoice(result.getProducts().get(i).getProvideInvoice());
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

            confirmOrderAdapter = new ConfirmOrderAdapter(R.layout.item_confirm_order, list,mActivity,content);
            confirmOrderAdapter.setEmptyView(getEmptyView());
            mRvConfirmOrder.setLayoutManager(new LinearLayoutManager(mActivity));
            mRvConfirmOrder.setAdapter(confirmOrderAdapter);
         //   mTvtotalmoney.setText("合计¥:" + String.format("%.2f", result.getTotalAmount()));
           //优惠后的价格
            mTvtotalmoney.setText("合计¥:" + String.format("%.2f", result.getOrderAmount()));

            mStateLayout.changeState(StateFrameLayout.SUCCESS);
            confirmOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.ll_billing:
                            Intent intent=new Intent(mActivity,BillingActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("position", position);
                            intent.putExtras(bundle);
//                            intent.putExtra("position",position);
                            startActivityForResult(intent,200);

                            break;
                    }
                }
            });
        }else {
            mStateLayout.changeState(StateFrameLayout.EMPTY);
        }


    }

    /*立即购买提交*/
    @Override
    public void PostSubmitOrder(ConfirmModel result) {
        cmResult =result;
        if (result.getSuccess().equals("true")){
//            ConfirmOrderActivity.this.finish();
//            Toast.makeText(mActivity,"提交成功待支付",Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post("UpdateOrderCount");
            for (int i = 0; i < result.getOrderIds().size(); i++) {
                OrderId+=result.getOrderIds().get(i)+",";
            }
            OrderId=OrderId.substring(0,OrderId.lastIndexOf(","));
            showPopupWindow(cmResult);
        }else{
            Toast.makeText(mActivity,result.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void PostSubmitOrderByCart(ConfirmModel result) {
        cmResult =result;
        if (result.getSuccess().equals("true")){
//            ConfirmOrderActivity.this.finish();
            EventBus.getDefault().post("cart");
            EventBus.getDefault().post("UpdateOrderCount");//更新个人中心订单数量
//            Toast.makeText(mActivity,"提交成功待支付",Toast.LENGTH_SHORT).show();
            for (int i = 0; i < result.getOrderIds().size(); i++) {
                OrderId+=result.getOrderIds().get(i)+",";
            }
            OrderId=OrderId.substring(0,OrderId.lastIndexOf(","));
            showPopupWindow(cmResult);
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
        if (requestCode==100){
            mPresenter.GetUserInfoList(UserID,"1");
        }

        if (requestCode==200){
            Bundle extras = getIntent().getExtras();
            int position=extras.getInt("position");
//            String content =extras.getString("position");
            content = data.getStringExtra("noInvoice");
            invoiceType = data.getStringExtra("invoiceType");
//            String position=data.getStringExtra("position");
//            ToastUtils.showShort(content);
            confirmOrderAdapter.notifyItemChanged(position);
            confirmOrderAdapter = new ConfirmOrderAdapter(R.layout.item_confirm_order, list,mActivity,content);
            confirmOrderAdapter.setEmptyView(getEmptyView());
            mRvConfirmOrder.setLayoutManager(new LinearLayoutManager(mActivity));
            mRvConfirmOrder.setAdapter(confirmOrderAdapter);
            confirmOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.ll_billing:
                            Intent intent=new Intent(mActivity,BillingActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("position", position);
                            intent.putExtras(bundle);
//                            intent.putExtra("position",position);
                            startActivityForResult(intent,200);

                            break;
                    }
                }
            });
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
            if (list.get(i).getOneCoupons()!=null){
                onecoupons+=list.get(i).getOneCoupons().getBaseId()+"_"+list.get(i).getOneCoupons().getBaseType()+",";
            }
        }
        if (onecoupons.contains(",")) {
            onecoupons=onecoupons.substring(0,onecoupons.lastIndexOf(","));
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
                mPresenter.GetOrderStr(UserID,"", "",GetConfirmModel.getTotalAmount()+"",jsonArray,String.valueOf(confirmModel.getPayInfo().get(0).getActualMoney()));
//                Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
//                intent.putExtra("OrderID",OrderId);
//                startActivity(intent);
//                mPopupWindow.dismiss();
            }
        });

        ll_wxpay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                mPresenter.GetWXOrderStr(UserID,"", "",GetConfirmModel.getTotalAmount()+"",jsonArray,String.valueOf(confirmModel.getPayInfo().get(0).getActualMoney()));
//                Intent intent=new Intent(mActivity, PaymentSuccessActivity.class);
//                intent.putExtra("OrderID",OrderId);
//                startActivity(intent);
//                mPopupWindow.dismiss();
            }
        });

        ll_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(userInfo.getPayPassWord())){
                    Toast.makeText(mActivity,"请设置支付密码",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mActivity, SettingPayPasswordActivity.class));
                    mPopupWindow.dismiss();
                }else {
                    openPayPasswordDialog();
                }

//                startFingerprintRecognition();

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
                cancelto();
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    /**
     * 取消支付跳转页面，一个订单号跳详情，多个订单号跳列表
     */
    public void cancelto(){
        if (OrderId.contains(",")){
            intent = new Intent(mActivity, OrderActivity.class);
            intent.putExtra("intent", "待付款");
        }else{
            intent =new Intent(mActivity,OrderDetailActivity.class);
            intent.putExtra("orderId",OrderId);
        }
        startActivity(intent);
        finish();
    }


    private FingerprintCore mFingerprintCore;
    private PwdFragment fragment;
    private int count = 5;
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
        fragment.setPaySuccessCallBack(ConfirmOrderActivity.this);
        fragment.show(getSupportFragmentManager(), "Pwd");

    }



    @Override
    public void onInputFinish(String result) {
        if (result.equals(userInfo.getPayPassWord())) {
            fragment.dismiss();
//            Toast.makeText(this, "验证成功", Toast.LENGTH_SHORT).show();
//            ToastUtils.showShort("验证成功");
                mPresenter.MallBalancePay("","",jsonArray,UserID,String.valueOf(cmResult.getPayInfo().get(0).getActualMoney()));
                mPopupWindow.dismiss();


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

                mPresenter.MallBalancePay("","",jsonArray,UserID,String.valueOf(cmResult.getPayInfo().get(0).getActualMoney()));
                mPopupWindow.dismiss();


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
                        EventBus.getDefault().post("UpdateOrderCount");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付失败");
                        cancelto();
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
                EventBus.getDefault().post("UpdateOrderCount");
                break;
            case -1:
                ToastUtils.showShort("支付出错");
                cancelto();
                break;
            case -2:
                ToastUtils.showShort("支付取消");
                cancelto();
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
                    if (baseResult.getData().getItem2()!=null){
                        //bottomSheetDialog.dismiss();

                        mPresenter.PostChangeOrderState(OrderId);
                        intent = new Intent(mActivity, PaymentSuccessActivity.class);
                        intent.putExtra("OrderID", OrderId);
                        startActivity(intent);
                        ToastUtils.showShort("支付成功");
                        ConfirmOrderActivity.this.finish();
                        EventBus.getDefault().post("UpdateOrderCount");
                    }
                }else{
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
                                startActivityForResult(new Intent(mActivity,RechargeActivity.class),100);
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
            default:
                ToastUtils.showShort("获取支付信息失败！");
                break;
        }
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
                    if ("".equals(userInfo.getPayPassWord())){
                        Toast.makeText(mActivity,"请设置支付密码",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(mActivity, SettingPayPasswordActivity.class));
                    }else {
                        if ("1".equals(extras.getString("TYPE"))) {//直接购买
                            String skuId = list.get(0).getList().get(0).getSkuId();
                            String count = list.get(0).getList().get(0).getCount();
                            if ("0".equals(invoiceType)){
                                mPresenter.PostSubmitOrder(skuId,count,addressid,getcoupons(list),"0","false","0","","",getleave_message(messagemap),userKey);
                            }else {
                                mPresenter.PostSubmitOrder(skuId,count,addressid,getcoupons(list),"0","false","2","",content,getleave_message(messagemap),userKey);
                            }

                        }else {
                            String cartItemIds = extras.getString("cartItemIds");

                            Log.d("====>优惠券",getcoupons(list)) ;
                            if ("0".equals(invoiceType)){
                                mPresenter.PostSubmitOrderByCart(cartItemIds,addressid,getcoupons(list),"0","false","0","","",getleave_message(messagemap),userKey);
                            }else {
                                mPresenter.PostSubmitOrderByCart(cartItemIds,addressid,getcoupons(list),"0","false","2","",content,getleave_message(messagemap),userKey);

                            }

                        }
                    }
                }
                break;

            default:
                break;

        }
    }

    @Override
    public void passwordFull(String password) {
        if (userInfo.getPayPassWord().equals(password)){
            bottomSheetDialog.dismiss();
            mPresenter.MallBalancePay("","",jsonArray,UserID,String.valueOf(cmResult.getPayInfo().get(0).getActualMoney()));

         Log.d("=======>",jsonArray.toString());
        }else {
            Toast.makeText(mActivity,"支付密码错误",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
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
}
