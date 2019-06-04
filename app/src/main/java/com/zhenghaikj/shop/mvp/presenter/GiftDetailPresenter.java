package com.zhenghaikj.shop.mvp.presenter;


import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.mvp.contract.GiftDetailContract;

public class GiftDetailPresenter extends GiftDetailContract.Presenter {


    @Override
    public void GetGifts(String id, String Userkey) {
        mModel.GetGifts(id, Userkey)
                .subscribe(new BaseObserver<GiftDetailResult>() {
                    @Override
                    protected void onHandleSuccess(GiftDetailResult value) {
                        mView.GetGifts(value);
                    }
                });
    }
}
