package com.zhenghaikj.shop.mvp.contract;


import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;

import io.reactivex.Observable;


public interface GiftContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> FAddCon(String UserID, String ToUserID,String Msg,String Connum);
    }

    interface View extends BaseView {
        void FAddCon(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void FAddCon(String UserID, String ToUserID,String Msg,String Connum);
    }
}
