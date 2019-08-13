package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.ConfirmOrderOverResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetExpressInfo;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.entity.GiftOrder;
import com.zhenghaikj.shop.entity.GiftOrderDetail;
import com.zhenghaikj.shop.entity.Logistics;

import java.util.List;

import io.reactivex.Observable;

public interface IntegralOrderContract {
    interface Model extends BaseModel {
        Observable<GiftOrder> GetMyOrderList(String status, String page, String userkey);
        Observable<GiftDetailResult> GetOrderCount(String userkey);
        Observable<ConfirmOrderOverResult> ConfirmOrderOver(String orderId, String userkey);
        Observable<GiftOrderDetail> GetOrder(String orderId, String userkey);
        Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(String ExpressNo);
    }

    interface View extends BaseView {
        void GetMyOrderList(GiftOrder Result);
        void GetOrderCount(GiftDetailResult Result);
        void ConfirmOrderOver(ConfirmOrderOverResult Result);
        void GetOrder(GiftOrderDetail Result);
        void GetExpressInfo(BaseResult<Data<List<Logistics>>> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetMyOrderList(String status, String page, String userkey);
        public abstract void GetOrderCount(String userkey);
        public abstract void ConfirmOrderOver(String orderId, String userkey);
        public abstract void GetOrder(String orderId, String userkey);
        public abstract void GetExpressInfo(String ExpressNo);
    }
}
