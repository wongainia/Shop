package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.Distribution;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.mvp.contract.DistributionContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DistributionModel implements DistributionContract.Model {
    private Map<String, String> map;
    private String sign;
    private String timestamp;
    @Override
    public Observable<List<Distribution>> ProductList(String gentUserId, String userkey) {
        map=new HashMap<>();
        map.put("agentuserid",gentUserId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().ProductList(gentUserId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PersonalInformation> PersonalInformation(String UserKey) {
        map=new HashMap<>();
        map.put("userkey",UserKey);
        map.put("app_key","himalltest");
        timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PersonalInformation(UserKey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
