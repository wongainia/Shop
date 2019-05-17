package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Area;
import com.zhenghaikj.shop.entity.City;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.District;
import com.zhenghaikj.shop.entity.Province;
import com.zhenghaikj.shop.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public interface AddOrderContract {
    interface Model extends BaseModel {
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
                                                      String ExpressNo);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
    }

    interface View extends BaseView {
        void GetProvince(BaseResult<List<Province>> baseResult);
        void GetCity(BaseResult<Data<List<City>>> baseResult);
        void GetArea(BaseResult<Data<List<Area>>> baseResult);
        void GetDistrict(BaseResult<Data<List<District>>> baseResult);
        void AddOrder(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
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
                                      String ExpressNo);
        public abstract void GetUserInfoList(String userName, String limit);
    }

}