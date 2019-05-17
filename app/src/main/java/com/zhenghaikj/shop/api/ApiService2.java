package com.zhenghaikj.shop.api;

import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Address;
import com.zhenghaikj.shop.entity.Area;
import com.zhenghaikj.shop.entity.Brand;
import com.zhenghaikj.shop.entity.CategoryData;
import com.zhenghaikj.shop.entity.City;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.District;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Province;
import com.zhenghaikj.shop.entity.Track;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.entity.WorkOrder;

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
     * 获取工单列表
     * 废除-1，待审核0，派单中1，服务中2，已完成3
     */
    @FormUrlEncoded
    @POST("Order/FactoryGetOrderList")
    Observable<BaseResult<WorkOrder>> FactoryGetOrderList(@Field("UserID") String UserID, @Field("State") String state, @Field("page") String page, @Field("limit") String limit);
    /*
     * 取消订单
     * */
    @FormUrlEncoded
    @POST("Order/UpdateOrderState")
    Observable<BaseResult<Data<String>>> UpdateOrderState(
            @Field("OrderID") String OrderID,
            @Field("State") String State
    );
    /**
     * 工厂投诉
     * @param OrderID 订单id
     * @param Content  投诉原因
     * @return
     */
    @FormUrlEncoded
    @POST("Order/FactoryComplaint")
    Observable<BaseResult<Data<String>>> FactoryComplaint(
            @Field("OrderID") String OrderID,
            @Field("Content") String Content
    );
    /**
     * 工厂确认订单 结算
     * @param OrderID 订单id
     * @param PayPassword  支付密码
     * @return
     */
    @FormUrlEncoded
    @POST("Order/FactoryEnsureOrder")
    Observable<BaseResult<Data<String>>> FactoryEnsureOrder(
            @Field("OrderID") String OrderID,
            @Field("PayPassword") String PayPassword
    );
    /**
     * 用户确认订单 结算
     * @param OrderID 订单id
     * @param PayPassword  支付密码
     * @return
     */
    @FormUrlEncoded
    @POST("Order/EnSureOrder")
    Observable<BaseResult<Data<String>>> EnSureOrder(
            @Field("OrderID") String OrderID,
            @Field("PayPassword") String PayPassword
    );
    /*更新工单消息为已读*/
    @FormUrlEncoded
    @POST("Order/UpdateOrderFIsLook")
    Observable<BaseResult<Data<String>>> UpdateOrderFIsLook(@Field("OrderID") String OrderID,
                                                            @Field("IsLook") String IsLook,
                                                            @Field("FIsLook") String FIsLook);
    /**
     * 对某笔单子发起质保
     * @param OrderID 订单id
     * @return
     */
    @FormUrlEncoded
    @POST("Order/ApplyCustomService")
    Observable<BaseResult<Data<String>>> ApplyCustomService(
            @Field("OrderID") String OrderID
    );

    /**
     * 审核远程费
     * @param OrderID   订单id
     * @param BeyondState   -1拒绝 1通过
     * @return
     */
    @FormUrlEncoded
    @POST("Order/ApproveBeyondMoney")
    Observable<BaseResult<Data<String>>> ApproveBeyondMoney(
            @Field("OrderID") String OrderID,
            @Field("BeyondState") String BeyondState
    );
    /**
     * 审核配件申请
     * @param OrderID 订单id
     * @param AccessoryApplyState  -1拒绝 1通过
     * @return
     */
    @FormUrlEncoded
    @POST("Order/ApproveOrderAccessory")
    Observable<BaseResult<Data<String>>> ApproveOrderAccessory(
            @Field("OrderID") String OrderID,
            @Field("AccessoryApplyState") String AccessoryApplyState,
            @Field("NewMoney") String NewMoney
    );

    /**
     * 审核服务申请
     * @param OrderID 订单id
     * @param State  -1拒绝 1通过
     * @return
     */
    @FormUrlEncoded
    @POST("Order/ApproveOrderService")
    Observable<BaseResult<Data<String>>> ApproveOrderService(
            @Field("OrderID") String OrderID,
            @Field("State") String State
    );
    /**
     * 工厂添加配件快递信息
     * @param OrderID 订单id
     * @param ExpressNo  快递单号
     * @return
     */
    @FormUrlEncoded
    @POST("Order/AddOrUpdateExpressNo")
    Observable<BaseResult<Data<String>>> AddOrUpdateExpressNo(
            @Field("OrderID") String OrderID,
            @Field("ExpressNo") String ExpressNo
    );
    /**
     * 旧件是否需要返件
     * @param OrderID
     * @param IsReturn
     * @param AddressBack
     * @param PostPayType
     * @return
     */
    @FormUrlEncoded
    @POST("Order/UpdateIsReturnByOrderID")
    Observable<BaseResult<Data<String>>> UpdateIsReturnByOrderID(
            @Field("OrderID") String OrderID,
            @Field("IsReturn") String IsReturn,
            @Field("AddressBack") String AddressBack,
            @Field("PostPayType") String PostPayType
    );
    /**
     * 获取收货地址列表
     * @param UserID
     * @return
     */
    @FormUrlEncoded
    @POST("Account/GetAccountAddress")
    Observable<BaseResult<List<Address>>> GetAccountAddress(
            @Field("UserID") String UserID
    );
    /*工单跟踪*/
    @FormUrlEncoded
    @POST("Order/GetOrderRecordByOrderID")
    Observable<BaseResult<List<Track>>> GetOrderRecordByOrderID(@Field("OrderID") String OrderID);
    /**
     * 快递信息
     * @param ExpressNo  快递单号
     * @return
     */
    @FormUrlEncoded
    @POST("Order/GetExpressInfo")
    Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(@Field("ExpressNo") String ExpressNo);
    /**
     * 获取工单详情
     * 通过OrderID获取工单详情
     */
    @FormUrlEncoded
    @POST("Order/GetOrderInfo")
    Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(@Field("OrderID") String OrderID);

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

    /**
     * 添加品牌
     */
    @FormUrlEncoded
    @POST("FactoryConfig/AddFactoryBrand")
    Observable<BaseResult<Data>> AddFactoryBrand(@Field("UserID") String UserID, @Field("FBrandName") String FBrandName);
    /**
     * 获取品牌
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryBrand")
    Observable<BaseResult<List<Brand>>> GetFactoryBrand(@Field("UserID") String UserID);
    /**
     *删除工厂品牌
     */
    @FormUrlEncoded
    @POST("FactoryConfig/DeleteFactoryBrand")
    Observable<BaseResult<Data>> DeleteFactoryBrand(@Field("FBrandID") String FBrandID);
    /**
     * 获取分类
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryCategory")
    Observable<BaseResult<CategoryData>> GetFactoryCategory(@Field("ParentID") String ParentID);

    /**
     * 获取子分类
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryCategory")
    Observable<BaseResult<CategoryData>> GetChildFactoryCategory(@Field("ParentID") String ParentID);
}