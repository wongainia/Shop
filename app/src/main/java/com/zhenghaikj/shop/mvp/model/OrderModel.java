package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.ChangeOrderAddress;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.mvp.contract.OrderContract;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderModel implements OrderContract.Model {

    private Map<String, String> map;
    private String sign;
    private String timestamp;
    private SPUtils spUtil;
    private String Userkey;

    @Override
    public Observable<Order> GetOrders(String orderStatus,String pageNo,String pageSize,String userkey ) {
        map = new HashMap<>();
        map.put("orderstatus",orderStatus);
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);
        map.put("userkey",userkey );
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetOrders(orderStatus,pageNo,pageSize,userkey ,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CloseOrder> PostCloseOrder(String orderId, String userkey) {
        map = new HashMap<>();
        map.put("orderid",orderId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostCloseOrder(orderId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ConfirmOrder> PostConfirmOrder(String orderId, String userkey) {
        map = new HashMap<>();
        map.put("orderid",orderId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostConfirmOrder(orderId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Express> GetExpressInfo(String orderId, String userkey) {
        map = new HashMap<>();
        map.put("orderid",orderId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetExpressInfo(orderId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String Bisid,String OrderId,String TotalAmount, JSONArray jsonStr,String ActualMoney) {
        return ApiRetrofit2.getDefault().GetOrderStr(userid, Bisid,OrderId,TotalAmount,"3",jsonStr,ActualMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String Bisid,String OrderId,String TotalAmount, JSONArray jsonStr,String ActualMoney) {
        return ApiRetrofit2.getDefault().GetWXOrderStr(userid, Bisid,OrderId,TotalAmount,"3","mall",jsonStr,ActualMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> MallBalancePay(String OrderId,
                                                               String CustomerId,
                                                               JSONArray JsonStr,
                                                               String UserID,String ActualMoney) {
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
    public Observable<EasyResult> CancelOrder(String cartItemIds,String recieveAddressId,String userkey) {
        map = new HashMap<>();
        map.put("cartitemids", cartItemIds);
        map.put("recieveaddressid","0");
        map.put("userkey", userkey);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().CancelOrder(cartItemIds,recieveAddressId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit) {
        return ApiRetrofit2.getDefault().GetUserInfoList(userName, limit)
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
    public Observable<ChangeOrderAddress> PostChangeOrderAddress(String OrderId, String ReceiveAddressId,String userkey) {
        map = new HashMap<>();
        map.put("orderid",OrderId);
        map.put("receiveaddressid",ReceiveAddressId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().PostChangeOrderAddress(OrderId,ReceiveAddressId,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> IsMallid(String MallID) {
        return ApiRetrofit2.getDefault().IsMallid(MallID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    
}
