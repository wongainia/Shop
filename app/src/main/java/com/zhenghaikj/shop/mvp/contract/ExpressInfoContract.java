package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;

public interface ExpressInfoContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(String ExpressNo);
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
    }

    interface View extends BaseView {
        void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult);
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetExpressInfo(String ExpressNo);
        public abstract void GetOrderInfo(String OrderID);
    }
}
