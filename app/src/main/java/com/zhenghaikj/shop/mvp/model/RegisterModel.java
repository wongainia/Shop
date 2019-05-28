package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.entity.RegisterResult;
import com.zhenghaikj.shop.entity.SendMessage;
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
    private String timestamp;
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
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().Reg(userName,password,oauthType,email,code,oauthOpenId,oauthNickName,"himalltest", timestamp,sign)
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
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetImageCheckCode("himalltest", timestamp, sign)
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
    @Override
    public Observable<BaseResult<Data>> AddFactoryBrand(String UserID, String FBrandName) {
        return ApiRetrofit2.getDefault().AddFactoryBrand(UserID, FBrandName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<SendMessage> GetCode(String contact, String userkey) {
        map = new HashMap<>();
        map.put("contact",contact);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetCode(contact,null,null,"himalltest",timestamp, sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CheckMessage> GetCheckPhoneOrEmailCheckCode(String contact, String checkCode, String userkey) {
        map=new HashMap<>();
        map.put("contact",contact);
        map.put("checkcode",checkCode);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign=ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetCheckPhoneOrEmailCheckCode(contact,checkCode,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<CheckMessage> CheckUserName(String contact, String checkCode) {
        map=new HashMap<>();
        map.put("contact",contact);
        map.put("checkcode",checkCode);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().CheckUserName(contact,checkCode,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
