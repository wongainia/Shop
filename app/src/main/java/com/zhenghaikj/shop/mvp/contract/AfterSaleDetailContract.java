package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.entity.RefundDetailResult;
import com.zhenghaikj.shop.entity.RefundProcessDetailResult;

import io.reactivex.Observable;

/*售后详情页*/
public interface AfterSaleDetailContract {
    interface Model extends BaseModel{
        /*获取  申请售后/退款单 详情*/
        Observable<RefundDetailResult> GetRefundDetail(String id, String userkey);
        Observable<OrderDetail> GetOrderDetail(String id, String userkey);

        /*获取申请售后/退款单 进程详情*/

        Observable<RefundProcessDetailResult> GetRefundProcessDetail(String id, String userkey);

    }

    interface View extends BaseView{
        void GetRefundDetail(RefundDetailResult result);
        void GetOrderDetail(OrderDetail result);
        void GetRefundProcessDetail(RefundProcessDetailResult result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetRefundDetail(String id,String userkey);
        public abstract void GetOrderDetail(String id,String userkey);
        public abstract void GetRefundProcessDetail(String id,String userkey);

    }
}
