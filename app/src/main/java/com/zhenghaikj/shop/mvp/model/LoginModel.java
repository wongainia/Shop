package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.mvp.contract.LoginContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements LoginContract.Model {

    private Map<String, String> map;
    private String sign;
    private String timestamp;

    @Override
    public Observable<LoginResult> GetUser(String userName, String password,String oauthType,String oauthOpenId,String oauthNickName) {
        map = new HashMap<>();
        map.put("username",userName);
        map.put("password",password);
        map.put("oauthtype",oauthType);
        map.put("oauthopenid",oauthOpenId);
        map.put("oauthnickname",oauthNickName);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetUser(userName, password, oauthType, oauthOpenId, oauthNickName,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> LoginOn(String userName, String passWord) {
        return ApiRetrofit2.getDefault().LoginOn(userName, passWord,"8")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public  Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token,String type,String UserID){
        return ApiRetrofit2.getDefault().AddAndUpdatePushAccount(token,"8",UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
