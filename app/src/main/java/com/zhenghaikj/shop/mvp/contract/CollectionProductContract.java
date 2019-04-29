package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CollectionProduct;

import io.reactivex.Observable;

public interface CollectionProductContract {
    interface Model extends BaseModel{
        Observable<CollectionProduct> GetUserCollectionProduct(String pageNo,String pageSize,String userkey);
    }

    interface View extends BaseView{
        void GetUserCollectionProduct(CollectionProduct result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserCollectionProduct(String pageNo,String pageSize,String userkey);
    }
}
