package com.zhenghaikj.shop.api;

import com.zhenghaikj.shop.activity.CartResult;
import com.zhenghaikj.shop.entity.Address;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.Category;
import com.zhenghaikj.shop.entity.ChagePassword;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.CollectionProduct;
import com.zhenghaikj.shop.entity.CollectionShop;
import com.zhenghaikj.shop.entity.DetailResult;
import com.zhenghaikj.shop.entity.GetGoodSKu;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.entity.Logout;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.entity.RegionResult;
import com.zhenghaikj.shop.entity.RegisterResult;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.entity.SendMessage;
import com.zhenghaikj.shop.entity.ShippingAddressList;

import java.util.List;

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
     *
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
    @GET("api/Login/GetUser")
    Observable<LoginResult> GetUser(
            @Query("userName") String userName,
            @Query("password") String password,
            @Query("oauthType") String oauthType,
            @Query("oauthOpenId ") String oauthOpenId,
            @Query("oauthNickName") String oauthNickName,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("api/Register/PostRegisterUser")
    Observable<RegisterResult> Reg(@Field("userName") String userName,
                                   @Field("password") String password,
                                   @Field("oauthType") String oauthType,
                                   @Field("email") String email,
                                   @Field("code") String code,
                                   @Field("oauthOpenId ") String oauthOpenId,
                                   @Field("oauthNickName") String oauthNickName,
                                   @Query("app_key") String app_key,
                                   @Query("timestamp") String timestamp,
                                   @Query("sign") String sign);

    /*
     * 图片验证码
     * */
    @GET("api/Login/GetImageCheckCode")
    Observable<GetImageCheckCode> GetImageCheckCode(@Query("app_key") String app_key,
                                                    @Query("timestamp") String timestamp,
                                                    @Query("sign") String sign);

    /**
     * 获取短信
     */
    @FormUrlEncoded
    @POST("api/UserCenter/GetPhoneOrEmailCheckCode")
    Observable<SendMessage> GetCode(@Query("contact") String contact,
                                    @Query("userkey") String userkey,
                                    @Query("app_key") String app_key,
                                    @Query("timestamp") String timestamp,
                                    @Query("sign") String sign);


    /**
     * 验证验证码
     */
    @FormUrlEncoded
    @POST("api/UserCenter/GetCheckPhoneOrEmailCheckCode")
    Observable<CheckMessage> GetCheckPhoneOrEmailCheckCode(@Field("contact") String contact,
                                                           @Field("checkCode") String checkCode,
                                                           @Field("userkey ") String userkey,
                                                           @Field("app_key") String app_key,
                                                           @Field("timestamp") String timestamp,
                                                           @Field("sign") String sign);

    /**
     * 验证手机号或邮箱是否绑定账号
     */
    @GET("api/Login/CheckUserName")
    Observable<CheckMessage> CheckUserName(@Query("contact") String contact,
                                           @Query("checkCode") String checkCode,
                                           @Query("app_key") String app_key,
                                           @Query("timestamp") String timestamp,
                                           @Query("sign") String sign);

    /**
     * 修改 密码
     */
    @FormUrlEncoded
    @POST("api/UserCenter/PostChangePassword")
    Observable<ChagePassword> PostChangePassword(@Field("oldPassword") String oldPassword,
                                                 @Field("password") String password,
                                                 @Field("userkey") String userkey,
                                                 @Field("app_key") String app_key,
                                                 @Field("timestamp") String timestamp,
                                                 @Field("sign") String sign);

    /**
     * 退出登录
     *
     * @return
     */
    @POST("api/Login/PostLogout")
    Observable<Logout> PostLogout(
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 获取所有分类
     *
     * @return
     */
    @GET("api/Category/GetCategories")
    Observable<Category> GetCategories(
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 搜索
     *
     * @param keywords     搜索关键字
     * @param exp_keywords 渐进搜索关键字
     * @param cid          分类ID
     * @param b_id         品牌ID
     * @param orderKey     排序项（1：默认，2：销量，3：价格，4：评论数，5：上架时间）
     * @param orderType    排序方式（1：升序，2：降序）
     * @param pageNo       页码
     * @param pageSize     每页显示数据量
     * @param app_key
     * @param timestamp
     * @param sign
     * @return
     */
    @GET("api/search/GetSearchProducts")
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
    @GET("api/UserCenter/GetUser")
    Observable<PersonalInformation> PersonalInformation(
            @Query("UserKey") String UserKey,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );


    /**
     * 根据id获取商品详情
     *
     * @param id
     * @param app_key
     * @param timestamp
     * @param sign
     * @return
     */

    @GET("api/product/GetProductDetail")
    Observable<DetailResult> GetProductDetail(
            @Query("id") String id,
            @Query("UserKey") String Userkey,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );


    /**
     * 获取首页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("api/home/Get")
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
    @GET("api/ShippingAddress/GetShippingAddressList")
    Observable<ShippingAddressList> GetShippingAddressList(
            @Query("userkey") String userkey,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 获取省市区数据
     *
     * @return
     */
    @GET("common/RegionAPI/GetAllRegion")
    Observable<List<RegionResult>> GetAllRegion(
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 获取街道数据
     *
     * @return
     * @id 区id
     */
    @GET("common/RegionAPI/GetSubRegion")
    Observable<List<RegionResult>> GetSubRegion(
            @Query("parent") String id,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 获取订单列表
     *
     * @return
     */
    @GET("api/MemberOrder/GetOrders")
    Observable<Order> GetOrders(
            @Query("orderStatus") String orderStatus,
            @Query("pageNo") String pageNo,
            @Query("pageSize") String pageSize,
            @Query("userkey") String userkey,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );


    /*
     *获取订单详情
     * */
    @GET("api/MemberOrder/GetOrderDetail")
    Observable<OrderDetail> GetOrderDetail(@Query("id") String id,
                                           @Query("userkey") String userkey,
                                           @Query("app_key") String app_key,
                                           @Query("timestamp") String timestamp,
                                           @Query("sign") String sign
    );

    /**
     * 获取我的足迹（商品浏览记录）列表
     *
     * @return
     */
    @GET("api/product/GetHistoryVisite")
    Observable<HistoryVisite> GetHistoryVisite(
            @Query("userkey") String userkey,
            @Query("app_key") String app_key,
            @Query("timestamp") String timestamp,
            @Query("sign") String sign
    );

    /**
     * 新增收货地址
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/ShippingAddress/PostAddShippingAddress")
    Observable<Address> PostAddShippingAddress(
            @Field("regionId") String regionId,
            @Field("address") String address,
            @Field("phone") String phone,
            @Field("shipTo") String shipTo,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("Userkey") String Userkey,
            @Field("app_key") String app_key,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign
    );

    /**
     * 编辑收货地址
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/ShippingAddress/PostEditShippingAddress")
    Observable<Address> PostEditShippingAddress(
            @Field("id") String id,
            @Field("regionId") String regionId,
            @Field("address") String address,
            @Field("phone") String phone,
            @Field("shipTo") String shipTo,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("Userkey") String Userkey,
            @Field("app_key") String app_key,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign
    );

    @FormUrlEncoded
    @POST("api/Cart/PostAddProductToCart")
    Observable<AddtoCartResult> PostAddProductToCart(@Field("skuId") String skuId,
                                                     @Field("count") String count,
                                                     @Field("Userkey") String Userkey,
                                                     @Query("app_key") String app_key,
                                                     @Query("timestamp") String timestamp,
                                                     @Query("sign") String sign
    );


    @GET("api/Cart/GetCartProduct")
    Observable<Cart> GetCartProduct(@Query("Userkey") String Userkey,
                                    @Query("app_key") String app_key,
                                    @Query("timestamp") String timestamp,
                                    @Query("sign") String sign
    );

    @GET("api/product/GetSKUInfo")
    Observable<GetGoodSKu> GetSKUInfo(@Query("productId") String productId,
                                      @Query("app_key") String app_key,
                                      @Query("timestamp") String timestamp,
                                      @Query("sign") String sign
    );

    /*
     * 新增或取消商品收藏
     * */
    @FormUrlEncoded
    @POST("api/product/PostAddFavoriteProduct")
    Observable<CollectResult> PostAddFavoriteProduct(@Field("productId") String productId,
                                                     @Field("userkey") String Userkey,
                                                     @Query("app_key") String app_key,
                                                     @Query("timestamp") String timestamp,
                                                     @Query("sign") String sign);

    /*
     * 获取用户收藏的商品
     * */
    @GET("api/UserCenter/GetUserCollectionProduct")
    Observable<CollectionProduct> GetUserCollectionProduct(@Query("pageNo") String pageNo,
                                                           @Query("pageSize") String pageSize,
                                                           @Query("userkey") String userkey,
                                                           @Query("app_key") String app_key,
                                                           @Query("timestamp") String timestamp,
                                                           @Query("sign") String sign);

    /*
     * 获取用户收藏的商品
     * */
    @GET("api/UserCenter/GetUserCollectionShop")
    Observable<CollectionShop> GetUserCollectionShop(@Query("pageNo") String pageNo,
                                                     @Query("pageSize") String pageSize,
                                                     @Query("userkey") String userkey,
                                                     @Query("app_key") String app_key,
                                                     @Query("timestamp") String timestamp,
                                                     @Query("sign") String sign);
    /*
     * 获取申请售后/退款列表
     * */
    @GET("api/OrderRefund/GetRefundList")
    Observable<Refund> GetRefundList(@Query("pageNo") String pageNo,
                                     @Query("pageSize") String pageSize,
                                     @Query("userkey") String userkey,
                                     @Query("app_key") String app_key,
                                     @Query("timestamp") String timestamp,
                                     @Query("sign") String sign);
    /*删除购物车众多商品*/
    @FormUrlEncoded
    @POST("api/Cart/PostDeleteCartProduct")
    Observable<CartResult> PostDeleteCartProduct(@Field("skuIds") String skuIds,
                                                 @Field("Userkey") String Userkey,
                                                 @Query("app_key") String app_key,
                                                 @Query("timestamp") String timestamp,
                                                 @Query("sign") String sign);


}
