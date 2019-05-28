package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.mvp.contract.OrderDetailContract;

import org.json.JSONArray;

public class OrderDetailPresenter extends OrderDetailContract.Presenter {
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
    public void IsMallid(String MallID) {
        mModel.IsMallid(MallID)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.IsMallid(value);
                    }
                });
    }

    @Override
    public void GetOrderDetail(String id, String userkey) {
        mModel.GetOrderDetail(id, userkey)
                .subscribe(new BaseObserver<OrderDetail>() {
                    @Override
                    protected void onHandleSuccess(OrderDetail value) {
                        mView.GetOrderDetail(value);
                    }
                });
    }

    @Override
    public void PostCloseOrder(String orderId, String userkey) {
        mModel.PostCloseOrder(orderId, userkey)
                .subscribe(new BaseObserver<CloseOrder>() {
                    @Override
                    protected void onHandleSuccess(CloseOrder value) {
                        mView.PostCloseOrder(value);
                    }
                });
    }

    @Override
    public void PostConfirmOrder(String orderId, String userkey) {
        mModel.PostConfirmOrder(orderId, userkey)
                .subscribe(new BaseObserver<ConfirmOrder>() {
                    @Override
                    protected void onHandleSuccess(ConfirmOrder value) {
                        mView.PostConfirmOrder(value);
                    }
                });
    }

    @Override
    public void GetExpressInfo(String orderId, String userkey) {
        mModel.GetExpressInfo(orderId, userkey)
                .subscribe(new BaseObserver<Express>() {
                    @Override
                    protected void onHandleSuccess(Express value) {
                        mView.GetExpressInfo(value);
                    }
                });
    }
    @Override
    public void GetOrderStr(String userid, String Bisid,String OrderId,String TotalAmount, JSONArray jsonStr) {
        mModel.GetOrderStr(userid,Bisid,OrderId, TotalAmount,jsonStr)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetOrderStr(value);
                    }
                });
    }
    @Override
    public void GetWXOrderStr(String userid, String Bisid,String OrderId,String TotalAmount, JSONArray jsonStr) {
        mModel.GetWXOrderStr(userid,Bisid,OrderId, TotalAmount,jsonStr)
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

    @Override
    public void MallBalancePay(String OrderId, String CustomerId, JSONArray JsonStr, String UserID) {
        mModel.MallBalancePay(OrderId, CustomerId, JsonStr, UserID)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.MallBalancePay(value);
                    }
                });
    }
}
