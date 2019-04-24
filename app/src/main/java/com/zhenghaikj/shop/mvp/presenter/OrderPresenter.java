package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
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
}
