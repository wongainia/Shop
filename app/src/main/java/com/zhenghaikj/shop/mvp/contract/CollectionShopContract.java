package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.CollectionProduct;
import com.zhenghaikj.shop.entity.CollectionShop;

import io.reactivex.Observable;

public interface CollectionShopContract {
    interface Model extends BaseModel{
        Observable<CollectionShop> GetUserCollectionShop(String pageNo, String pageSize, String userkey);

    }

    interface View extends BaseView{
        void GetUserCollectionShop(CollectionShop result);

    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserCollectionShop(String pageNo,String pageSize,String userkey);

    }
}
