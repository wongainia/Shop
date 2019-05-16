package com.zhenghaikj.shop.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.AddressAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.mvp.contract.ShippingAddressListContract;
import com.zhenghaikj.shop.mvp.model.ShippingAddressListModel;
import com.zhenghaikj.shop.mvp.presenter.ShippingAddressListPresenter;
import com.zhenghaikj.shop.utils.SingleClick;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        addressAdapter = new AddressAdapter(R.layout.item_address, addressList);
        addressAdapter.setEmptyView(getEmptyViewNoAddress());
        mRvAddress.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAddress.setAdapter(addressAdapter);
        addressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_delete:
                        new AlertDialog.Builder(mActivity).setMessage("确定删除该地址吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mPresenter.PostDeleteShippingAddress(list.get(position).getId(),userkey);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                        break;
                    case R.id.tv_edit:
                        Intent intent = new Intent(mActivity, AddAddressActivity.class);
                        intent.putExtra("address", list.get(position));
                        ShippingAddressActivity.this.startActivityForResult(intent, 100);
                        break;
                    case R.id.ll_address:
                        boolean choose_address_request = ShippingAddressActivity.this.getIntent().getBooleanExtra("CHOOSE_ADDRESS_REQUEST", false);
                        if (choose_address_request) {
                            ShippingAddressList.ShippingAddressBean shippingAddressBean = new ShippingAddressList.ShippingAddressBean();
                            shippingAddressBean = ((ShippingAddressList.ShippingAddressBean) adapter.getData().get(position));
                            Intent intent1 = new Intent();
                            intent1.putExtra("Address", shippingAddressBean);
                            ShippingAddressActivity.this.setResult(Config.CHOOSE_ADDRESS_RESULT, intent1);
                            ShippingAddressActivity.this.finish();
                        } else {
                            return;
                        }


                        break;

                    default:
                        break;
                }
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
    @SingleClick
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
            addressList=result.getShippingAddress();
            list.clear();
            list.addAll(addressList);
            addressAdapter.setNewData(list);
        }
    }

    @Override
    public void PostDeleteShippingAddress(EasyResult result) {
        if (result.getSuccess()) {
            ToastUtils.showShort("删除成功！");
            mPresenter.GetShippingAddressList(userkey);
        }else{
            ToastUtils.showShort("删除失败！");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            mPresenter.GetShippingAddressList(userkey);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        switch (message){
            case "Refresh_address":
                mPresenter.GetShippingAddressList(userkey);
                break;
            default:
                break;
        }

    }
}
