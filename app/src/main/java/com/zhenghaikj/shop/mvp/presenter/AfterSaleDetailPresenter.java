package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.ComplaintRecord;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.PostOrderComplaint;
import com.zhenghaikj.shop.entity.RefundApplyResult;
import com.zhenghaikj.shop.entity.RefundDetailResult;
import com.zhenghaikj.shop.entity.RefundProcessDetailResult;
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

    @Override
    public void PostOrderComplaint(String userkey, String ShopId, String OrderId, String ComplaintReason, String UserPhone) {
        mModel.PostOrderComplaint(userkey, ShopId, OrderId, ComplaintReason, UserPhone)
                .subscribe(new BaseObserver<PostOrderComplaint>() {
                    @Override
                    protected void onHandleSuccess(PostOrderComplaint value) {
                        mView.PostOrderComplaint(value);
                    }
                });
    }

    @Override
    public void ApplyArbitration(String userkey, String OrderId) {
        mModel.ApplyArbitration(userkey,  OrderId)
                .subscribe(new BaseObserver<PostOrderComplaint>() {
                    @Override
                    protected void onHandleSuccess(PostOrderComplaint value) {
                        mView.ApplyArbitration(value);
                    }
                });
    }

    @Override
    public void GetRecord(String userkey, String pageSize, String pageNo) {
        mModel.GetRecord(userkey, pageSize, pageNo)
                .subscribe(new BaseObserver<ComplaintRecord>() {
                    @Override
                    protected void onHandleSuccess(ComplaintRecord value) {
                        mView.GetRecord(value);
                    }
                });
    }
}
