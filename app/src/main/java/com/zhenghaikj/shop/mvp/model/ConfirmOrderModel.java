package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.ConfirmModel;
import com.zhenghaikj.shop.entity.GetConfirmModel;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.mvp.contract.ConfirmOrderContract;
import com.zhenghaikj.shop.mvp.contract.ShippingAddressListContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ConfirmOrderModel implements ConfirmOrderContract.Model {

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
    public Observable<GetConfirmModel> GetSubmitModel(String skuId, String count, String userkey) {
        map = new HashMap<>();
        map.put("skuid",skuId);
        map.put("count",count);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetSubmitModel(skuId,count,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<GetConfirmModel> GetSubmitByCartModel(String CartItemId, String userkey) {
        map = new HashMap<>();
        map.put("cartitemid",CartItemId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetSubmitByCartModel(CartItemId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ConfirmModel> PostSubmitOrder(String skuIds, String counts, String recieveAddressId, String couponIds, String integral, String isCashOnDelivery, String invoiceType, String invoiceContext, String invoiceTitle, String orderRemarks, String userkey) {
        map = new HashMap<>();
        map.put("skuids",skuIds);
        map.put("counts",counts);
        map.put("recieveaddressid",recieveAddressId);
        map.put("couponids",couponIds);
        map.put("integral",integral);
        map.put("iscashondelivery",isCashOnDelivery);
        map.put("invoicetype",invoiceType);
        map.put("invoicecontext",invoiceContext);
        map.put("invoicetitle",invoiceTitle);
        map.put("orderremarks",orderRemarks);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostSubmitOrder(skuIds,counts,recieveAddressId,couponIds,integral,isCashOnDelivery,invoiceType,invoiceContext,invoiceTitle,orderRemarks,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
