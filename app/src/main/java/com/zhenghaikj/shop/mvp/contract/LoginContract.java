package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.LoginResult;

import io.reactivex.Observable;

public interface LoginContract {
    interface Model extends BaseModel {
        Observable<LoginResult> GetUser(String userName, String password,String oauthType,String oauthOpenId,String oauthNickName);
    }

    interface View extends BaseView {
        void GetUser(LoginResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetUser(String userName, String password,String oauthType,String oauthOpenId,String oauthNickName);
    }
}
