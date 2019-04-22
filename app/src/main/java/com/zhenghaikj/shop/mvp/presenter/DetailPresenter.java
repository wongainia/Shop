package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.DetailContract;

public class DetailPresenter extends DetailContract.Presenter {
    @Override
    public void GetProductDetail(String id) {
        mModel.GetProductDetail(id)
                .subscribe(new BaseObserver<SearchResult>() {
                    @Override
                    protected void onHandleSuccess(SearchResult value) {
                        mView.GetProductDetail(value);
                    }
                });
    }
}
