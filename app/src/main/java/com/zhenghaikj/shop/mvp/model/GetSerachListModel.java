package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.entity.GetSerachListResult;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.mvp.contract.GetSerachListContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetSerachListModel implements GetSerachListContract.Model {
    private Map<String, String> map;
    private String sign;
    private String timestamp;


    @Override
    public Observable<GetSerachListResult> GetSerachList(String skey) {
        map = new HashMap<>();
        map.put("skey",skey);
        map.put("app_key","himalltest");
        timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetSerachList(skey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
