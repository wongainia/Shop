package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.SimilarProduct;
import com.zhenghaikj.shop.mvp.contract.HistoryVisiteContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HistoryVisiteModel implements HistoryVisiteContract.Model {

    private Map<String, String> map;
    private String sign;
    private String timestamp;

    @Override
    public Observable<HistoryVisite> GetHistoryVisite(String rows,String page,String userkey) {
        map = new HashMap<>();
        map.put("rows",rows);
        map.put("page",page);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetHistoryVisite(rows,page,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<List<SimilarProduct>> GetHotProduct(String productId, String categoryId) {
        return ApiRetrofit.getDefault().GetHotProduct(productId, categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
