package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.SearchContract;

public class SearchPresenter extends SearchContract.Presenter {
    @Override
    public void GetSearchProducts(String keywords,
                              String exp_keywords,
                              String cid,
                              String b_id,
                              String orderKey,
                              String orderType,
                              String pageNo,
                              String pageSize) {
        mModel.GetSearchProducts(keywords,exp_keywords,cid,b_id,orderKey,orderType,pageNo,pageSize)
                .subscribe(new BaseObserver<SearchResult>() {
                    @Override
                    protected void onHandleSuccess(SearchResult value) {
                        mView.GetSearchProducts(value);
                    }
                });
    }
}
