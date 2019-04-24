package com.zhenghaikj.shop.mvp.model;


import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.SendMessage;
import com.zhenghaikj.shop.mvp.contract.BindPhoneContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BindPhoneModel implements BindPhoneContract.Model {

    private Map<String, String> map;
    private String sign;

    @Override
    public Observable<SendMessage> GetCode(String contact, String userkey) {
        map = new HashMap<>();
        map.put("contact",contact);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        map.put("time", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetCode(contact,userkey,"himalltest",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")), sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CheckMessage> GetCheckPhoneOrEmailCheckCode(String contact, String checkCode, String userkey) {
        map=new HashMap<>();
        map.put("contract",contact);
        map.put("checkcode",checkCode);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        map.put("time",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign=ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetCheckPhoneOrEmailCheckCode(contact,checkCode,userkey,"himalltest",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<GetImageCheckCode> GetImageCheckCode() {
        map = new HashMap<>();
//        map.put("oauthtype",oauthType);
//        map.put("oauthopenid",oauthOpenId);
//        map.put("oauthnickname",oauthNickName);
        map.put("app_key","himalltest");
        map.put("timestamp", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetImageCheckCode("himalltest", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")), sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CheckMessage> GetCheckUserName(String contact, String checkCode) {
        map=new HashMap<>();
        map.put("contact",contact);
        map.put("checkcode",checkCode);
        map.put("app_key","himalltest");
        map.put("timestamp", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetCheckUserName(contact,checkCode,"himalltest",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
