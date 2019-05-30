package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.ShopResult;
import com.zhenghaikj.shop.mvp.contract.ShopContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShopModel implements ShopContract.Model {
    private Map<String, String> map;
    private String sign;
    private String timestamp;
    @Override
    public Observable<ShopResult> index() {
        map = new HashMap<>();
        map.put("app_key","himalltest");
        timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().Index("himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ShopResult> IndexJson() {
        map = new HashMap<>();
        map.put("app_key","himalltest");
        timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().IndexJson("himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
