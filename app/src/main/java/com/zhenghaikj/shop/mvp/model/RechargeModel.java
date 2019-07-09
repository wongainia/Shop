package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.mvp.contract.RechargeContract;

import org.json.JSONArray;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RechargeModel implements RechargeContract.Model {


    @Override
    public Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount) {
        return ApiRetrofit2.getDefault().GetOrderStr(userid, "","",TotalAmount,"1",new JSONArray(),"0")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String TotalAmount) {
        return ApiRetrofit2.getDefault().GetWXOrderStr(userid, "","",TotalAmount,"1","mall",new JSONArray(),"0")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo) {
        return ApiRetrofit2.getDefault().WXNotifyManual(OutTradeNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
