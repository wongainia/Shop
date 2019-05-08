package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.ConfirmOrderAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.entity.StoreBean;
import com.zhenghaikj.shop.mvp.contract.ConfirmOrderContract;
import com.zhenghaikj.shop.mvp.model.ConfirmOrderModel;
import com.zhenghaikj.shop.mvp.presenter.ConfirmOrderPresenter;

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

    private ConfirmOrderAdapter confirmOrderAdapter;


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
        mPresenter.GetShippingAddressList(Userkey);
        List<StoreBean> list = (List<StoreBean>) getIntent().getSerializableExtra("checkshop");
        confirmOrderAdapter = new ConfirmOrderAdapter(R.layout.item_confirm_order, list);
        confirmOrderAdapter.setEmptyView(getEmptyView());
        mRvConfirmOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvConfirmOrder.setAdapter(confirmOrderAdapter);


        double Money = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                double Count = Double.parseDouble(list.get(i).getList().get(j).getCount());
                double Price = Double.parseDouble(list.get(i).getList().get(j).getPrice());
                Money += Count * Price;
            }
        }
        /*总价*/
        mTvtotalmoney.setText("合计¥:" + String.format("%.2f", Money));

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

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /*获取地址*/
    @Override
    public void GetShippingAddressList(ShippingAddressList result) {
        if (result.isSuccess()) {
            /*没有默认地址  默认显示第一个*/

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*处理选择的地址*/
        if (resultCode == Config.CHOOSE_ADDRESS_RESULT) {
            if (requestCode == Config.CHOOSE_ADDRESS_REQUEST) {

                ShippingAddressList.ShippingAddressBean address = (ShippingAddressList.ShippingAddressBean) data.getSerializableExtra("Address");
                mTvName.setText(address.getShipTo());
                mTvPhone.setText(address.getPhone());
                mTvAddress.setText(address.getRegionFullName() + " " + address.getAddress());


            }

        }

    }
}
