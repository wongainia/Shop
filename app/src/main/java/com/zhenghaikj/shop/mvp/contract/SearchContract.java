package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.SearchResult;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface SearchContract {
    interface Model extends BaseModel {
        Observable<SearchResult> GetSearchProducts(
                String keywords,
                String cid,
                String b_id,
                String a_id,
                String orderKey,
                String orderType,
                String pageNo,
                String pageSize,
                String sid
        );
    }

    interface View extends BaseView {
        void GetSearchProducts(SearchResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetSearchProducts(
                String keywords,
                String cid,
                String b_id,
                String a_id,
                String orderKey,
                String orderType,
                String pageNo,
                String pageSize,
                String sid
        );
    }
}
