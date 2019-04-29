package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.CollectionProduct;

import io.reactivex.Observable;

public interface CollectionProductContract {
    interface Model extends BaseModel{
        Observable<CollectionProduct> GetUserCollectionProduct(String pageNo,String pageSize,String userkey);
        Observable<CollectResult> PostAddFavoriteProduct(String productId, String Userkey);
    }

    interface View extends BaseView{
        void GetUserCollectionProduct(CollectionProduct result);
        void PostAddFavoriteProduct(CollectResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserCollectionProduct(String pageNo,String pageSize,String userkey);
        public abstract void PostAddFavoriteProduct(String productId,String Userkey);
    }
}
