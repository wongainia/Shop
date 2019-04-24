package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
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
}
