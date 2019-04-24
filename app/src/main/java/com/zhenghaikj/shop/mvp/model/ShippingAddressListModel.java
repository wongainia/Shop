package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.mvp.contract.ShippingAddressListContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShippingAddressListModel implements ShippingAddressListContract.Model {

    private Map<String, String> map;

    @Override
    public Observable<ShippingAddressList> GetShippingAddressList(String userkey) {
        map = new HashMap<>();
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        map.put("timestamp", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        String sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetShippingAddressList(userkey,"himalltest",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
