package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WXpayInfo;

import io.reactivex.Observable;


public interface RechargeContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo);
    }

    interface View extends BaseView {
        void GetOrderStr(BaseResult<Data<String>> baseResult);
        void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult);
        void WXNotifyManual(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderStr(String userid,String TotalAmount);
        public abstract void GetWXOrderStr(String userid,String TotalAmount);
        public abstract void WXNotifyManual(String OutTradeNo);
    }
}
