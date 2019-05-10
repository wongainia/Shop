package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.ConfirmModel;
import com.zhenghaikj.shop.entity.GetConfirmModel;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.mvp.contract.ConfirmOrderContract;
import com.zhenghaikj.shop.mvp.contract.ShippingAddressListContract;

public class ConfirmOrderPresenter extends ConfirmOrderContract.Presenter {
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



    @Override
    public void GetSubmitModel(String skuId, String count, String userkey) {
        mModel.GetSubmitModel(skuId,count,userkey)
                .subscribe(new BaseObserver<GetConfirmModel>() {
                    @Override
                    protected void onHandleSuccess(GetConfirmModel value) {
                        mView.GetSubmitModel(value);
                    }
                });
    }

    @Override
    public void GetSubmitByCartModel(String CartItemId, String userkey) {

        mModel.GetSubmitByCartModel(CartItemId,userkey)
                .subscribe(new BaseObserver<GetConfirmModel>() {
                    @Override
                    protected void onHandleSuccess(GetConfirmModel value) {
                        mView.GetSubmitByCartModel(value);
                    }
                });

    }

    @Override
    public void PostSubmitOrder(String skuIds, String counts, String recieveAddressId, String couponIds, String integral, String isCashOnDelivery, String invoiceType, String invoiceContext, String invoiceTitle, String orderRemarks, String userkey) {
        mModel.PostSubmitOrder(skuIds,counts,recieveAddressId,couponIds,integral,isCashOnDelivery,invoiceType,invoiceContext,invoiceTitle,orderRemarks,userkey)
                .subscribe(new BaseObserver<ConfirmModel>() {
                    @Override
                    protected void onHandleSuccess(ConfirmModel value) {
                        mView.PostSubmitOrder(value);
                    }
                });
    }

    @Override
    public void PostSubmitOrderByCart(String cartItemIds, String recieveAddressId, String couponIds, String integral, String isCashOnDelivery, String invoiceType, String invoiceContext, String invoiceTitle, String orderRemarks, String userkey) {
        mModel.PostSubmitOrderByCart(cartItemIds,recieveAddressId,couponIds,integral,isCashOnDelivery,invoiceType,invoiceContext,invoiceTitle,orderRemarks,userkey)
                .subscribe(new BaseObserver<ConfirmModel>() {
                    @Override
                    protected void onHandleSuccess(ConfirmModel value) {
                        mView.PostSubmitOrderByCart(value);
                    }
                });
    }
}
