package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.entity.FilterResult;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.mvp.contract.SearchContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchModel implements SearchContract.Model {

    private Map<String, String> map;
    private String sign;
    private String timestamp;

    @Override
    public Observable<SearchResult> GetSearchProducts(
            String keywords,
            String cid,
            String b_id,
            String a_id,
            String orderKey,
            String orderType,
            String pageNo,
            String pageSize,
            String sid

    ) {
        map = new HashMap<>();
        map.put("keywords",keywords);
        map.put("cid",cid);
        map.put("b_id",b_id);
        map.put("a_id",a_id);
        map.put("orderkey",orderKey);
        map.put("ordertype",orderType);
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);
        map.put("sid",sid);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetSearchProducts(keywords,cid,b_id,a_id,orderKey,orderType,pageNo,pageSize,sid,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<FilterResult> GetSearchFilter(String keywords, String cid, String a_id, String b_id, String userkey) {
        map = new HashMap<>();
        map.put("keyword",keywords);
        map.put("cid",cid);
        map.put("a_id",a_id);
        map.put("b_id",b_id);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetSearchFilter(keywords,cid,a_id,b_id,userkey,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
