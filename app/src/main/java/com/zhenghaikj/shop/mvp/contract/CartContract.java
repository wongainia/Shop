package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.entity.CartResult;
import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;

import io.reactivex.Observable;

public interface CartContract {
    interface Model extends BaseModel {
        Observable<Cart> GetCartProduct(String Userkey);
        Observable<CartResult> PostDeleteCartProduct(String skuIds,String Userkey);
        Observable<CartResult> PostUpdateCartItem(String json,String Userkey);

        Observable<ShopCoupResult> GetShopCouponList(String shopId);

        Observable<GetShopCoupResult> PostAcceptCoupon(String vshopId,String couponId,String Userkey);
    }

    interface View extends BaseView {
        void GetCartProduct(Cart Result);
        void PostDeleteCartProduct(CartResult Result);
        void PostUpdateCartItem(CartResult Result);

        void GetShopCouponList(ShopCoupResult Result);

        void PostAcceptCoupon(GetShopCoupResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetCartProduct(String Userkey);
        public abstract void PostDeleteCartProduct(String skuIds,String Userkey);
        public abstract void PostUpdateCartItem(String json,String Userkey);
        public abstract void GetShopCouponList(String shopId);
        public abstract void PostAcceptCoupon(String vshopId,String couponId,String Userkey);
    }
}
