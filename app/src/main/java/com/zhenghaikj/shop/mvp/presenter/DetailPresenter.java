package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.DetailResult;
import com.zhenghaikj.shop.entity.GetGoodSKu;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.DetailContract;

public class DetailPresenter extends DetailContract.Presenter {
    @Override
    public void GetProductDetail(String id,String Userkey) {
        mModel.GetProductDetail(id,Userkey)
                .subscribe(new BaseObserver<DetailResult>() {
                    @Override
                    protected void onHandleSuccess(DetailResult value) {
                        mView.GetProductDetail(value);
                    }
                });
    }

    @Override
    public void PostAddProductToCart(String skuId, String count, String Userkey) {
        mModel.PostAddProductToCart(skuId,count,Userkey)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(String value) {
                        mView.PostAddProductToCart(value);
                    }
                });
    }

    @Override
    public void GetSKUInfo(String productId) {
        mModel.GetSKUInfo(productId)
                .subscribe(new BaseObserver<GetGoodSKu>() {
                    @Override
                    protected void onHandleSuccess(GetGoodSKu value) {
                        mView.GetSKUInfo(value);
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
