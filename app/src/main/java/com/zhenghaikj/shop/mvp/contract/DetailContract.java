package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.SearchResult;

import io.reactivex.Observable;

public interface DetailContract {
    interface Model extends BaseModel {
        Observable<SearchResult> GetProductDetail(
                String id
        );
    }

    interface View extends BaseView {
        void GetProductDetail(SearchResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetProductDetail(
                String id
        );
    }
}
