package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.mvp.contract.IntegralUseContract;

public class IntegralUsePresenter extends IntegralUseContract.Presenter {
    @Override
    public void GetIntegralRecord(String page,String type,String userkey) {
        mModel.GetIntegralRecord(page, type, userkey)
                .subscribe(new BaseObserver<GiftDetailResult>() {
                    @Override
                    protected void onHandleSuccess(GiftDetailResult value) {
                        mView.GetIntegralRecord(value);
                    }
                });
    }
}
