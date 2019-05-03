package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.entity.CartResult;
import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.mvp.contract.CartContract;

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

    @Override
    public void PostUpdateCartItem(String json,String Userkey) {
        mModel.PostUpdateCartItem(json,Userkey)
                .subscribe(new BaseObserver<CartResult>() {
                    @Override
                    protected void onHandleSuccess(CartResult value) {
                        mView.PostUpdateCartItem(value);
                    }
                });
    }

    @Override
    public void GetShopCouponList(String shopId) {
        mModel.GetShopCouponList(shopId)
                .subscribe(new BaseObserver<ShopCoupResult>() {
                    @Override
                    protected void onHandleSuccess(ShopCoupResult value) {
                        mView.GetShopCouponList(value);
                    }
                });
    }

    @Override
    public void PostAcceptCoupon(String vshopId, String couponId, String Userkey) {
        mModel.PostAcceptCoupon(vshopId,couponId,Userkey)
                .subscribe(new BaseObserver<GetShopCoupResult>() {
                    @Override
                    protected void onHandleSuccess(GetShopCoupResult value) {
                        mView.PostAcceptCoupon(value);
                    }
                });
    }

}
