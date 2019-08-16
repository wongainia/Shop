package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CoinRecord;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GiftDetailResult;

import java.util.List;

import io.reactivex.Observable;

public interface IntegralUseContract {
    interface Model extends BaseModel {
        Observable<GiftDetailResult> GetIntegralRecord(String page, String type, String userkey);
        Observable<BaseResult<Data<List<CoinRecord>>>> CoinList(String UserID, String state);
    }

    interface View extends BaseView {
        void GetIntegralRecord(GiftDetailResult Result);
        void CoinList(BaseResult<Data<List<CoinRecord>>> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetIntegralRecord(String page,String type,String userkey);
        public abstract void CoinList(String UserID,String state);
    }
}
