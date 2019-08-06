package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.HomeJsonResult;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.LimitBuyListResult;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.HomeContract;

public class HomePresenter extends HomeContract.Presenter {
    @Override
    public void GetList(String categoryId,String rows, String page, String userkey) {
        mModel.GetList(categoryId,rows, page, userkey)
                .subscribe(new BaseObserver<Announcement>() {
                    @Override
                    protected void onHandleSuccess(Announcement value) {
                        mView.GetList(value);
                    }
                });
    }
    @Override
    public void Get(String pageNo, String pageSize) {
        mModel.Get(pageNo, pageSize)
                .subscribe(new BaseObserver<HomeResult>() {
                    @Override
                    protected void onHandleSuccess(HomeResult value) {
                        mView.Get(value);
                    }
                });
    }

    @Override
    public void Get() {
        mModel.Get()
                .subscribe(new BaseObserver<HomeJsonResult>() {
                    @Override
                    protected void onHandleSuccess(HomeJsonResult value) {
                        mView.Get(value);
                    }
                });
    }

    @Override
    public void GetLismitBuyList(String pageNo, String pageSize, String cateName) {
        mModel.GetLismitBuyList(pageNo, pageSize,cateName)
                .subscribe(new BaseObserver<LimitBuyListResult>() {
                    @Override
                    protected void onHandleSuccess(LimitBuyListResult value) {
                        mView.GetLismitBuyList(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String userName, String limit) {
        mModel.GetUserInfoList(userName, limit)
                .subscribe(new BaseObserver2<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }
}
