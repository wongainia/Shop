package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.ConfirmOrderOverResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.entity.GiftOrder;
import com.zhenghaikj.shop.entity.GiftOrderDetail;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.mvp.contract.IntegralOrderContract;

import java.util.List;

public class IntegralOrderPresenter extends IntegralOrderContract.Presenter {
    @Override
    public void GetMyOrderList(String status,String page,String userkey) {
        mModel.GetMyOrderList(status, page, userkey)
                .subscribe(new BaseObserver<GiftOrder>() {
                    @Override
                    protected void onHandleSuccess(GiftOrder value) {
                        mView.GetMyOrderList(value);
                    }
                });
    }
    @Override
    public void GetOrderCount(String userkey) {
        mModel.GetOrderCount(userkey)
                .subscribe(new BaseObserver<GiftDetailResult>() {
                    @Override
                    protected void onHandleSuccess(GiftDetailResult value) {
                        mView.GetOrderCount(value);
                    }
                });
    }
    @Override
    public void ConfirmOrderOver(String orderId,String userkey) {
        mModel.ConfirmOrderOver(orderId, userkey)
                .subscribe(new BaseObserver<ConfirmOrderOverResult>() {
                    @Override
                    protected void onHandleSuccess(ConfirmOrderOverResult value) {
                        mView.ConfirmOrderOver(value);
                    }
                });
    }
    @Override
    public void GetOrder(String orderId,String userkey) {
        mModel.GetOrder(orderId, userkey)
                .subscribe(new BaseObserver<GiftOrderDetail>() {
                    @Override
                    protected void onHandleSuccess(GiftOrderDetail value) {
                        mView.GetOrder(value);
                    }
                });
    }

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


}
