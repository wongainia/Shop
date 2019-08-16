package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.FilterResult;
import com.zhenghaikj.shop.entity.SearchResult;

import io.reactivex.Observable;

public interface SearchContract {
    interface Model extends BaseModel {
        Observable<SearchResult> GetSearchProducts(
                String keywords,
                String cid,
                String a_id,
                String b_id,
                String orderKey,
                String orderType,
                String pageNo,
                String pageSize,
                String sid
        );

        Observable<FilterResult> GetSearchFilter(String keywords,
                                                 String cid,
                                                 String a_id,
                                                 String b_id,
                                                 String userkey);


    }

    interface View extends BaseView {
        void GetSearchProducts(SearchResult Result);
        void GetSearchFilter(FilterResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetSearchProducts(
                String keywords,
                String cid,
                String a_id,
                String b_id,
                String orderKey,
                String orderType,
                String pageNo,
                String pageSize,
                String sid
        );

        public abstract void GetSearchFilter(String keywords,
                                             String cid,
                                             String a_id,
                                             String b_id,
                                           String userkey);

    }
}
