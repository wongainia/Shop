package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.mvp.contract.WithdrawContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WithdrawModel implements WithdrawContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> WithDraw(String DrawMoney, String CardNo, String UserID,String CardName) {
        return ApiRetrofit2.getDefault().WithDraw(DrawMoney, CardNo, UserID,CardName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit) {
        return ApiRetrofit2.getDefault().GetUserInfoList(userName, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(String UserId) {
        return ApiRetrofit2.getDefault().GetAccountPayInfoList(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
