package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.UserCouponListResult;

import io.reactivex.Observable;

public interface CouponContract {
    interface Model extends BaseModel {
        Observable<UserCouponListResult> GetUserCounponList(String UserKey);

    }

    interface View extends BaseView {
        void GetUserCounponList(UserCouponListResult baseResult);

    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetUserCounponList(String UserKey);

    }

}
