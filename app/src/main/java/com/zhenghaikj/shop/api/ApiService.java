package com.zhenghaikj.shop.api;

import com.zhenghaikj.shop.entity.LoginResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 登录
     * 注：userName,password和oauthType,oauthOpenId,oauthNickName至少要有一组参数不为空
     * username,password在oauthType,oauthOpenId,oauthNickName不为空的情况下，可以为空。当username,password为空，oauthType,oauthOpenId,oauthNickName不为空时即是信任登录否则
     * 为普通登录
     * @param userName
     * @param password
     * @param oauthType
     * @param oauthOpenId
     * @param oauthNickName
     * @return {"Success": "true", "ErrorCode": null,"ErrorMsg": null,"UserId":110,"UserKey":"safsdasd"}
     * Success：是否成功，
     * ErrorCode：错误码，
     * ErrorMsg：错误信息，
     * UserId：用户编号
     * UserKey：用户登录凭证
     */
    @GET("Login/GetUser")
    Observable<LoginResult> GetUser(
            @Query("userName") String userName,
            @Query("password") String password,
            @Query("oauthType") String oauthType,
            @Query("oauthOpenId ") String oauthOpenId ,
            @Query("oauthNickName") String oauthNickName,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

}
