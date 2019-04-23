package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.SearchResult;

import io.reactivex.Observable;

public interface HomeContract {
    interface Model extends BaseModel {
        Observable<SearchResult> Get(
                String pageNo, String pageSize
        );
    }

    interface View extends BaseView {
        void Get(SearchResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void Get(
                String pageNo, String pageSize
        );
    }
}
