package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.DetailContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailModel implements DetailContract.Model {

    private Map<String, String> map;
    private String sign;

    @Override
    public Observable<SearchResult> GetProductDetail(
            String id
    ) {
        map = new HashMap<>();
        map.put("id",id);
        
        map.put("app_key","himalltest");
        map.put("timestamp",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetProductDetail(id,"himalltest", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
