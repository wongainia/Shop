package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.ChangeOrderAddress;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;

import org.json.JSONArray;

import io.reactivex.Observable;

public interface OrderDetailContract {
    interface Model extends BaseModel {
        Observable<OrderDetail> GetOrderDetail(String id, String userkey);
        Observable<CloseOrder> PostCloseOrder(String orderId, String userkey);
        Observable<ConfirmOrder> PostConfirmOrder(String orderId, String userkey);
        //查询物流Or
        Observable<Express> GetExpressInfo(String orderId, String userkey);


        Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String Bisid, String OrderId, String TotalAmount, JSONArray JsonStr, String ActualMoney);
        Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String Bisid, String OrderId, String TotalAmount, JSONArray JsonStr, String ActualMoney);
        Observable<BaseResult<Data<String>>> MallBalancePay( String OrderId,
                                                             String CustomerId,
                                                             JSONArray JsonStr,
                                                             String UserID,
                                                             String ActualMoney);
        Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo);
        Observable<EasyResult> PostChangeOrderState(String orderId);


        /*判断商品工单号是否发起过保内安装*/
        Observable<BaseResult<Data<String>>> IsMallid(String MallID);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
        Observable<AddtoCartResult> PostAddProductToCart(String skuId, String count, String Userkey);
        Observable<ChangeOrderAddress> PostChangeOrderAddress(String OrderId, String ReceiveAddressId, String userkey);

    }

    interface View extends BaseView {
        void GetOrderDetail(OrderDetail result);
        void PostCloseOrder(CloseOrder Result);
        void PostConfirmOrder(ConfirmOrder Result);
        void GetExpressInfo(Express Result);

        void GetOrderStr(BaseResult<Data<String>> baseResult);
        void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult);
        void WXNotifyManual(BaseResult<Data<String>> baseResult);
        void PostChangeOrderState(EasyResult baseResult);

        void IsMallid(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> Result);
        void MallBalancePay(BaseResult<Data<String>> baseResult);
        void PostAddProductToCart(AddtoCartResult Result);

        void PostChangeOrderAddress(ChangeOrderAddress Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderDetail(String id,String userkey);
        public abstract void PostCloseOrder(String orderId, String userkey);
        public abstract void PostConfirmOrder(String orderId, String userkey);
        public abstract void GetExpressInfo(String orderId,String userkey);

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

        public abstract void IsMallid(String MallID);
        public abstract void GetUserInfoList(String userName, String limit);
        public abstract void PostAddProductToCart(String skuId,String count,String Userkey);

        public abstract void PostChangeOrderAddress(String OrderId,String ReceiveAddressId,String userkey);

    }
}
