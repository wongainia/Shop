package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.CollectionShop;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.mvp.contract.CollectionShopContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CollectionShopModel implements CollectionShopContract.Model {

    private Map<String, String> map;
    private String timestamp;
    private String sign;



    @Override
    public Observable<CollectionShop> GetUserCollectionShop(String pageNo, String pageSize, String userkey) {
        map = new HashMap<>();
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetUserCollectionShop(pageNo,pageSize,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<PostattentionResult> PostAddFavoriteShop(String shopId, String userkey) {
        map = new HashMap<>();
        map.put("shopid",shopId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostAddFavoriteShop(shopId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
