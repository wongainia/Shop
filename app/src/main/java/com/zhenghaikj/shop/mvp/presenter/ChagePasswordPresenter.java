package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.ChagePassword;
import com.zhenghaikj.shop.mvp.contract.ChagePasswordContract;

public class ChagePasswordPresenter extends ChagePasswordContract.Presenter {
    @Override
    public void PostChangePassword(String oldPassword, String password, String userkey) {
        mModel.PostChangePassword(oldPassword, password, userkey)
                .subscribe(new BaseObserver<ChagePassword>() {
                    @Override
                    protected void onHandleSuccess(ChagePassword value) {
                        mView.PostChangePassword(value);
                    }
                });
    }
}
