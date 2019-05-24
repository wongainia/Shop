package com.zhenghaikj.shop.mvp.model;


import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.CardContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CardModel implements CardContract.Model {
    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit) {
        return ApiRetrofit2.getDefault().GetUserInfoList(UserId, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddorUpdateAccountPayInfo(String UserId, String PayInfoCode, String PayInfoName, String PayNo) {
        return ApiRetrofit2.getDefault().AddorUpdateAccountPayInfo(UserId, PayInfoCode,PayInfoName,PayNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(String UserId) {
        return ApiRetrofit2.getDefault().GetAccountPayInfoList(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> GetBankNameByCardNo(String CardNo) {
        return ApiRetrofit2.getDefault().GetBankNameByCardNo(CardNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
