package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Logout;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.mvp.contract.SettingContract;

public class SettingPresenter extends SettingContract.Presenter {
    @Override
    public void PostLogout() {
        mModel.PostLogout()
                .subscribe(new BaseObserver<Logout>() {
                    @Override
                    protected void onHandleSuccess(Logout value) {
                        mView.PostLogout(value);
                    }
                });
    }

    @Override
    public void PersonalInformation(String UserKey) {
        mModel.PersonalInformation(UserKey)
                .subscribe(new BaseObserver<PersonalInformation>() {
                    @Override
                    protected void onHandleSuccess(PersonalInformation value) {
                        mView.PersonalInformation(value);
                    }
                });
    }
}
