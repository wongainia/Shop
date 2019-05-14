package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.HomeJsonResult;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.LimitBuyListResult;
import com.zhenghaikj.shop.mvp.contract.HomeContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.Model {

    private Map<String, String> map;
    private String sign;
    private String timestamp;

    @Override
    public Observable<HomeResult> Get(
            String pageNo, String pageSize
    ) {
        map = new HashMap<>();
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);

        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().Get(pageNo,pageSize,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<HomeJsonResult> Get() {
        map = new HashMap<>();

        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().Get("himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<LimitBuyListResult> GetLismitBuyList(String pageNo, String pageSize, String cateName) {
        map = new HashMap<>();
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);
        map.put("cateName",cateName);

        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetLismitBuyList(pageNo,pageSize,cateName,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
