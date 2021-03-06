package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.mvp.contract.ShippingAddressListContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShippingAddressListModel implements ShippingAddressListContract.Model {

    private Map<String, String> map;
    private String sign;
    private String timestamp;

    @Override
    public Observable<ShippingAddressList> GetShippingAddressList(String userkey) {
        map = new HashMap<>();
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetShippingAddressList(userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<EasyResult> PostDeleteShippingAddress(String id, String userkey) {
        map = new HashMap<>();
        map.put("id",id);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostDeleteShippingAddress(id,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
