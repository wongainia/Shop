package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.AddressCodeResult;
import com.zhenghaikj.shop.entity.Area;
import com.zhenghaikj.shop.entity.Brand;
import com.zhenghaikj.shop.entity.CategoryData;
import com.zhenghaikj.shop.entity.City;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.District;
import com.zhenghaikj.shop.entity.Province;
import com.zhenghaikj.shop.entity.ShippingAddressList;
import com.zhenghaikj.shop.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public interface AddOrderContract {
    interface Model extends BaseModel {
        Observable<BaseResult<CategoryData>> GetFactoryCategory(String ParentID);
        Observable<BaseResult<CategoryData>> GetChildFactoryCategory(String ParentId);
        Observable<BaseResult<CategoryData>> GetChildFactoryCategory2(String ParentId);
        Observable<BaseResult<Data<List<Brand>>>> GetBrand(String UserId);
        Observable<BaseResult<List<Province>>> GetProvince();
        Observable<BaseResult<Data<List<City>>>> GetCity(String parentcode);
        Observable<BaseResult<Data<List<Area>>>> GetArea(String parentcode);
        Observable<BaseResult<Data<List<District>>>> GetDistrict(String parentcode);
        Observable<BaseResult<Data<String>>> AddOrder(String TypeID,
                                                      String TypeName,
                                                      String UserID,
                                                      String FBrandID,
                                                      String BrandName,
                                                      String FCategoryID,
                                                      String CategoryName,
                                                      String SubCategoryID,
                                                      String SubCategoryName,
                                                      String ProvinceCode,
                                                      String CityCode,
                                                      String AreaCode,
                                                      String DistrictCode,
                                                      String Address,
                                                      String UserName,
                                                      String Phone,
                                                      String Memo,
                                                      String OrderMoney,
                                                      String RecycleOrderHour,
                                                      String Guarantee,
                                                      String AccessorySendState,
                                                      String Extra,
                                                      String ExtraTime,
                                                      String ExtraFee,
                                                      String Num,
                                                      String IsRecevieGoods,
                                                      String ExpressNo,
                                                      String MallID,
                                                      String ordersource);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
        Observable<AddressCodeResult> GetRegion(String id);
        Observable<ShippingAddressList> GetShippingAddressList(String userkey);

    }

    interface View extends BaseView {
        void GetFactoryCategory(BaseResult<CategoryData> baseResult);
        void GetChildFactoryCategory(BaseResult<CategoryData> baseResult);
        void GetChildFactoryCategory2(BaseResult<CategoryData> baseResult);
        void GetBrand(BaseResult<Data<List<Brand>>> baseResult);
        void GetProvince(BaseResult<List<Province>> baseResult);
        void GetCity(BaseResult<Data<List<City>>> baseResult);
        void GetArea(BaseResult<Data<List<Area>>> baseResult);
        void GetDistrict(BaseResult<Data<List<District>>> baseResult);
        void AddOrder(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> Result);
        void GetRegion(AddressCodeResult result);
        void GetShippingAddressList(ShippingAddressList result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetFactoryCategory(String ParentID);
        public abstract void GetChildFactoryCategory(String ParentId);
        public abstract void GetChildFactoryCategory2(String ParentId);
        public abstract void GetBrand(String UserId);
        public abstract void GetProvince();

        public abstract void GetCity(String parentcode);

        public abstract void GetArea(String parentcode);

        public abstract void GetDistrict(String parentcode);
        public abstract void AddOrder(String TypeID,
                                      String TypeName,
                                      String UserID,
                                      String FBrandID,
                                      String BrandName,
                                      String FCategoryID,
                                      String CategoryName,
                                      String SubCategoryID,
                                      String SubCategoryName,
                                      String ProvinceCode,
                                      String CityCode,
                                      String AreaCode,
                                      String DistrictCode,
                                      String Address,
                                      String UserName,
                                      String Phone,
                                      String Memo,
                                      String OrderMoney,
                                      String RecycleOrderHour,
                                      String Guarantee,
                                      String AccessorySendState,
                                      String Extra,
                                      String ExtraTime,
                                      String ExtraFee,
                                      String Num,
                                      String IsRecevieGoods,
                                      String ExpressNo,
                                      String MallID,
                                      String ordersource);
        public abstract void GetUserInfoList(String userName, String limit);
        public abstract void GetRegion(String id);
        public abstract void GetShippingAddressList(String userkey);
    }

}
