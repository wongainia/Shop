package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.ConfirmOrderOverResult;
import com.zhenghaikj.shop.entity.GiftConfirmOrder;

import io.reactivex.Observable;

public interface GiftConfirmOrderContract {
    interface Model extends BaseModel {
        Observable<GiftConfirmOrder> ConfirmOrder(String id, String count, String userkey);
        Observable<ConfirmOrderOverResult> SubmitOrder(String id, String count, String regionId, String userkey);
    }

    interface View extends BaseView {
        void ConfirmOrder(GiftConfirmOrder result);
        void SubmitOrder(ConfirmOrderOverResult result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void ConfirmOrder(String id,String count,String userkey);
        public abstract void SubmitOrder(String id,String count,String regionId,String userkey);
    }
}
