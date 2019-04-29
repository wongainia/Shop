package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.activity.CartResult;
import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.CartContract;
import com.zhenghaikj.shop.mvp.contract.DetailContract;

public class CartPresenter extends CartContract.Presenter {
    @Override
    public void GetCartProduct(String Userkey) {
        mModel.GetCartProduct(Userkey)
                .subscribe(new BaseObserver<Cart>() {
                    @Override
                    protected void onHandleSuccess(Cart value) {
                        mView.GetCartProduct(value);
                    }
                });
    }

    @Override
    public void PostDeleteCartProduct(String skuIds, String Userkey) {
        mModel.PostDeleteCartProduct(skuIds,Userkey)
                .subscribe(new BaseObserver<CartResult>() {
                    @Override
                    protected void onHandleSuccess(CartResult value) {
                        mView.PostDeleteCartProduct(value);
                    }
                });
    }

}
