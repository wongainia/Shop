package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.ConfirmModel;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.GetConfirmModel;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.mvp.contract.ConfirmOrderContract;

import org.json.JSONArray;

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
    private SPUtils spUtil;
    private String Userkey;

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
    public Observable<EasyResult> PostChangeOrderState(String orderId) {
        map = new HashMap<>();
        spUtil = SPUtils.getInstance("token");
        Userkey = spUtil.getString("UserKey");
        map.put("userkey", Userkey);
        map.put("orderid",orderId);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign= ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostChangeOrderState(orderId,Userkey,"himalltest",timestamp,sign)
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
        map.put("cartitemids",CartItemId);
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

    @Override
    public Observable<ConfirmModel> PostSubmitOrderByCart(String cartItemIds, String recieveAddressId, String couponIds, String integral, String isCashOnDelivery, String invoiceType, String invoiceContext, String invoiceTitle, String orderRemarks, String userkey) {
        map = new HashMap<>();
        map.put("cartitemids",cartItemIds);
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
        return ApiRetrofit.getDefault().PostSubmitOrderByCart(cartItemIds,recieveAddressId,couponIds,integral,isCashOnDelivery,invoiceType,invoiceContext,invoiceTitle,orderRemarks,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String Bisid, String OrderId, String TotalAmount, JSONArray JsonStr, String ActualMoney) {
        return ApiRetrofit2.getDefault().GetOrderStr(userid, Bisid,OrderId,TotalAmount,"3",JsonStr,ActualMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String Bisid, String OrderId, String TotalAmount, JSONArray JsonStr, String ActualMoney) {
        return ApiRetrofit2.getDefault().GetWXOrderStr(userid, Bisid,OrderId,TotalAmount,"3","mall",JsonStr,ActualMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> MallBalancePay(String OrderId,
                                                               String CustomerId,
                                                               JSONArray JsonStr,
                                                               String UserID,
                                                               String ActualMoney) {
        return ApiRetrofit2.getDefault().MallBalancePay(OrderId, CustomerId, JsonStr, UserID,ActualMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo) {
        return ApiRetrofit2.getDefault().WXNotifyManual(OutTradeNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit) {
        return ApiRetrofit2.getDefault().GetUserInfoList(userName, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
