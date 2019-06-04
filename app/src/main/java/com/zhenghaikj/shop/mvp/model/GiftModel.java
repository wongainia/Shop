package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.GiftContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class GiftModel implements GiftContract.Model {


    @Override
    public Observable<BaseResult<Data<String>>> FAddCon(String UserID, String ToUserID,String Msg,String Connum) {
        return ApiRetrofit2.getDefault().FAddCon(UserID, ToUserID, Msg, Connum)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
