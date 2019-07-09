package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.ConfirmModel;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.GetConfirmModel;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;

import org.json.JSONArray;

import io.reactivex.Observable;
import retrofit2.http.Field;

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
        Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String Bisid, String OrderId, String TotalAmount, JSONArray JsonStr,String ActualMoney);
        Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String Bisid, String OrderId, String TotalAmount,JSONArray JsonStr,String ActualMoney);
        Observable<BaseResult<Data<String>>> MallBalancePay( String OrderId,
                                                             String CustomerId,
                                                             JSONArray JsonStr,
                                                             String UserID,
                                                             String ActualMoney);

        Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo);
        Observable<EasyResult> PostChangeOrderState(String orderId);

        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
    }

    interface View extends BaseView{
        void GetShippingAddressList(ShippingAddressList result);
        void GetSubmitModel(GetConfirmModel result);
        void GetSubmitByCartModel(GetConfirmModel result);
        void PostSubmitOrder(ConfirmModel result);
        void PostSubmitOrderByCart(ConfirmModel result);

        void GetOrderStr(BaseResult<Data<String>> baseResult);
        void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult);
        void MallBalancePay(BaseResult<Data<String>> baseResult);
        void WXNotifyManual(BaseResult<Data<String>> baseResult);
        void PostChangeOrderState(EasyResult baseResult);
        void GetUserInfoList(BaseResult<UserInfo> Result);
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

        public abstract void GetOrderStr(String userid,String Bisid,String OrderId,String TotalAmount, JSONArray JsonStr,String ActualMoney);
        public abstract void GetWXOrderStr(String userid,String Bisid,String OrderId,String TotalAmount, JSONArray JsonStr,String ActualMoney);
        public abstract void MallBalancePay(String OrderId,
                                            String CustomerId,
                                            JSONArray JsonStr,
                                            String UserID,
                                            String ActualMoney
        );
        public abstract void WXNotifyManual(String OutTradeNo);
        public abstract void PostChangeOrderState(String orderId);

        public abstract void GetUserInfoList(String userName, String limit);

    }
}
