package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.ComplaintRecord;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.PostOrderComplaint;
import com.zhenghaikj.shop.entity.RefundApplyResult;
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

        Observable<RefundApplyResult> PostSellerSendGoods(String Id,
                                                          String ExpressCompanyName,
                                                          String ShipOrderNumber,
                                                          String userkey);

        Observable<PostOrderComplaint> PostOrderComplaint(String userkey,
                                                          String ShopId,
                                                          String OrderId,
                                                          String ComplaintReason,
                                                          String UserPhone);
        Observable<PostOrderComplaint> ApplyArbitration(String userkey,
                                                          String OrderId);

        Observable<ComplaintRecord> GetRecord(String userkey,
                                              String pageSize,
                                              String pageNo);

    }

    interface View extends BaseView{
        void GetRefundDetail(RefundDetailResult result);
        void GetOrderDetail(OrderDetail result);
        void GetRefundProcessDetail(RefundProcessDetailResult result);
        void PostSellerSendGoods(RefundApplyResult result);
        void PostOrderComplaint(PostOrderComplaint result);
        void ApplyArbitration(PostOrderComplaint result);
        void GetRecord(ComplaintRecord result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetRefundDetail(String id,String userkey);
        public abstract void GetOrderDetail(String id,String userkey);
        public abstract void GetRefundProcessDetail(String id,String userkey);
        public abstract void PostSellerSendGoods(String Id,
                                                 String ExpressCompanyName,
                                                 String ShipOrderNumber,
                                                 String userkey);
        public abstract void PostOrderComplaint(String userkey,
                                                String ShopId,
                                                String OrderId,
                                                String ComplaintReason,
                                                String UserPhone);
        public abstract void ApplyArbitration(String userkey,
                                                String OrderId);

        public abstract void GetRecord(String userkey,
                                       String pageSize,
                                       String pageNo);
    }
}
