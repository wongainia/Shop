package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.mvp.contract.LoginContract;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements LoginContract.Model {

    private Map<String, String> map;
    private String sign;

    @Override
    public Observable<LoginResult> GetUser(String userName, String password,String oauthType,String oauthOpenId,String oauthNickName) {
        map = new HashMap<>();
        map.put("userName",userName);
        map.put("password",password);
        map.put("oauthType",oauthType);
        map.put("oauthOpenId",oauthOpenId);
        map.put("oauthNickName",oauthNickName);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault(sign).GetUser(userName, password, oauthType, oauthOpenId, oauthNickName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
