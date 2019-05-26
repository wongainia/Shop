package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.Track;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.MineContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MineModel implements MineContract.Model {
    private Map<String, String> map;
    private String sign;
    private String timestamp;
    @Override
    public Observable<PersonalInformation> PersonalInformation(String UserKey) {
        map=new HashMap<>();
        map.put("userkey",UserKey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PersonalInformation(UserKey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<HistoryVisite> GetHistoryVisite(String userkey) {
        map = new HashMap<>();
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetHistoryVisite(userkey,"himalltest",timestamp,sign)
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
    public Observable<BaseResult<Data<List<WorkOrder.DataBean>>>> GetOrderByhmall(String UserID) {
        return ApiRetrofit2.getDefault().GetOrderByhmall(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> EnSureOrder(String OrderID, String PayPassword, String Grade, String Grade1, String Grade2, String Grade3, String OrgAppraise) {
        return ApiRetrofit2.getDefault().EnSureOrder(OrderID, PayPassword, Grade, Grade1, Grade2, Grade3, OrgAppraise)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<WorkOrder>> GetOrderInfoList(String UserID, String state, String page, String limit) {
        return ApiRetrofit2.getDefault().FactoryGetOrderList(UserID,state, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<WorkOrder.DataBean>>>> GetOrderByhmalluserid(String UserID) {
        return ApiRetrofit2.getDefault().GetOrderByhmalluserid(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<Track>>> GetOrderRecordByOrderID(String OrderId) {
        return ApiRetrofit2.getDefault().GetOrderRecordByOrderID(OrderId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> PressWokerAccount(String OrderID, String Content) {
        return ApiRetrofit2.getDefault().PressWokerAccount(OrderID, Content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(String ExpressNo) {
        return ApiRetrofit2.getDefault().GetExpressInfo(ExpressNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Order> GetOrders(String orderStatus, String pageNo, String pageSize, String userkey ) {
        map = new HashMap<>();
        map.put("orderstatus",orderStatus);
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);
        map.put("userkey",userkey );
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetOrders(orderStatus,pageNo,pageSize,userkey ,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Express> GetExpress(String orderId, String userkey) {
        map = new HashMap<>();
        map.put("orderid",orderId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetExpressInfo(orderId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
