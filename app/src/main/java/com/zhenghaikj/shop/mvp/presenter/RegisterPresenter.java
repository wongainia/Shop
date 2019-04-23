package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.entity.RegisterResult;
import com.zhenghaikj.shop.mvp.contract.RegisterContract;

public class RegisterPresenter extends RegisterContract.Presenter {
    @Override
    public void Reg(String userName, String password,String oauthType,String email,String code,String oauthOpenId ,String oauthNickName) {
        mModel.Reg(userName, password, oauthType, email, code, oauthOpenId, oauthNickName)
                .subscribe(new BaseObserver<RegisterResult>() {
                    @Override
                    protected void onHandleSuccess(RegisterResult value) {
                        mView.Reg(value);
                    }
                });
    }


    @Override
    public void GetImageCheckCode() {
        mModel.GetImageCheckCode()
                .subscribe(new BaseObserver<GetImageCheckCode>() {
                    @Override
                    protected void onHandleSuccess(GetImageCheckCode value) {
                        mView.GetImageCheckCode(value);
                    }
                });
    }

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