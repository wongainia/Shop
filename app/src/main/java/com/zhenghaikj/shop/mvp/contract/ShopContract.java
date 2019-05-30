package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.ShopResult;

import io.reactivex.Observable;


public interface ShopContract {
    interface Model extends BaseModel{
        Observable<ShopResult> index();
        Observable<ShopResult> IndexJson();
    }

    interface View extends BaseView{
        void index(ShopResult result);
        void IndexJson(ShopResult result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void index();
        public abstract void IndexJson();
    }
}
