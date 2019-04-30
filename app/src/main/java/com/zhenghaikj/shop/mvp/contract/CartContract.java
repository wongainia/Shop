package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.activity.CartResult;
import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.SearchResult;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface CartContract {
    interface Model extends BaseModel {
        Observable<Cart> GetCartProduct(String Userkey);
        Observable<CartResult> PostDeleteCartProduct(String skuIds,String Userkey);
        Observable<CartResult> PostUpdateCartItem(String json,String Userkey);


    }

    interface View extends BaseView {
        void GetCartProduct(Cart Result);
        void PostDeleteCartProduct(CartResult Result);
        void PostUpdateCartItem(CartResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetCartProduct(String Userkey);
        public abstract void PostDeleteCartProduct(String skuIds,String Userkey);
        public abstract void PostUpdateCartItem(String json,String Userkey);
    }
}
