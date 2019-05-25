package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.WXpayInfo;

import org.json.JSONArray;

import io.reactivex.Observable;

public interface OrderContract {
    interface Model extends BaseModel{
        Observable<Order> GetOrders(String orderStatus,String pageNo,String pageSize,String userkey );
        Observable<CloseOrder> PostCloseOrder(String orderId, String userkey);
        Observable<ConfirmOrder> PostConfirmOrder(String orderId, String userkey);
        //查询物流
        Observable<String> GetExpressInfo(String orderId,String userkey);

        Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String Bisid, String OrderId, String TotalAmount, JSONArray jsonStr);
        Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid,String Bisid,String OrderId, String TotalAmount, JSONArray jsonStr);
        Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo);
        Observable<EasyResult> PostChangeOrderState(String orderId);
        Observable<EasyResult> CancelOrder(String orderId,String userid);

    }

    interface View extends BaseView{
        void GetOrders(Order result);
        void PostCloseOrder(CloseOrder Result);
        void PostConfirmOrder(ConfirmOrder Result);
        void GetExpressInfo(String Result);

        void GetOrderStr(BaseResult<Data<String>> baseResult);
        void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult);
        void WXNotifyManual(BaseResult<Data<String>> baseResult);
        void PostChangeOrderState(EasyResult baseResult);
        void CancelOrder(EasyResult baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetOrders(String orderStatus,String pageNo,String pageSize,String userkey );
        public abstract void PostCloseOrder(String orderId, String userkey);
        public abstract void PostConfirmOrder(String orderId, String userkey);
        public abstract void GetExpressInfo(String orderId,String userkey);

        public abstract void GetOrderStr(String userid,String Bisid,String OrderId,String TotalAmount, JSONArray jsonStr);
        public abstract void GetWXOrderStr(String userid,String Bisid,String OrderId,String TotalAmount, JSONArray jsonStr);
        public abstract void WXNotifyManual(String OutTradeNo);
        public abstract void PostChangeOrderState(String orderId);
        public abstract void CancelOrder(String orderId,String userid);
    }
}
