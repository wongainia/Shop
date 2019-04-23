package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.SearchResult;
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

    @Override
    public Observable<SearchResult> Get(
            String pageNo, String pageSize
    ) {
        map = new HashMap<>();
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);

        map.put("app_key","himalltest");
        map.put("timestamp",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().Get(pageNo,pageSize,"himalltest", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
