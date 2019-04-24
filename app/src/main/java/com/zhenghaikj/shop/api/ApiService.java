package com.zhenghaikj.shop.api;

import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.ChagePassword;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.Category;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.entity.Logout;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.RegisterResult;
import com.zhenghaikj.shop.entity.Search;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.entity.SendMessage;
import com.zhenghaikj.shop.entity.ShippingAddressList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("Register/PostRegisterUser")
    Observable<RegisterResult> Reg(@Field("userName") String userName,
                                   @Field("password") String password,
                                   @Field("oauthType") String oauthType,
                                   @Field("email") String email,
                                   @Field("code") String code,
                                   @Field("oauthOpenId ") String oauthOpenId ,
                                   @Field("oauthNickName") String oauthNickName,
                                   @Query("app_key") String app_key,
                                   @Query("timestamp") String timestamp,
                                   @Query("sign") String sign        );

    /*
    * 图片验证码
    * */
    @GET("Login/GetImageCheckCode")
    Observable<GetImageCheckCode> GetImageCheckCode(@Query("app_key") String app_key,
                                                                @Query("timestamp") String timestamp,
                                                                @Query("sign") String sign);

    /**
     * 获取短信
     */
    @FormUrlEncoded
    @POST("UserCenter/GetPhoneOrEmailCheckCode")
    Observable<SendMessage> GetCode(@Query("contact") String contact,
                                    @Query("userkey") String userkey,
                                    @Query("app_key") String app_key,
                                    @Query("timestamp") String timestamp,
                                    @Query("sign") String sign);


    /**
     * 验证验证码
     */
    @FormUrlEncoded
    @POST("UserCenter/GetCheckPhoneOrEmailCheckCode")
    Observable<CheckMessage> GetCheckPhoneOrEmailCheckCode(@Field("contact") String contact,
                                                           @Field("checkCode")String checkCode,
                                                           @Field("userkey ")String userkey ,
                                                           @Field("app_key") String app_key,
                                                           @Field("timestamp") String timestamp,
                                                           @Field("sign") String sign);

    /**
     * 验证手机号或邮箱是否绑定账号
     */
    @GET("Login/GetCheckUserName")
    Observable<CheckMessage> GetCheckUserName(@Query("contact") String contact,
                                                           @Query("checkCode")String checkCode,
                                                           @Query("app_key") String app_key,
                                                           @Query("timestamp") String timestamp,
                                                           @Query("sign") String sign);

    /**
     * 修改 密码
     */
    @FormUrlEncoded
    @POST("UserCenter/PostChangePassword")
    Observable<ChagePassword> PostChangePassword(@Field("oldPassword") String oldPassword,
                                                 @Field("password")String password,
                                                 @Field("userkey")String userkey ,
                                                 @Field("app_key") String app_key,
                                                 @Field("timestamp") String timestamp,
                                                 @Field("sign") String sign);

    /**
     * 退出登录
     * @return
     */
    @POST("Login/PostLogout")
    Observable<Logout> PostLogout(
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 获取所有分类
     * @return
     */
    @GET("Category/GetCategories")
    Observable<Category> GetCategories(
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );
    /**
     * 搜索
     * @param keywords 搜索关键字
     * @param exp_keywords 渐进搜索关键字
     * @param cid 分类ID
     * @param b_id 品牌ID
     * @param orderKey 排序项（1：默认，2：销量，3：价格，4：评论数，5：上架时间）
     * @param orderType 排序方式（1：升序，2：降序）
     * @param pageNo 页码
     * @param pageSize 每页显示数据量
     * @param app_key
     * @param timestamp
     * @param sign
     * @return
     */
    @GET("search/GetSearchProducts")
    Observable<SearchResult> GetSearchProducts(
            @Query("keywords") String keywords,
            @Query("exp_keywords") String exp_keywords,
            @Query("cid") String cid,
            @Query("b_id") String b_id,
            @Query("orderKey") String orderKey,
            @Query("orderType") String orderType,
            @Query("pageNo") String pageNo,
            @Query("pageSize") String pageSize,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );



    /*个人信息*/
    @GET("UserCenter/GetUser")
    Observable<PersonalInformation> PersonalInformation(
            @Query("UserKey") String UserKey,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );


    /**
     * 根据id获取商品详情
     * @param id
     * @param app_key
     * @param timestamp
     * @param sign
     * @return
     */
    @GET("product/GetProductDetail")
    Observable<SearchResult> GetProductDetail(
            @Query("id") String id,
            @Query("UserKey") String Userkey,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 获取首页
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("home/Get")
    Observable<HomeResult> Get(
            @Query("pageNo") String pageNo,
            @Query("pageSize") String pageSize,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 获取当前用户的收货地址列表数据
     */
    @GET("ShippingAddress/GetShippingAddressList")
    Observable<ShippingAddressList> GetShippingAddressList(
            @Query("userkey") String userkey,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );
}
