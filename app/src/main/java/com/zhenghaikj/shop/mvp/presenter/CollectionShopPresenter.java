package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.CollectionShop;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.mvp.contract.CollectionShopContract;

public class CollectionShopPresenter extends CollectionShopContract.Presenter {
    @Override
    public void GetUserCollectionShop(String pageNo, String pageSize, String userkey) {
        mModel.GetUserCollectionShop(pageNo, pageSize, userkey)
                .subscribe(new BaseObserver<CollectionShop>() {
                    @Override
                    protected void onHandleSuccess(CollectionShop value) {
                        mView.GetUserCollectionShop(value);
                    }
                });
    }
    @Override
    public void PostAddFavoriteShop(String shopId, String userkey) {
        mModel.PostAddFavoriteShop(shopId, userkey)
                .subscribe(new BaseObserver<PostattentionResult>() {
                    @Override
                    protected void onHandleSuccess(PostattentionResult value) {
                        mView.PostAddFavoriteShop(value);
                    }
                });
    }
}
