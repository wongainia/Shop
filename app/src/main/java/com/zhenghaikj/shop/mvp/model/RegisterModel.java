package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.api.ApiService;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.entity.RegisterResult;
import com.zhenghaikj.shop.mvp.contract.RegisterContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel implements RegisterContract.Model {
    private Map<String, String> map;
    private String sign;
    @Override
    public Observable<RegisterResult> Reg(String userName, String password,String oauthType,String email,String code,String oauthOpenId ,String oauthNickName) {
        map = new HashMap<>();
        map.put("username",userName);
        map.put("password",password);
        map.put("oauthtype",oauthType);
        map.put("email",email);
        map.put("code",code);
        map.put("oauthopenid",oauthOpenId);
        map.put("oauthnickname",oauthNickName);
        map.put("app_key","himalltest");
        map.put("timestamp", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().Reg(userName,password,oauthType,email,code,oauthOpenId,oauthNickName,"himalltest", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
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
    public Observable<LoginResult> GetUser(String userName, String password, String oauthType, String oauthOpenId, String oauthNickName) {
        map = new HashMap<>();
        map.put("username",userName);
        map.put("password",password);
        map.put("oauthtype",oauthType);
        map.put("oauthopenid",oauthOpenId);
        map.put("oauthnickname",oauthNickName);
        map.put("app_key","himalltest");
        map.put("timestamp",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetUser(userName, password, oauthType, oauthOpenId, oauthNickName,"himalltest", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
