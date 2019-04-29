package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.CollectionProduct;
import com.zhenghaikj.shop.mvp.contract.CollectionProductContract;

public class CollectionProductPresenter extends CollectionProductContract.Presenter {
    @Override
    public void GetUserCollectionProduct(String pageNo, String pageSize, String userkey) {
        mModel.GetUserCollectionProduct(pageNo, pageSize, userkey)
                .subscribe(new BaseObserver<CollectionProduct>() {
                    @Override
                    protected void onHandleSuccess(CollectionProduct value) {
                        mView.GetUserCollectionProduct(value);
                    }
                });
    }

    @Override
    public void PostAddFavoriteProduct(String productId, String Userkey) {
        mModel.PostAddFavoriteProduct(productId,Userkey)
                .subscribe(new BaseObserver<CollectResult>() {
                    @Override
                    protected void onHandleSuccess(CollectResult value) {
                        mView.PostAddFavoriteProduct(value);
                    }
                });
    }
}
