package com.zhenghaikj.shop.api;

import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Area;
import com.zhenghaikj.shop.entity.City;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.District;
import com.zhenghaikj.shop.entity.Province;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService2 {


    /**
     * app用户登录
     */
    @FormUrlEncoded
    @POST("Account/LoginOn")
    Observable<BaseResult<Data<String>>> LoginOn(@Field("userName") String userName,
                                                 @Field("passWord") String passWord,
                                                 @Field("RoleType") String RoleType);
    /*获取用户信息*/
    @FormUrlEncoded
    @POST("Account/GetUserInfoList")
    Observable<BaseResult<UserInfo>> GetUserInfoList(@Field("UserID") String UserID,
                                                     @Field("limit") String limit);

    /**
     * 获取省
     */
    @POST("Config/GetProvince")
    Observable<BaseResult<List<Province>>> GetProvince();

    /**
     * 获取市
     */
    @FormUrlEncoded
    @POST("Config/GetCity")
    Observable<BaseResult<Data<List<City>>>> GetCity(@Field("parentcode") String parentcode);

    /**
     * 获取区
     */
    @FormUrlEncoded
    @POST("Config/GetArea")
    Observable<BaseResult<Data<List<Area>>>> GetArea(@Field("parentcode") String parentcode);

    /*
     * 获取街道，乡镇
     * */
    @FormUrlEncoded
    @POST("Config/GetDistrict")
    Observable<BaseResult<Data<List<District>>>> GetDistrict(@Field("parentcode") String parentcode);
    /**
     * 发布工单
     * TypeID;//分类ID 1维修 2安装 3其他服务
     * TypeName;//
     * UserID;//用户id
     * FBrandID;//品牌id
     * FBrandName;//品牌名
     * FCategoryID;//分类id
     * FCategoryName;//分类名
     * FProductTypeID;//型号id
     * FProductType;//型号名
     * ProvinceCode;//省code
     * CityCode;//市code
     * AreaCode;//区code
     * Address;//详细地址
     * UserName;//客户姓名
     * Phone;//客户手机
     * Memo;//故障描述
     * RecycleOrderHour;//回收时间
     * Guarantee;//保内Y保外N
     * AccessorySendState;//是否已发配件 Y是N否
     * Extra;//是否加急Y是N否
     * ExtraTime;//加急时间
     * ExtraFee;//加急费用
     */
    @FormUrlEncoded
    @POST("Order/AddOrder")
    Observable<BaseResult<Data<String>>> AddOrder(@Field("TypeID") String TypeID,
                                                  @Field("TypeName") String TypeName,
                                                  @Field("UserID") String UserID,
                                                  @Field("BrandID") String FBrandID,
                                                  @Field("BrandName") String BrandName,
                                                  @Field("CategoryID") String FCategoryID,
                                                  @Field("CategoryName") String CategoryName,
                                                  @Field("SubCategoryID") String SubCategoryID,
                                                  @Field("SubCategoryName") String SubCategoryName,
                                                  @Field("ProvinceCode") String ProvinceCode,
                                                  @Field("CityCode") String CityCode,
                                                  @Field("AreaCode") String AreaCode,
                                                  @Field("DistrictCode") String DistrictCode,
                                                  @Field("Address") String Address,
                                                  @Field("UserName") String UserName,
                                                  @Field("Phone") String Phone,
                                                  @Field("Memo") String Memo,
                                                  @Field("OrderMoney") String OrderMoney,
                                                  @Field("RecycleOrderHour") String RecycleOrderHour,
                                                  @Field("Guarantee") String Guarantee,
                                                  @Field("AccessorySendState") String AccessorySendState,
                                                  @Field("Extra") String Extra,
                                                  @Field("ExtraTime") String ExtraTime,
                                                  @Field("ExtraFee") String ExtraFee,
                                                  @Field("Num") String Num,
                                                  @Field("IsRecevieGoods") String IsRecevieGoods,
                                                  @Field("ExpressNo") String ExpressNo);


    @FormUrlEncoded
    @POST("Order/AddOrder")
    Observable<BaseResult<Data<String>>> AddOrderService(@Field("TypeID") String TypeID,
                                                         @Field("TypeName") String TypeName,
                                                         @Field("UserID") String UserID,
                                                         @Field("BrandID") String FBrandID,
                                                         @Field("BrandName") String BrandName,
                                                         @Field("CategoryID") String FCategoryID,
                                                         @Field("CategoryName") String CategoryName,
                                                         @Field("SubCategoryID") String SubCategoryID,
                                                         @Field("SubCategoryName") String SubCategoryName,
                                                         @Field("ProvinceCode") String ProvinceCode,
                                                         @Field("CityCode") String CityCode,
                                                         @Field("AreaCode") String AreaCode,
                                                         @Field("DistrictCode") String DistrictCode,
                                                         @Field("Address") String Address,
                                                         @Field("UserName") String UserName,
                                                         @Field("Phone") String Phone,
                                                         @Field("Memo") String Memo,
                                                         @Field("OrderMoney") String OrderMoney,
                                                         @Field("RecycleOrderHour") String RecycleOrderHour,
                                                         @Field("Guarantee") String Guarantee,
                                                         @Field("Num") String Num);

    /**
     * 充值信息
     * @param UserID 账号
     * @param TotalAmount 金额
     * @param Type  1余额 2 诚意金
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/GetOrderStr")
    Observable<BaseResult<Data<String>>> GetOrderStr(@Field("UserID") String UserID,
                                                     @Field("TotalAmount") String TotalAmount,
                                                     @Field("Type") String Type);
    /**
     * 充值信息
     * @param UserID 账号
     * @param TotalAmount 金额
     * @param Type  1余额 2 诚意金
     * @param Style  工厂传factory
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/GetWXOrderStr")
    Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(@Field("UserID") String UserID,
                                                          @Field("TotalAmount") String TotalAmount,
                                                          @Field("Type") String Type,
                                                          @Field("Style") String Style);
    /**
     * 微信人工回调OutTradeNo
     * @param OutTradeNo
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/WXNotifyManual")
    Observable<BaseResult<Data<String>>> WXNotifyManual(@Field("OutTradeNo") String OutTradeNo);


}
