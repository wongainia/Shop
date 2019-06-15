package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CollectionShop;
import com.zhenghaikj.shop.entity.PostattentionResult;

import io.reactivex.Observable;

public interface CollectionShopContract {
    interface Model extends BaseModel{
        Observable<CollectionShop> GetUserCollectionShop(String pageNo, String pageSize, String userkey);
        Observable<PostattentionResult> PostAddFavoriteShop(String shopId, String userkey);

    }

    interface View extends BaseView{
        void GetUserCollectionShop(CollectionShop result);
        void PostAddFavoriteShop(PostattentionResult result);

    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserCollectionShop(String pageNo,String pageSize,String userkey);
        public abstract void PostAddFavoriteShop(String shopId,String userkey);

    }
}
