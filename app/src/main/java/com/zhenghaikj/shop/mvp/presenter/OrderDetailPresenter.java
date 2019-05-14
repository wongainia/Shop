package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.mvp.contract.OrderDetailContract;

public class OrderDetailPresenter extends OrderDetailContract.Presenter {
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


}
