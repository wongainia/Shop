package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.ConfirmModel;
import com.zhenghaikj.shop.entity.GetConfirmModel;
import com.zhenghaikj.shop.entity.ShippingAddressList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.Query;

public interface ConfirmOrderContract {
    interface Model extends BaseModel{
        Observable<ShippingAddressList> GetShippingAddressList(String userkey);
        Observable<GetConfirmModel> GetSubmitModel(String skuId, String count, String userkey);

        Observable<GetConfirmModel> GetSubmitByCartModel(String CartItemId,String userkey);


        Observable<ConfirmModel> PostSubmitOrder(String skuIds,
                                                 String counts,
                                                 String recieveAddressId,
                                                 String couponIds,
                                                 String integral,
                                                 String isCashOnDelivery,
                                                 String invoiceType,
                                                 String invoiceContext,
                                                 String invoiceTitle,
                                                 String orderRemarks,
                                                 String userkey);

        Observable<ConfirmModel> PostSubmitOrderByCart(
                                                 String cartItemIds,
                                                 String recieveAddressId,
                                                 String couponIds,
                                                 String integral,
                                                 String isCashOnDelivery,
                                                 String invoiceType,
                                                 String invoiceContext,
                                                 String invoiceTitle,
                                                 String orderRemarks,
                                                 String userkey
        );
    }

    interface View extends BaseView{
        void GetShippingAddressList(ShippingAddressList result);
        void GetSubmitModel(GetConfirmModel result);
        void GetSubmitByCartModel(GetConfirmModel result);
        void PostSubmitOrder(ConfirmModel result);
        void PostSubmitOrderByCart(ConfirmModel result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetShippingAddressList(String userkey);
        public abstract void GetSubmitModel(String skuId, String count, String userkey);

        public abstract void GetSubmitByCartModel(String CartItemId,String userkey);
        public abstract void PostSubmitOrder (String skuIds,
                                              String counts,
                                              String recieveAddressId,
                                              String couponIds,
                                              String integral,
                                              String isCashOnDelivery,
                                              String invoiceType,
                                              String invoiceContext,
                                              String invoiceTitle,
                                              String orderRemarks,
                                              String userkey);
        public abstract void PostSubmitOrderByCart(
                                              String cartItemIds,
                                              String recieveAddressId,
                                              String couponIds,
                                              String integral,
                                              String isCashOnDelivery,
                                              String invoiceType,
                                              String invoiceContext,
                                              String invoiceTitle,
                                              String orderRemarks,
                                              String userkey
        );

    }
}
