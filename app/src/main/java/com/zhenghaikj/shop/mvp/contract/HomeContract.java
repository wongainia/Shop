package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.LimitBuyListResult;

import io.reactivex.Observable;

public interface HomeContract {
    interface Model extends BaseModel {
        Observable<HomeResult> Get(
                String pageNo, String pageSize
        );
        Observable<LimitBuyListResult> GetLismitBuyList(
                String pageNo, String pageSize,String cateName
        );
    }

    interface View extends BaseView {
        void Get(HomeResult Result);
        void GetLismitBuyList(LimitBuyListResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void Get(
                String pageNo, String pageSize
        );
        public abstract void GetLismitBuyList(
                String pageNo, String pageSize,String cateName
        );
    }
}
