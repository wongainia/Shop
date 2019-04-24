package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.SearchResult;

import io.reactivex.Observable;

public interface CartContract {
    interface Model extends BaseModel {
        Observable<Cart> GetCartProduct(String Userkey);



    }

    interface View extends BaseView {
        void GetCartProduct(Cart Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetCartProduct(String Userkey);
    }
}
