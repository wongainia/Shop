package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.OrderDetail;

import io.reactivex.Observable;

public interface OrderDetailContract {
    interface Model extends BaseModel{
        Observable<OrderDetail> GetOrderDetail(String id,String userkey);
    }

    interface View extends BaseView{
        void GetOrderDetail(OrderDetail result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetOrderDetail(String id,String userkey);
    }
}
