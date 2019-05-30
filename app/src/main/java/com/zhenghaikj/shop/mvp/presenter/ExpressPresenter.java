package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.mvp.contract.ExpressContract;

import java.util.List;

public class ExpressPresenter extends ExpressContract.Presenter {
    @Override
    public void GetExpressInfo(String ExpressNo) {
        mModel.GetExpressInfo(ExpressNo)
                .subscribe(new BaseObserver2<Data<List<Logistics>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Logistics>>> value) {
                        mView.GetExpressInfo(value);
                    }
                });
    }

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
    public void GetExpress(String orderId, String userkey) {
        mModel.GetExpress(orderId, userkey)
                .subscribe(new BaseObserver<Express>() {
                    @Override
                    protected void onHandleSuccess(Express value) {
                        mView.GetExpress(value);
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

}
