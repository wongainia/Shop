package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.ShopResult;
import com.zhenghaikj.shop.mvp.contract.ShopContract;

public class ShopPresenter extends ShopContract.Presenter {
    @Override
    public void index() {
        mModel.index().subscribe(new BaseObserver<ShopResult>() {
            @Override
            protected void onHandleSuccess(ShopResult value) {
                mView.index(value);
            }
        });
    }

    @Override
    public void IndexJson() {
        mModel.IndexJson().subscribe(new BaseObserver<ShopResult>() {
            @Override
            protected void onHandleSuccess(ShopResult value) {
                mView.index(value);
            }
        });
    }
}
