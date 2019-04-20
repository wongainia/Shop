package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.SearchResult;
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

    @Override
    public Observable<SearchResult> GetSearchProducts(
            String keywords,
            String exp_keywords,
            String cid,
            String b_id,
            String orderKey,
            String orderType,
            String pageNo,
            String pageSize
    ) {
        map = new HashMap<>();
        map.put("keywords",keywords);
        map.put("exp_keywords",exp_keywords);
        map.put("cid",cid);
        map.put("b_id",b_id);
        map.put("orderkey",orderKey);
        map.put("ordertype",orderType);
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);
        
        map.put("app_key","himalltest");
        map.put("timestamp",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetSearchProducts(keywords,exp_keywords,cid,b_id,orderKey,orderType,pageNo,pageSize,"himalltest", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
