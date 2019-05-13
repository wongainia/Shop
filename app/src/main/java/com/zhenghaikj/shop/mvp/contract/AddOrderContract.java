package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;

import io.reactivex.Observable;

public interface AddOrderContract {
    interface Model extends BaseModel {
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
        void AddOrder(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
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
