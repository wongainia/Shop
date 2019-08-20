package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.ShipmentNumber;

import java.util.List;

import io.reactivex.Observable;

public interface ExpressContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(String ExpressNo);
        Observable<Order> GetOrders(String orderStatus, String pageNo, String pageSize, String userkey );
        //查询物流Or
        Observable<Express> GetExpress(String orderId, String userkey);
        Observable<OrderDetail> GetOrderDetail(String id, String userkey);
        Observable<ShipmentNumber> GetExpressNum(String orderId,String userkey);
    }

    interface View extends BaseView {
        void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult);
        void GetOrders(Order result);
        void GetExpress(Express Result);
        void GetOrderDetail(OrderDetail result);
        void GetExpressNum(ShipmentNumber result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetExpressInfo(String ExpressNo);
        public abstract void GetOrders(String orderStatus,String pageNo,String pageSize,String userkey );
        public abstract void GetExpress(String orderId,String userkey);
        public abstract void GetOrderDetail(String id,String userkey);
        public abstract void GetExpressNum(String orderId,String userkey);
    }
}
