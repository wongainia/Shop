package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Distribution;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.mvp.contract.DistributionContract;

import java.util.List;

public class DistributionPresenter extends DistributionContract.Presenter {
    @Override
    public void ProductList(String gentUserId,String userkey) {
        mModel.ProductList(gentUserId,userkey)
                .subscribe(new BaseObserver<List<Distribution>>() {
                    @Override
                    protected void onHandleSuccess(List<Distribution> value) {
                        mView.ProductList(value);
                    }
                });
    }

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
}
