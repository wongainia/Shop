package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Order;

import io.reactivex.Observable;

public interface OrderContract {
    interface Model extends BaseModel{
        Observable<Order> GetOrders(String orderStatus,String pageNo,String pageSize,String userkey );
    }

    interface View extends BaseView{
        void GetOrders(Order result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetOrders(String orderStatus,String pageNo,String pageSize,String userkey );
    }
}
