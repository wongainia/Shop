package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.UserCouponListResult;
import com.zhenghaikj.shop.mvp.contract.CouponContract;

public class CouponPresenter extends CouponContract.Presenter {

    @Override
    public void GetUserCounponList(String UserKey) {
        mModel.GetUserCounponList(UserKey)
                .subscribe(new BaseObserver<UserCouponListResult>() {
                    @Override
                    protected void onHandleSuccess(UserCouponListResult value) {
                        mView.GetUserCounponList(value);
                    }
                });
    }
}
