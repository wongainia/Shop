package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.mvp.contract.OrderContract;

public class OrderPresenter extends OrderContract.Presenter {
    @Override
    public void GetOrders(String orderStatus,String pageNo,String pageSize,String userkey ) {
        mModel.GetOrders(orderStatus, pageNo, pageSize, userkey )
                .subscribe(new BaseObserver<Order>() {
                    @Override
                    protected void onHandleSuccess(Order value) {
                        mView.GetOrders(value);
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
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(String value) {
                        mView.GetExpressInfo(value);
                    }
                });
    }

    @Override
    public void PostAddComment(String userkey, String jsonstr) {
        mModel.PostAddComment(userkey, jsonstr)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(String value) {
                        mView.PostAddComment(value);
                    }
                });
    }
}
