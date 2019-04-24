package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.mvp.contract.HomeContract;

public class HomePresenter extends HomeContract.Presenter {
    @Override
    public void Get(String pageNo, String pageSize) {
        mModel.Get(pageNo, pageSize)
                .subscribe(new BaseObserver<HomeResult>() {
                    @Override
                    protected void onHandleSuccess(HomeResult value) {
                        mView.Get(value);
                    }
                });
    }
}
