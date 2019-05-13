package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.AddOrderContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddOrderModel implements AddOrderContract.Model {

    @Override
    public Observable<BaseResult<Data<String>>> AddOrder(String TypeID, String TypeName, String UserID, String FBrandID, String BrandName, String FCategoryID, String CategoryName, String SubCategoryID, String SubCategoryName, String ProvinceCode, String CityCode, String AreaCode,String DistrictCode, String Address, String UserName, String Phone, String Memo, String OrderMoney, String RecycleOrderHour, String Guarantee, String AccessorySendState, String Extra, String ExtraTime, String ExtraFee,String Num,String IsRecevieGoods, String ExpressNo) {
        return ApiRetrofit2.getDefault().AddOrder(TypeID, TypeName, UserID, FBrandID, BrandName, FCategoryID, CategoryName, SubCategoryID, SubCategoryName , ProvinceCode, CityCode, AreaCode,DistrictCode, Address, UserName, Phone, Memo, OrderMoney, RecycleOrderHour, Guarantee, AccessorySendState, Extra, ExtraTime, ExtraFee,Num,IsRecevieGoods, ExpressNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit) {
        return ApiRetrofit2.getDefault().GetUserInfoList(userName, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
