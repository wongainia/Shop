package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.entity.RefundApplyResult;

import io.reactivex.Observable;
/*售后*/
public interface AfterSaleContract {
    interface Model extends BaseModel{
        Observable<Refund> GetRefundList(String pageNo, String pageSize,String userkey);
    }

    interface View extends BaseView{
        void GetRefundList(Refund result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetRefundList(String pageNo,String pageSize, String userkey);

    }
}
