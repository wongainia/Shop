package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.mvp.contract.OrderContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderModel implements OrderContract.Model {

    private Map<String, String> map;
    private String sign;

    @Override
    public Observable<Order> GetOrders(String orderStatus,String pageNo,String pageSize,String userkey ) {
        map = new HashMap<>();
        map.put("orderstatus",orderStatus);
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);
        map.put("userkey",userkey );
        map.put("app_key","himalltest");
        map.put("timestamp", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetOrders(orderStatus,pageNo,pageSize,userkey ,"himalltest",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
