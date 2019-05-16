package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.lwkandroid.widget.stateframelayout.StateFrameLayout;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.ConfirmOrderAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.CommodityBean;
import com.zhenghaikj.shop.entity.ConfirmModel;
import com.zhenghaikj.shop.entity.GetConfirmModel;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.entity.StoreBean;
import com.zhenghaikj.shop.mvp.contract.ConfirmOrderContract;
import com.zhenghaikj.shop.mvp.model.ConfirmOrderModel;
import com.zhenghaikj.shop.mvp.presenter.ConfirmOrderPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
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
            ConfirmOrderActivity.this.finish();
            Toast.makeText(mActivity,"提交成功待支付",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void PostSubmitOrderByCart(ConfirmModel result) {

        if (result.getSuccess().equals("true")){
            ConfirmOrderActivity.this.finish();
            EventBus.getDefault().post("cart");
            Toast.makeText(mActivity,"提交成功待支付",Toast.LENGTH_SHORT).show();
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

}
