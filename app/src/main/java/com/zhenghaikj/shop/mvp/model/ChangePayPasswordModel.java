package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.mvp.contract.ChangePayPasswordContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChangePayPasswordModel implements ChangePayPasswordContract.Model {

    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit) {
        return ApiRetrofit2.getDefault().GetUserInfoList(UserId, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data>> ChangePayPassword(String UserID, String OldPayPassword, String PayPassword) {
        return ApiRetrofit2.getDefault().ChangePayPassword(UserID, OldPayPassword, PayPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
