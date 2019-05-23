package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
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
import com.zhenghaikj.shop.mvp.contract.AddOrderContract;

import java.util.List;

public class AddOrderPresenter extends AddOrderContract.Presenter {
    @Override
    public void GetFactoryCategory(String ParentID) {
        mModel.GetFactoryCategory(ParentID)
                .subscribe(new BaseObserver2<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetFactoryCategory(value);
                    }
                });
    }

    @Override
    public void GetChildFactoryCategory(String ParentId) {
        mModel.GetChildFactoryCategory(ParentId)
                .subscribe(new BaseObserver2<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetChildFactoryCategory(value);
                    }
                });
    }
    @Override
    public void GetChildFactoryCategory2(String ParentId) {
        mModel.GetChildFactoryCategory2(ParentId)
                .subscribe(new BaseObserver2<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetChildFactoryCategory2(value);
                    }
                });
    }

    @Override
    public void GetBrand(String UserId) {
        mModel.GetBrand(UserId)
                .subscribe(new BaseObserver2<List<Brand>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Brand>> value) {
                        mView.GetBrand(value);
                    }
                });
    }
    @Override
    public void GetProvince() {
        mModel.GetProvince()
                .subscribe(new BaseObserver2<List<Province>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Province>> value) {
                        mView.GetProvince(value);
                    }
                });
    }

    @Override
    public void GetCity(String parentcode) {
        mModel.GetCity(parentcode)
                .subscribe(new BaseObserver2<Data<List<City>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<City>>> value) {
                        mView.GetCity(value);
                    }
                });
    }

    @Override
    public void GetArea(String parentcode) {
        mModel.GetArea(parentcode)
                .subscribe(new BaseObserver2<Data<List<Area>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Area>>> value) {
                        mView.GetArea(value);
                    }
                });
    }

    @Override
    public void GetDistrict(String parentcode) {
        mModel.GetDistrict(parentcode)
                .subscribe(new BaseObserver2<Data<List<District>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<District>>> value) {
                        mView.GetDistrict(value);
                    }
                });
    }
    @Override
    public void AddOrder(String TypeID, String TypeName, String UserID, String FBrandID, String BrandName, String FCategoryID, String CategoryName, String SubCategoryID, String SubCategoryName, String ProvinceCode, String CityCode, String AreaCode,String DistrictCode, String Address, String UserName, String Phone, String Memo, String OrderMoney, String RecycleOrderHour, String Guarantee, String AccessorySendState, String Extra, String ExtraTime, String ExtraFee,String Num, String IsRecevieGoods, String ExpressNo,String MallID) {
        mModel.AddOrder(TypeID, TypeName, UserID, FBrandID, BrandName, FCategoryID, CategoryName, SubCategoryID, SubCategoryName, ProvinceCode, CityCode, AreaCode,DistrictCode, Address, UserName, Phone, Memo, OrderMoney, RecycleOrderHour, Guarantee, AccessorySendState, Extra, ExtraTime, ExtraFee,Num,IsRecevieGoods,ExpressNo,MallID)
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
    @Override
    public void GetRegion(String id) {
        mModel.GetRegion(id)
                .subscribe(new BaseObserver<AddressCodeResult>() {
                    @Override
                    protected void onHandleSuccess(AddressCodeResult value) {
                        mView.GetRegion(value);
                    }
                });
    }
    @Override
    public void GetShippingAddressList(String userkey) {
        mModel.GetShippingAddressList(userkey)
                .subscribe(new BaseObserver<ShippingAddressList>() {
                    @Override
                    protected void onHandleSuccess(ShippingAddressList value) {
                        mView.GetShippingAddressList(value);
                    }
                });
    }
}
