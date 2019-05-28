package com.zhenghaikj.shop.mvp.contract;


import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;

import io.reactivex.Observable;


public interface OpinionContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> AddOpinion(String UserID, String BackType, String Content);
    }

    interface View extends BaseView {
        void AddOpinion(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void AddOpinion(String UserID,String BackType,String Content);
    }


}
