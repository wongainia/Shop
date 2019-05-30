package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.mvp.contract.ExpressContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ExpressModel implements ExpressContract.Model {
    private Map<String, String> map;
    private String sign;
    private String timestamp;
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
        timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
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
}
