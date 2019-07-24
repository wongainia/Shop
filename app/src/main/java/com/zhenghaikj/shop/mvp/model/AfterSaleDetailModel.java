package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.ComplaintRecord;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.PostOrderComplaint;
import com.zhenghaikj.shop.entity.RefundApplyResult;
import com.zhenghaikj.shop.entity.RefundDetailResult;
import com.zhenghaikj.shop.entity.RefundProcessDetailResult;
import com.zhenghaikj.shop.mvp.contract.AfterSaleDetailContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AfterSaleDetailModel implements AfterSaleDetailContract.Model {

    private Map<String, String> map;
    private String timestamp;
    private String sign;

    @Override
    public Observable<RefundDetailResult> GetRefundDetail(String id, String userkey) {
        map = new HashMap<>();
        map.put("id",id);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetRefundDetail(id,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<OrderDetail> GetOrderDetail(String id, String userkey) {
        map = new HashMap<>();
        map.put("id",id);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetOrderDetail(id,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<RefundProcessDetailResult> GetRefundProcessDetail(String id, String userkey) {
        map = new HashMap<>();
        map.put("id",id);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetRefundProcessDetail(id,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<RefundApplyResult> PostSellerSendGoods(String Id, String ExpressCompanyName, String ShipOrderNumber, String userkey) {
        map = new HashMap<>();
        map.put("id",Id);
        map.put("expresscompanyname",ExpressCompanyName);
        map.put("shipordernumber",ShipOrderNumber);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostSellerSendGoods(Id,ExpressCompanyName,ShipOrderNumber,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PostOrderComplaint> PostOrderComplaint(String userkey, String ShopId, String OrderId, String ComplaintReason, String UserPhone) {
        map = new HashMap<>();
        map.put("userkey",userkey);
        map.put("shopid",ShopId);
        map.put("orderid",OrderId);
        map.put("complaintreason",ComplaintReason);
        map.put("userphone",UserPhone);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostOrderComplaint(userkey,ShopId,OrderId,ComplaintReason,UserPhone,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PostOrderComplaint> ApplyArbitration(String userkey, String OrderId) {
        map = new HashMap<>();
        map.put("userkey",userkey);
        map.put("orderid",OrderId);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().ApplyArbitration(userkey,OrderId,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ComplaintRecord> GetRecord(String userkey, String pageSize, String pageNo) {
        map = new HashMap<>();
        map.put("userkey",userkey);
        map.put("pagesize",pageSize);
        map.put("pageno",pageNo);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetRecord(userkey,pageSize,pageNo,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
