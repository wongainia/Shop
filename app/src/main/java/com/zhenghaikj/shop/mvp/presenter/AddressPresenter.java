package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Address;
import com.zhenghaikj.shop.entity.RegionResult;
import com.zhenghaikj.shop.mvp.contract.AddressContract;

import java.util.List;

public class AddressPresenter extends AddressContract.Presenter {
    @Override
    public void GetAllRegion() {
        mModel.GetAllRegion()
                .subscribe(new BaseObserver<List<RegionResult>>() {
                    @Override
                    protected void onHandleSuccess(List<RegionResult> value) {
                        mView.GetAllRegion(value);
                    }
                });
    }
    @Override
    public void GetSubRegion(String id) {
        mModel.GetSubRegion(id)
                .subscribe(new BaseObserver<List<RegionResult>>() {
                    @Override
                    protected void onHandleSuccess(List<RegionResult> value) {
                        mView.GetSubRegion(value);
                    }
                });
    }

    @Override
    public void PostAddShippingAddress(String regionId, String address, String phone, String shipTo, String latitude, String longitude, String Userkey) {
        mModel.PostAddShippingAddress(regionId, address, phone, shipTo, latitude, longitude, Userkey)
                .subscribe(new BaseObserver<Address>() {
                    @Override
                    protected void onHandleSuccess(Address value) {
                        mView.PostAddShippingAddres(value);
                    }
                });
    }
    @Override
    public void PostEditShippingAddress(String id,String regionId, String address, String phone, String shipTo, String latitude, String longitude, String Userkey) {
        mModel.PostEditShippingAddress(id,regionId, address, phone, shipTo, latitude, longitude, Userkey)
                .subscribe(new BaseObserver<Address>() {
                    @Override
                    protected void onHandleSuccess(Address value) {
                        mView.PostEditShippingAddress(value);
                    }
                });
    }
}
