package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.SendMessage;
import com.zhenghaikj.shop.mvp.contract.BindPhoneContract;

public class BindPhonePresenter extends BindPhoneContract.Presenter {
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
