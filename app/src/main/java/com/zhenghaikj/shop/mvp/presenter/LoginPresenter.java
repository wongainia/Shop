package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.mvp.contract.LoginContract;

public class LoginPresenter extends LoginContract.Presenter {
    @Override
    public void GetUser(String userName, String password, String oauthType, String oauthOpenId, String oauthNickName) {
        mModel.GetUser(userName, password, oauthType, oauthOpenId, oauthNickName)
                .subscribe(new BaseObserver<LoginResult>() {
                    @Override
                    protected void onHandleSuccess(LoginResult value) {
                        mView.GetUser(value);
                    }
                });
    }

    @Override
    public void LoginOn(String userName, String password) {
        mModel.LoginOn(userName, password)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.LoginOn(value);
                    }
                });
    }

    @Override
    public void AddAndUpdatePushAccount(String token, String type, String UserID) {
        mModel.AddAndUpdatePushAccount(token, type, UserID)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddAndUpdatePushAccount(value);
                    }
                });
    }
}
