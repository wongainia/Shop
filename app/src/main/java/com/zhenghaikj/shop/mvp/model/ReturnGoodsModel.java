package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.RefundApplyResult;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.mvp.contract.ReturnGoodsContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReturnGoodsModel implements ReturnGoodsContract.Model {

    private Map<String, String> map;
    private String timestamp;
    private String sign;

    @Override
    public Observable<OrderDetail> GetOrderDetail(String id, String userkey) {
        map = new HashMap<>();
        map.put("id",id);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetOrderDetail(id,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<RefundApplyResult> PostRefundApply(String OrderId, String OrderItemId, String RefundType, String ReturnQuantity, String Amount, String Reason, String ContactPerson, String ContactCellPhone, String RefundPayType, String userkey) {
        map = new HashMap<>();
        map.put("orderid",OrderId);
        map.put("orderitemid",OrderItemId);
        map.put("refundtype",RefundType);
        map.put("returnquantity",ReturnQuantity);
        map.put("amount",Amount);
        map.put("reason",Reason);
        map.put("contactperson",ContactPerson);
        map.put("contactcellphone",ContactCellPhone);
        map.put("refundpaytype",RefundPayType);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostRefundApply(OrderId,OrderItemId, RefundType, ReturnQuantity, Amount, Reason, ContactPerson,  ContactCellPhone,  RefundPayType,  userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
