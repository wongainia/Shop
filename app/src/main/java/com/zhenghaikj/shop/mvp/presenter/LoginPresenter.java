package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
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
}
