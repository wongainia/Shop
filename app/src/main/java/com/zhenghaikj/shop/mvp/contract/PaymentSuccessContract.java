package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.OrderDetail;

import io.reactivex.Observable;

public interface PaymentSuccessContract {
    interface Model extends BaseModel {
        Observable<OrderDetail> GetOrderDetail(String id, String userkey);
        /*判断商品工单号是否发起过保内安装*/
        Observable<BaseResult<Data<String>>> IsMallid(String MallID);

    }

    interface View extends BaseView {
        void GetOrderDetail(OrderDetail result);
        void IsMallid(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderDetail(String id,String userkey);
        public abstract void IsMallid(String MallID);

    }
}
