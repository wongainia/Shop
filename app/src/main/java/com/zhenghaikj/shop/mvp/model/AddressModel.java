package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.Address;
import com.zhenghaikj.shop.entity.RegionResult;
import com.zhenghaikj.shop.mvp.contract.AddressContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddressModel implements AddressContract.Model {

    private Map<String, String> map;
    private String sign;
    private String timestamp;

    @Override
    public Observable<List<RegionResult>> GetAllRegion(
    ) {
        map = new HashMap<>();

        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetAllRegion("himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<List<RegionResult>> GetSubRegion(
            String id
    ) {
        map = new HashMap<>();

        map.put("parent",id);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetSubRegion(id,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Address> PostAddShippingAddress(String regionId, String address, String phone, String shipTo, String latitude, String longitude, String Userkey) {
        map = new HashMap<>();
        map.put("regionid",regionId);
        map.put("address",address);
        map.put("phone",phone);
        map.put("shipto",shipTo);
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        map.put("userkey",Userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostAddShippingAddress(regionId,address,phone,shipTo,latitude,longitude,Userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<Address> PostEditShippingAddress(String id,String regionId, String address, String phone, String shipTo, String latitude, String longitude, String Userkey) {
        map = new HashMap<>();
        map.put("id",id);
        map.put("regionid",regionId);
        map.put("address",address);
        map.put("phone",phone);
        map.put("shipto",shipTo);
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        map.put("userkey",Userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostEditShippingAddress(id,regionId,address,phone,shipTo,latitude,longitude,Userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<String> PostSetDefaultAddress(String addId, String userkey) {
        map = new HashMap<>();
        map.put("addid",addId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostSetDefaultAddress(addId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
