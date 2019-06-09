package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.GiftDetailResult;

import io.reactivex.Observable;

public interface IntegralUseContract {
    interface Model extends BaseModel {
        Observable<GiftDetailResult> GetIntegralRecord(String page,String type,String userkey);
    }

    interface View extends BaseView {
        void GetIntegralRecord(GiftDetailResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetIntegralRecord(String page,String type,String userkey);
    }
}
