package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.entity.RegisterResult;
import com.zhenghaikj.shop.entity.SendMessage;
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
    public void GetUser(String userName, String password/*, String oauthType, String oauthOpenId, String oauthNickName*/) {
        mModel.GetUser(userName, password/*, oauthType, oauthOpenId, oauthNickName*/)
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
    @Override
    public void AddFactoryBrand(String UserID, String FBrandName) {
        mModel.AddFactoryBrand(UserID, FBrandName)
                .subscribe(new BaseObserver2<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.AddFactoryBrand(value);
                    }
                });
    }
    @Override
    public void GetCode(String contact, String userkey) {
        mModel.GetCode(contact,userkey)
                .subscribe(new BaseObserver<SendMessage>() {
                    @Override
                    protected void onHandleSuccess(SendMessage value) {
                        mView.GetCode(value);
                    }
                });
    }

    @Override
    public void GetCheckPhoneOrEmailCheckCode(String contact, String checkCode, String userkey) {
        mModel.GetCheckPhoneOrEmailCheckCode(contact, checkCode, userkey)
                .subscribe(new BaseObserver<CheckMessage>() {
                    @Override
                    protected void onHandleSuccess(CheckMessage value) {
                        mView.GetCheckPhoneOrEmailCheckCode(value);
                    }
                });
    }

    @Override
    public void CheckUserName(String contact, String checkCode) {
        mModel.CheckUserName(contact, checkCode)
                .subscribe(new BaseObserver<CheckMessage>() {
                    @Override
                    protected void onHandleSuccess(CheckMessage value) {
                        mView.CheckUserName(value);
                    }
                });
    }


}
