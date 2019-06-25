package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.entity.RefundApplyResult;
import com.zhenghaikj.shop.entity.RefundDetailResult;
import com.zhenghaikj.shop.entity.RefundProcessDetailResult;
import com.zhenghaikj.shop.mvp.contract.AfterSaleContract;
import com.zhenghaikj.shop.mvp.contract.AfterSaleDetailContract;

public class AfterSaleDetailPresenter extends AfterSaleDetailContract.Presenter {

    @Override
    public void GetRefundDetail(String id, String userkey) {
        mModel.GetRefundDetail(id,userkey)
                .subscribe(new BaseObserver<RefundDetailResult>() {
                    @Override
                    protected void onHandleSuccess(RefundDetailResult value) {
                        mView.GetRefundDetail(value);
                    }
                });
    }

    @Override
    public void GetOrderDetail(String id, String userkey) {
        mModel.GetOrderDetail(id,userkey)
                .subscribe(new BaseObserver<OrderDetail>() {
                    @Override
                    protected void onHandleSuccess(OrderDetail value) {
                        mView.GetOrderDetail(value);
                    }
                });
    }

    @Override
    public void GetRefundProcessDetail(String id, String userkey) {
        mModel.GetRefundProcessDetail(id,userkey)
                .subscribe(new BaseObserver<RefundProcessDetailResult>() {
                    @Override
                    protected void onHandleSuccess(RefundProcessDetailResult value) {
                        mView.GetRefundProcessDetail(value);
                    }
                });
    }

    @Override
    public void PostSellerSendGoods(String Id, String ExpressCompanyName, String ShipOrderNumber, String userkey) {
        mModel.PostSellerSendGoods(Id,ExpressCompanyName,ShipOrderNumber,userkey)
                .subscribe(new BaseObserver<RefundApplyResult>() {
                    @Override
                    protected void onHandleSuccess(RefundApplyResult value) {
                        mView.PostSellerSendGoods(value);
                    }
                });
    }
}
