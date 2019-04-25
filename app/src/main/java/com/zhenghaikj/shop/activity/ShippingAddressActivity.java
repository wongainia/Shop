package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.AddressAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.mvp.contract.ShippingAddressListContract;
import com.zhenghaikj.shop.mvp.model.ShippingAddressListModel;
import com.zhenghaikj.shop.mvp.presenter.ShippingAddressListPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingAddressActivity extends BaseActivity<ShippingAddressListPresenter, ShippingAddressListModel> implements View.OnClickListener, ShippingAddressListContract.View {

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
    @BindView(R.id.rv_address)
    RecyclerView mRvAddress;
    @BindView(R.id.tv_no_address)
    TextView mTvNoAddress;

    private List<ShippingAddressList.ShippingAddressBean> addressList = new ArrayList<>();
    private List<ShippingAddressList.ShippingAddressBean> list = new ArrayList<>();
    private SPUtils spUtils;
    private String userkey;
    private AddressAdapter addressAdapter;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_shipping_address;
    }

    @Override
    protected void initData() {
//        addressAdapter = new AddressAdapter(R.layout.item_address, list);
        addressAdapter = new AddressAdapter(R.layout.item_address, addressList);
        mRvAddress.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAddress.setAdapter(addressAdapter);
        addressAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch(view.getId()){
                case R.id.tv_edit:
                    Intent intent=new Intent(mActivity, AddAddressActivity.class);
                    intent.putExtra("address",addressList.get(position));
                    startActivityForResult(intent,100);
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    protected void initView() {
        mTvTitle.setText("我的收货地址");
        mTvSave.setText("添加新地址");
        mTvSave.setVisibility(View.VISIBLE);
        mTvTitle.setVisibility(View.VISIBLE);

        spUtils = SPUtils.getInstance("token");
        userkey = spUtils.getString("UserKey");
        mPresenter.GetShippingAddressList(userkey);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_save:
                startActivityForResult(new Intent(mActivity, AddAddressActivity.class),100);
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
        if (result.isSuccess()) {
            if (result.getShippingAddress().size() == 0) {
                    mTvNoAddress.setVisibility(View.VISIBLE);
            } else {
                addressList=result.getShippingAddress();
                list.clear();
                for (int i = 0; i < addressList.size(); i++) {
                    if(addressList.get(i).isDefault()){
                        list.add(0,result.getShippingAddress().get(i));
                    }else {
                        list.add(result.getShippingAddress().get(i));
                    }
                    addressAdapter.setNewData(list);
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            mPresenter.GetShippingAddressList(userkey);
        }
    }
}
