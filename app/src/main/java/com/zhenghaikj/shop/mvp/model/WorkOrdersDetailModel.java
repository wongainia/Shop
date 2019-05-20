package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Address;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.WorkOrdersDetailContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class WorkOrdersDetailModel implements WorkOrdersDetailContract.Model {


    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit2.getDefault().GetOrderInfo(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApplyCustomService(String OrderID) {
        return ApiRetrofit2.getDefault().ApplyCustomService(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveOrderAccessory(String OrderID, String AccessoryApplyState,String NewMoney) {
        return ApiRetrofit2.getDefault().ApproveOrderAccessory(OrderID, AccessoryApplyState,NewMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveBeyondMoney(String OrderID, String BeyondState) {
        return ApiRetrofit2.getDefault().ApproveBeyondMoney(OrderID, BeyondState)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveOrderService(String OrderID, String State) {
        return ApiRetrofit2.getDefault().ApproveOrderService(OrderID, State)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddOrUpdateExpressNo(String OrderID, String ExpressNo) {
        return ApiRetrofit2.getDefault().AddOrUpdateExpressNo(OrderID, ExpressNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> EnSureOrder(String OrderID, String PayPassword, String Grade, String OrgAppraise) {
        return ApiRetrofit2.getDefault().EnSureOrder(OrderID, PayPassword,Grade,OrgAppraise)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<BaseResult<Data<String>>> FactoryEnsureOrder(String OrderID, String PayPassword) {
        return ApiRetrofit2.getDefault().FactoryEnsureOrder(OrderID, PayPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateIsReturnByOrderID(String OrderID, String IsReturn, String AddressBack, String PostPayType) {
        return ApiRetrofit2.getDefault().UpdateIsReturnByOrderID(OrderID, IsReturn,AddressBack,PostPayType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<List<Address>>> GetAccountAddress(String UserId) {
        return ApiRetrofit2.getDefault().GetAccountAddress(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
