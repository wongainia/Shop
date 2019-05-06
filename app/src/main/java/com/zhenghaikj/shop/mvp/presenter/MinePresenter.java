package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.MineContract;

public class MinePresenter extends MineContract.Presenter {
    @Override
    public void PersonalInformation(String UserKey) {
        mModel.PersonalInformation(UserKey)
                .subscribe(new BaseObserver<PersonalInformation>() {
                    @Override
                    protected void onHandleSuccess(PersonalInformation value) {
                        mView.PersonalInformation(value);
                    }
                });
    }
    @Override
    public void GetHistoryVisite(String userkey) {
        mModel.GetHistoryVisite(userkey)
                .subscribe(new BaseObserver<HistoryVisite>() {
                    @Override
                    protected void onHandleSuccess(HistoryVisite value) {
                        mView.GetHistoryVisite(value);
                    }
                });
    }
    @Override
    public void AddOrder(String TypeID, String TypeName, String UserID, String FBrandID, String BrandName, String FCategoryID, String CategoryName, String SubCategoryID, String SubCategoryName, String ProvinceCode, String CityCode, String AreaCode,String DistrictCode, String Address, String UserName, String Phone, String Memo, String OrderMoney, String RecycleOrderHour, String Guarantee, String AccessorySendState, String Extra, String ExtraTime, String ExtraFee,String Num) {
        mModel.AddOrder(TypeID, TypeName, UserID, FBrandID, BrandName, FCategoryID, CategoryName, SubCategoryID, SubCategoryName, ProvinceCode, CityCode, AreaCode,DistrictCode, Address, UserName, Phone, Memo, OrderMoney, RecycleOrderHour, Guarantee, AccessorySendState, Extra, ExtraTime, ExtraFee,Num)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddOrder(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String userName, String limit) {
        mModel.GetUserInfoList(userName, limit)
                .subscribe(new BaseObserver2<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }
}
