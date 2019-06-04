package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.GiftDetailResult;

import io.reactivex.Observable;

public interface GiftDetailContract {
    interface Model extends BaseModel {
        Observable<GiftDetailResult> GetGifts(String id, String Userkey);
    }

    interface View extends BaseView {
        void GetGifts(GiftDetailResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetGifts(String id, String Userkey);
    }
}
