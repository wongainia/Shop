package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.ConfirmOrderOverResult;
import com.zhenghaikj.shop.entity.GiftConfirmOrder;
import com.zhenghaikj.shop.mvp.contract.GiftConfirmOrderContract;

public class GiftConfirmOrderPresenter extends GiftConfirmOrderContract.Presenter {
    @Override
    public void ConfirmOrder(String id,String count,String userkey) {
        mModel.ConfirmOrder(id, count, userkey)
                .subscribe(new BaseObserver<GiftConfirmOrder>() {
                    @Override
                    protected void onHandleSuccess(GiftConfirmOrder value) {
                        mView.ConfirmOrder(value);
                    }
                });
    }
    @Override
    public void SubmitOrder(String id,String count,String regionId,String userkey) {
        mModel.SubmitOrder(id, count, regionId, userkey)
                .subscribe(new BaseObserver<ConfirmOrderOverResult>() {
                    @Override
                    protected void onHandleSuccess(ConfirmOrderOverResult value) {
                        mView.SubmitOrder(value);
                    }
                });
    }

}
