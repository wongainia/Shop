package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.DetailResult;
import com.zhenghaikj.shop.entity.GetGoodSKu;
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
    private String timestamp;



    @Override
    public Observable<DetailResult> GetProductDetail(
            String id,String Userkey
    ) {
        map = new HashMap<>();
        map.put("id",id);
        map.put("userkey",Userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);


        return ApiRetrofit.getDefault().GetProductDetail(id,Userkey,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<AddtoCartResult> PostAddProductToCart(String skuId, String count, String Userkey) {
        map = new HashMap<>();
        map.put("skuid",skuId);
        map.put("count",count);
        map.put("userkey",Userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostAddProductToCart(skuId,count,Userkey,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<GetGoodSKu> GetSKUInfo(String productId) {
        map = new HashMap<>();
        map.put("productid",productId);
        //map.put("userkey", SPUtils.getInstance("token").getString("UserKey"));
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetSKUInfo(productId,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CollectResult> PostAddFavoriteProduct(String productId, String Userkey) {
        map = new HashMap<>();
        map.put("productid",productId);
        map.put("userkey",Userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostAddFavoriteProduct(productId,Userkey,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }
}
