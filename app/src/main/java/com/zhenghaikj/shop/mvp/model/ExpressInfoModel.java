package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.ExpressInfoContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ExpressInfoModel implements ExpressInfoContract.Model {
    @Override
    public Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(String ExpressNo) {
        return ApiRetrofit2.getDefault().GetExpressInfo(ExpressNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit2.getDefault().GetOrderInfo(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
