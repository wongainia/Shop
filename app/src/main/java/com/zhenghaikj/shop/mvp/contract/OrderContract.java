package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Order;

import io.reactivex.Observable;

public interface OrderContract {
    interface Model extends BaseModel{
        Observable<Order> GetOrders(String orderStatus,String pageNo,String pageSize,String userkey );
        Observable<CloseOrder> PostCloseOrder(String orderId, String userkey);
        Observable<ConfirmOrder> PostConfirmOrder(String orderId, String userkey);
        //查询物流
        Observable<String> GetExpressInfo(String orderId,String userkey);



    }

    interface View extends BaseView{
        void GetOrders(Order result);
        void PostCloseOrder(CloseOrder Result);
        void PostConfirmOrder(ConfirmOrder Result);
        void GetExpressInfo(String Result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetOrders(String orderStatus,String pageNo,String pageSize,String userkey );
        public abstract void PostCloseOrder(String orderId, String userkey);
        public abstract void PostConfirmOrder(String orderId, String userkey);
        public abstract void GetExpressInfo(String orderId,String userkey);
    }
}
