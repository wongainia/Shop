package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.mvp.contract.ShippingAddressListContract;

public class ShippingAddressListPresenter extends ShippingAddressListContract.Presenter {
    @Override
    public void GetShippingAddressList(String userkey) {
        mModel.GetShippingAddressList(userkey)
                .subscribe(new BaseObserver<ShippingAddressList>() {
                    @Override
                    protected void onHandleSuccess(ShippingAddressList value) {
                        mView.GetShippingAddressList(value);
                    }
                });
    }
}
