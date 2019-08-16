package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.RefundApplyResult;

import io.reactivex.Observable;

public interface ReturnGoodsContract {
    interface Model extends BaseModel {
        Observable<OrderDetail> GetOrderDetail(String id, String userkey);
        Observable<RefundApplyResult> PostRefundApply(String OrderId,
                                                      String OrderItemId,
                                                      String RefundType,
                                                      String ReturnQuantity,
                                                      String Amount,
                                                      String Reason,
                                                      String ContactPerson,
                                                      String ContactCellPhone,
                                                      String RefundPayType,
                                                      String userkey);
    }

    interface View extends BaseView {
        void GetOrderDetail(OrderDetail result);
        void PostRefundApply(RefundApplyResult result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderDetail(String id,String userkey);

        public abstract void PostRefundApply(String OrderId,
                                             String OrderItemId,
                                             String RefundType,
                                             String ReturnQuantity,
                                             String Amount,
                                             String Reason,
                                             String ContactPerson,
                                             String ContactCellPhone,
                                             String RefundPayType,
                                             String userkey);
    }
}
