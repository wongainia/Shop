package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.PersonalInformation;

import io.reactivex.Observable;

public interface MineContract {
    interface Model extends BaseModel {
        Observable<PersonalInformation> PersonalInformation(String UserKey);
        Observable<HistoryVisite> GetHistoryVisite(String userkey);
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
                                                      String Num);
    }

    interface View extends BaseView {
        void PersonalInformation(PersonalInformation result);
        void GetHistoryVisite(HistoryVisite result);
        void AddOrder(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void PersonalInformation(String UserKey);
        public abstract void GetHistoryVisite(String userkey);
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
                                      String Num);
    }
}
