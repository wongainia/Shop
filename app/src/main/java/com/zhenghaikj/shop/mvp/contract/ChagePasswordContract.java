package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.ChagePassword;
import com.zhenghaikj.shop.entity.Data;

import io.reactivex.Observable;


public interface ChagePasswordContract {
    interface Model extends BaseModel{
        Observable<ChagePassword> PostChangePassword(String oldPassword,String password,String userkey);
        Observable<BaseResult<Data>> UpdatePassword(String UserID, String password);
    }

    interface View extends BaseView{
        void PostChangePassword(ChagePassword result);
        void UpdatePassword(BaseResult<Data> result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void PostChangePassword(String oldPassword,String password,String userkey);
        public abstract void UpdatePassword(String UserID,String password);
    }
}
