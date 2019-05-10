package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmOrderActivity extends BaseActivity<ConfirmOrderPresenter, ConfirmOrderModel> implements View.OnClickListener, ConfirmOrderContract.View {

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

    private String Userkey;
    private SPUtils spUtils = SPUtils.getInstance("token");
    Bundle extras;

    String addressid="";
    String message=""; //存放留言信息
    private ConfirmOrderAdapter confirmOrderAdapter;
    private ShippingAddressList.ShippingAddressBean address;


    List<StoreBean> list = new ArrayList<>();

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
        } else {


        }


    }

    @Override
    protected void initView() {
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
                    mPresenter.PostSubmitOrder(skuId,count,addressid,"","0","false","0","","","121",Userkey);

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

 /*   *//*获取地址*//*
    @Override
    public void GetShippingAddressList(ShippingAddressList result) {
        if (result.isSuccess()) {
            *//*没有默认地址  默认显示第一个*//*

            if (!result.getShippingAddress().isEmpty()) {
                mTvName.setText(result.getShippingAddress().get(0).getShipTo());
                mTvPhone.setText(result.getShippingAddress().get(0).getPhone());
                mTvAddress.setText(result.getShippingAddress().get(0).getRegionFullName() + " " + result.getShippingAddress().get(0).getAddress());
                mLlAddress.setVisibility(View.VISIBLE);
                mLlAddAddress.setVisibility(View.GONE);
            } else {
                mLlAddress.setVisibility(View.GONE);
                mLlAddAddress.setVisibility(View.VISIBLE);
            }

        }

    }*/


    @Override
    public void GetShippingAddressList(ShippingAddressList result) {

    }

    /*立即购买提交*/
    @Override
    public void GetSubmitModel(GetConfirmModel result) {
        if (result.getSuccess().equals("true")) {
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

            confirmOrderAdapter = new ConfirmOrderAdapter(R.layout.item_confirm_order, list);
            confirmOrderAdapter.setEmptyView(getEmptyView());
            mRvConfirmOrder.setLayoutManager(new LinearLayoutManager(mActivity));
/*

            EditText et_message = (EditText) confirmOrderAdapter.getViewByPosition(0, R.id.et_leave_a_message);
             message = et_message.getText().toString();
*/


            mRvConfirmOrder.setAdapter(confirmOrderAdapter);
            double price = Double.parseDouble(result.getProducts().get(0).getCartItemModels().get(0).getPrice());
            double count = Double.parseDouble(result.getProducts().get(0).getCartItemModels().get(0).getCount());
            double Money = price * count;
            mTvtotalmoney.setText("合计¥:" + String.format("%.2f", Money));
        }


    }





    @Override
    public void GetSubmitByCartModel(GetConfirmModel result) {

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*处理选择的地址*/
        if (resultCode == Config.CHOOSE_ADDRESS_RESULT) {
            if (requestCode == Config.CHOOSE_ADDRESS_REQUEST) {

                 address = (ShippingAddressList.ShippingAddressBean) data.getSerializableExtra("Address");
                addressid=address.getId();
                mTvName.setText(address.getShipTo());
                mTvPhone.setText(address.getPhone());
                mTvAddress.setText(address.getRegionFullName() + " " + address.getAddress());

            }

        }

    }
}
