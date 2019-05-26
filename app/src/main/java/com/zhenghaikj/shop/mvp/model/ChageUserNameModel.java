package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.activity.ChageUserNameActivity;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.ChageUserNameContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChageUserNameModel implements ChageUserNameContract.Model {
    @Override
    public Observable<BaseResult<Data>> UpdateAccountNickName(String UserID,String NickName) {
        return ApiRetrofit2.getDefault().UpdateAccountNickName(UserID,NickName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
