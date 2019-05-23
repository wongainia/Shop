package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.RefundApplyResult;
import com.zhenghaikj.shop.mvp.contract.PaymentSuccessContract;
import com.zhenghaikj.shop.mvp.contract.ReturnGoodsContract;

public class ReturnGoodsPresenter extends ReturnGoodsContract.Presenter {
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
    public void PostRefundApply(String OrderId, String OrderItemId, String RefundType, String ReturnQuantity, String Amount, String Reason, String ContactPerson, String ContactCellPhone, String RefundPayType, String userkey) {
        mModel.PostRefundApply(OrderId, OrderItemId,  RefundType,  ReturnQuantity, Amount, Reason, ContactPerson,  ContactCellPhone,  RefundPayType, userkey)
                .subscribe(new BaseObserver<RefundApplyResult>() {
                    @Override
                    protected void onHandleSuccess(RefundApplyResult value) {
                        mView.PostRefundApply(value);
                    }
                });
    }


}
