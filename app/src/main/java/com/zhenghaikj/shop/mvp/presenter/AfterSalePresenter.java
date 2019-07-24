package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.mvp.contract.AfterSaleContract;

public class AfterSalePresenter extends AfterSaleContract.Presenter {
    @Override
    public void GetRefundList(String pageNo, String pageSize, String userkey) {
        mModel.GetRefundList(pageNo,pageSize,userkey)
                .subscribe(new BaseObserver<Refund>() {
                    @Override
                    protected void onHandleSuccess(Refund value) {
                        mView.GetRefundList(value);
                    }
                });
    }
}
