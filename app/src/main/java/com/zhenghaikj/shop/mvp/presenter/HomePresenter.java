package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.HomeContract;

public class HomePresenter extends HomeContract.Presenter {
    @Override
    public void Get(String pageNo, String pageSize) {
        mModel.Get(pageNo, pageSize)
                .subscribe(new BaseObserver<SearchResult>() {
                    @Override
                    protected void onHandleSuccess(SearchResult value) {
                        mView.Get(value);
                    }
                });
    }
}
