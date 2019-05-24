package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.ConfirmModel;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.GetConfirmModel;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.mvp.contract.ConfirmOrderContract;

import org.json.JSONArray;

public class ConfirmOrderPresenter extends ConfirmOrderContract.Presenter {
    @Override
    public void PostChangeOrderState(String orderId) {
        mModel.PostChangeOrderState(orderId)
                .subscribe(new BaseObserver<EasyResult>() {
                    @Override
                    protected void onHandleSuccess(EasyResult value) {
                        mView.PostChangeOrderState(value);
                    }
                });
    }
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

    @Override
    public void GetOrderStr(String userid, String Bisid,String OrderId,String TotalAmount, JSONArray JsonStr) {
        mModel.GetOrderStr(userid,Bisid,OrderId, TotalAmount,JsonStr)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetOrderStr(value);
                    }
                });
    }
    @Override
    public void GetWXOrderStr(String userid, String Bisid,String OrderId,String TotalAmount, JSONArray JsonStr) {
        mModel.GetWXOrderStr(userid,Bisid,OrderId, TotalAmount,JsonStr)
                .subscribe(new BaseObserver2<Data<WXpayInfo>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<WXpayInfo>> value) {
                        mView.GetWXOrderStr(value);
                    }
                });
    }
    @Override
    public void WXNotifyManual(String OutTradeNo) {
        mModel.WXNotifyManual(OutTradeNo)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.WXNotifyManual(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String userName, String limit) {
        mModel.GetUserInfoList(userName, limit)
                .subscribe(new BaseObserver2<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }
}
