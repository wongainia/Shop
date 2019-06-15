package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.FilterResult;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.SearchContract;

public class SearchPresenter extends SearchContract.Presenter {
    @Override
    public void GetSearchProducts(String keywords,
                              String cid,
                              String b_id,
                              String a_id,
                              String orderKey,
                              String orderType,
                              String pageNo,
                              String pageSize,
                              String sid
                                  ) {
        mModel.GetSearchProducts(keywords,cid,b_id,a_id,orderKey,orderType,pageNo,pageSize,sid)
                .subscribe(new BaseObserver<SearchResult>() {
                    @Override
                    protected void onHandleSuccess(SearchResult value) {
                        mView.GetSearchProducts(value);
                    }
                });
    }

    @Override
    public void GetSearchFilter(String keywords, String cid, String a_id, String b_id, String userkey) {
        mModel.GetSearchFilter(keywords,cid,a_id,b_id,userkey)
                .subscribe(new BaseObserver<FilterResult>() {
                    @Override
                    protected void onHandleSuccess(FilterResult value) {
                        mView.GetSearchFilter(value);
                    }
                });
    }
}
