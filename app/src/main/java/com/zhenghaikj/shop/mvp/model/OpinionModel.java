package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.OpinionContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class OpinionModel implements OpinionContract.Model {

    @Override
    public Observable<BaseResult<Data<String>>> AddOpinion(String UserID, String BackType, String Content) {
        return  ApiRetrofit2.getDefault().AddOpinion(UserID, BackType, Content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
