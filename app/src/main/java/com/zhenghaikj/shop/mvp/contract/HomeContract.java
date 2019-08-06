package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.HomeJsonResult;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.LimitBuyListResult;
import com.zhenghaikj.shop.entity.UserInfo;

import io.reactivex.Observable;

public interface HomeContract {
    interface Model extends BaseModel {
        Observable<Announcement> GetList(String categoryId, String rows, String page, String userkey);


        Observable<HomeResult> Get(
                String pageNo, String pageSize
        );
        Observable<HomeJsonResult> Get();
        Observable<LimitBuyListResult> GetLismitBuyList(
                String pageNo, String pageSize,String cateName
        );
        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
    }

    interface View extends BaseView {
        void GetList(Announcement result);
        void Get(HomeResult Result);
        void Get(HomeJsonResult Result);
        void GetLismitBuyList(LimitBuyListResult Result);
        void GetUserInfoList(BaseResult<UserInfo> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetList(String categoryId,String rows, String page, String userkey);
        public abstract void Get(
                String pageNo, String pageSize
        );
        public abstract void Get();
        public abstract void GetLismitBuyList(
                String pageNo, String pageSize,String cateName
        );
        public abstract void GetUserInfoList(String userName, String limit);
    }
}
