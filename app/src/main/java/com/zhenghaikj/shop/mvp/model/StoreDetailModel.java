package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Brand;
import com.zhenghaikj.shop.entity.CategoryData;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.mvp.contract.AddBrandContract;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class StoreDetailModel implements StoreDetailContract.Model {
    private Map<String, String> map;
    private String timestamp;
    private String sign;



    @Override
    public Observable<StoreDetailResult> GetVShop(String id, String Userkey) {
        map = new HashMap<>();
        map.put("id",id);
        map.put("userkey",Userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetVShop(id,Userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
